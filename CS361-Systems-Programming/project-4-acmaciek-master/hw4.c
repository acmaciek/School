#include <stdlib.h>
#include <unistd.h>
#include <stdio.h>
#include <string.h>
#include <assert.h>

struct memory_region{
  size_t * start;
  size_t * end;
};

struct memory_region global_mem;
struct memory_region heap_mem;
struct memory_region stack_mem;

void walk_region_and_mark(void* start, void* end);

//p after third round of allocations: %zu, free %d, inuse %d\n",heap_size(),free_chunks(),inuse_chunks());how many ptrs into the heap we have
#define INDEX_SIZE 1000
void* heapindex[INDEX_SIZE];


//grabbing the address and size of the global memory region from proc 
void init_global_range(){
  char file[100];
  char * line=NULL;
  size_t n=0;
  size_t read_bytes=0;
  size_t start, end;

  sprintf(file, "/proc/%d/maps", getpid());
  FILE * mapfile  = fopen(file, "r");
  if (mapfile==NULL){
    perror("opening maps file failed\n");
    exit(-1);
  }

  int counter=0;
  while ((read_bytes = getline(&line, &n, mapfile)) != -1) {
    if (strstr(line, "hw4")!=NULL){
      ++counter;
      if (counter==3){
        sscanf(line, "%lx-%lx", &start, &end);
        global_mem.start=(size_t*)start;
        // with a regular address space, our globals spill over into the heap
        global_mem.end=malloc(256);
        free(global_mem.end);
      }
    }
    else if (read_bytes > 0 && counter==3) {
      if(strstr(line,"heap")==NULL) {
        // with a randomized address space, our globals spill over into an unnamed segment directly following the globals
        sscanf(line, "%lx-%lx", &start, &end);
        printf("found an extra segment, ending at %zx\n",end);						
        global_mem.end=(size_t*)end;
      }
      break;
    }
  }
  fclose(mapfile);
}


//marking related operations

int is_marked(size_t* chunk) {
  return ((*chunk) & 0x2) > 0;
}

void mark(size_t* chunk) {
  (*chunk)|=0x2;
}

void clear_mark(size_t* chunk) {
  (*chunk)&=(~0x2);
}

// chunk related operations

#define chunk_size(c)  ((*((size_t*)c))& ~(size_t)3 ) 
void* next_chunk(void* c) { 
  if(chunk_size(c) == 0) {
    printf("Panic, chunk is of zero size.\n");
  }
  if((c+chunk_size(c)) < sbrk(0))
    return ((void*)c+chunk_size(c));
  else 
    return 0;
}
int in_use(void *c) { 
  return (next_chunk(c) && ((*(size_t*)next_chunk(c)) & 1));
}


// index related operations

#define IND_INTERVAL ((sbrk(0) - (void*)(heap_mem.start - 1)) / INDEX_SIZE)
void build_heap_index() {
	size_t* curr = heap_mem.start - 1;
	size_t *next;
	int i;
	for (i = 0; i < INDEX_SIZE && curr < heap_mem.end;++i) {
		heapindex[i] = curr;
		curr = next_chunk(curr);	
}
	for(; i < INDEX_SIZE;++i) {
		heapindex[i] = NULL;
} 
}

// the actual collection code
void sweep() {
	size_t *curr = heap_mem.start; //pointer to a header of the heap
	while(curr < heap_mem.end && curr != NULL) { //loop throught the head and free unmarked blocks
		size_t *next = next_chunk(curr);
		if(is_marked(curr)) { clear_mark(curr);} //unmark the block if marked
		else if(in_use(curr)) { //else free
			free(curr + 1);
			heap_mem.end = sbrk(0);
		}
		curr = next;//move to the next block
	}
}

size_t * is_pointer(size_t * ptr) {
	if(ptr <= heap_mem.start || ptr > (size_t*)sbrk(0)) {return NULL;}

	size_t *curr = heap_mem.start - 1; // pointer to a header of the heap
	size_t *prev = NULL; // previos pointer set to NULL
	while(curr < heap_mem.end) { // while current didnt reach the end
		if(curr >= ptr) {break;} 
		prev = curr; //update previous
		curr = next_chunk(curr); //advance curr to next
	}
	return prev;
}

void markHelper(size_t * p)
{
  size_t * block = is_pointer(p);
  if(block == NULL) 
		return;
  if(is_marked(block))
		 return;
  mark(block);
  for(int i = 0; i < chunk_size(block); i++){
    markHelper((block + i));
  }
  return;
}

void walk_region_and_mark(void* start, void* end) {
  if(start >= end) 
		return;
  size_t * block = is_pointer(start); // determine if its a poitner
  if(block == NULL || is_marked(block)) //if block is null or block is already marked
		return walk_region_and_mark((start + 1), end); //recursive call
  mark(block);//mark the chunk
  for(int i = 0; i < chunk_size(block); i++){
    markHelper((block + i));
  }
  return walk_region_and_mark((start + 1), end);
}

void init_gc() {
  size_t stack_var;
  init_global_range();
  heap_mem.start=malloc(512);
  //since the heap grows down, the end is found first
  stack_mem.end=(size_t *)&stack_var;
}

void gc() {
  size_t stack_var;
  heap_mem.end=sbrk(0);
  //grows down, so start is a lower address
  stack_mem.start=(size_t *)&stack_var;

  // build the index that makes determining valid ptrs easier
  // implementing this smart function for collecting garbage can get bonus;
  // if you can't figure it out, just comment out this function.
  // walk_region_and_mark and sweep are enough for this project.
  build_heap_index();

  //walk memory regions
  walk_region_and_mark(global_mem.start,global_mem.end);
  walk_region_and_mark(stack_mem.start,stack_mem.end);
  sweep();
}
