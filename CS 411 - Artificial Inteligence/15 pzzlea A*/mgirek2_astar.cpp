#include <iostream>
#include <iomanip>
#include <vector>
#include <queue>
#include <cmath>
#include <fstream>
#include <unistd.h>
using namespace std;
typedef vector<int> vi;
typedef vector<vi> vvi;
typedef pair<int,int> ii;

int numNodesExpanded = 0;

vvi solvedGrid(4, vi(4)); //solved grid. Used for some functions below.

void process_mem_usage(double& vm_usage, double& resident_set)
{
    vm_usage     = 0.0;
    resident_set = 0.0;
    
    // the two fields we want
    unsigned long vsize;
    long rss;
    {
        std::string ignore;
        std::ifstream ifs("/proc/self/stat", std::ios_base::in);
        ifs >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore
        >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore
        >> ignore >> ignore >> vsize >> rss;
    }
    
    long page_size_kb = sysconf(_SC_PAGE_SIZE) / 1024; // in case x86-64 is configured to use 2MB pages
    vm_usage = vsize / 1024.0;
    resident_set = rss * page_size_kb;
}

void setupGrid(){ //mount the solved grid. Used at the start of the program
    int v = 1;
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++)
            solvedGrid[i][j] = v++;
    solvedGrid[3][3] = 0;
}

ii findPosition(vvi grid, int c){ //find coordinates of c in the grid
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++)
            if (grid[i][j] == c) return make_pair(i,j);
    return make_pair(-1, -1); //not found
}

bool isSolvable(vvi grid){
    vi f(16, 0);
    for(int i=0; i<4; i++)
        for(int j=0; j<4; j++)
            f[i*4+j] = grid[i][j]; //Transforms grid into an array
    ii blank = findPosition(grid, 0);
    int count = 0;
    int result = 0;
    for(int i=0; i<16; i++)
        for(int j=i+1; j<16; j++){
            if (f[i] == 0 || f[j] == 0) continue;
            if (f[i] > f[j]) result++;
        }
    count = result;
    
    if ((4 - blank.first) % 2 == 0) return count % 2 == 1; // (1)
    return count % 2 == 0; // (2)
}

bool isSolved(vvi &grid){ //Compares grid with a solved one. Returns true only if the two grids are equal.
    for(int i=0;i<4;i++)
        for(int j=0;j<4;j++)
            if(grid[i][j] != solvedGrid[i][j]) return false;
    return true;
}

struct State{
    vvi grid; // the current grid
    int f; // The result of the heuristic, calculated at the constructor. Used in the priority_queue
    string path; // The path from the initial state to this one.
    State(vvi grid, string path) : grid(grid), path(path){
        int h = 0;
        for(int i = 1; i<16; i++){ //Doesn't need to count the 0's distances.
            ii a = findPosition(grid, i), b = findPosition(solvedGrid, i);
            h += abs(a.first - b.first) + abs(a.second - b.second);
        }
        f = h;
        f += path.size();
        
    } //constructor
    bool operator>(const State &b) const{ return f > b.f;} //operator overload for the priority_queue
    char prev(){ // The previous state direction, that is, "From the previous state, we went to R, L, D or U?"
        if(path.size()) return *(path.end()-1);
        else return 0;
    }
};

typedef priority_queue<State, vector<State>, greater<State> > pqs;

string solve(pqs &pq){
    while(!pq.empty()){
        numNodesExpanded++;
        State s = pq.top(); pq.pop(); //dequeue state
        if(isSolved(s.grid) && s.path.size() <= 50) return s.path; //if solved and path found is below 50 steps
        ii zero = findPosition(s.grid, 0);
        //up
        if(zero.first > 0 && s.prev() != 'D'){ //does not enqueue previous state
            vvi ng = s.grid; swap(ng[zero.first][zero.second], ng[zero.first-1][zero.second]); // "moves" blank
            State ns(ng, s.path + 'U'); // new state
            pq.push(ns); //enqueue new state
        }
        //down
        if(zero.first < 3 && s.prev() != 'U'){
            vvi ng = s.grid; swap(ng[zero.first][zero.second], ng[zero.first+1][zero.second]);
            State ns(ng, s.path + 'D');
            pq.push(ns);
        }
        //left
        if(zero.second > 0 && s.prev() != 'R'){
            vvi ng = s.grid; swap(ng[zero.first][zero.second], ng[zero.first][zero.second-1]);
            State ns(ng, s.path + 'L');
            pq.push(ns);
        }
        //right
        if(zero.second < 3 && s.prev() != 'L'){
            vvi ng = s.grid; swap(ng[zero.first][zero.second], ng[zero.first][zero.second+1]);
            State ns(ng, s.path + 'R');
            pq.push(ns);
        }
    }
    return "This puzzle is not solvable."; //Should not reach this point
}

int main(int argc, char** argv){
    setupGrid();
    
    vvi solvedGrid(4, vi(4));
    double vm, rss;
    
    clock_t totalT=0; //total processing time

        //input of each set
        vvi grid(4, vi(4));
        for (int i = 0; i < 4; i++)
            for (int j = 0; j < 4; j++)
                cin >> grid[i][j];
        //Start timer
        clock_t t = clock();
        if(!isSolvable(grid)) cout << "This puzzle is not solvable." << endl; //not solvable
        else if(isSolved(grid)) cout << "" << endl; //already solved
        else{
            State initial(grid, "");
            pqs pq; pq.push(initial);
            string solution = solve(pq); //get solution
            cout <<"Moves: "<< solution << endl; //print solution, if found
        }
        //End timer and print time of this case
        t = clock() - t;

        totalT += t;

    //Print total processing time
    cout << "Number of Nodes expanded: "<<numNodesExpanded<<endl;
    cout << "Time Taken:  " << setiosflags(ios::fixed) << setprecision(6) << ((double)totalT)/CLOCKS_PER_SEC << "s" << endl;
    process_mem_usage(vm, rss);
    cout << "Memory used: "<<endl;
    cout << "VM: " << vm << "; RSS: " << rss << endl;
    return 0;
}
