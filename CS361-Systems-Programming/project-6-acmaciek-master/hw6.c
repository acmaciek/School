#include "hw6.h"
#include <stdio.h>
#include <pthread.h>
#include <stdlib.h>

struct Elevator {
    int current_floor;
    int destination;
    int occupancy;
    int direction;
    pthread_mutex_t lock;
    pthread_cond_t ext,closeDoor,arrived[FLOORS];
    int passengers[FLOORS];
    
} Elevator[ELEVATORS];

void scheduler_init() {
    for(int i=0; i<ELEVATORS; ++i){
        Elevator[i].current_floor=0;
        Elevator[i].destination=0;
        Elevator[i].direction=-1;
        Elevator[i].occupancy=0;
        pthread_mutex_init(&Elevator[i].lock, 0);
        pthread_cond_init( &Elevator[i].ext, NULL);
        pthread_cond_init( &Elevator[i].closeDoor, NULL);
        for(int j=0; j<FLOORS; ++j){
            pthread_cond_init( &Elevator[i].arrived[j], NULL);
            Elevator[i].passengers[j]=0;
        }
    }
}


void passenger_request(int passenger, int from_floor, int to_floor,
                       void (*enter)(int, int),
                       void(*exit)(int, int))
{
    int currentFloor = rand() % ELEVATORS;
    pthread_mutex_lock(&Elevator[currentFloor].lock);
    Elevator[currentFloor].passengers[from_floor] += 1;
    pthread_cond_wait(&Elevator[currentFloor].arrived[from_floor],
                      &Elevator[currentFloor].lock);
    enter(passenger, currentFloor);
    Elevator[currentFloor].occupancy++;
    Elevator[currentFloor].destination = to_floor;
    pthread_cond_signal( &Elevator[currentFloor].closeDoor);
    pthread_cond_wait( &Elevator[currentFloor].ext,
                       &Elevator[currentFloor].lock);
    Elevator[currentFloor].passengers[from_floor] -= 1;
    exit(passenger, currentFloor);
    Elevator[currentFloor].occupancy--;
    pthread_cond_signal( &Elevator[currentFloor].closeDoor);
    pthread_mutex_unlock(&Elevator[currentFloor].lock);
    
}

void elevator_ready(int elevator, int at_floor,
                    void(*move_direction)(int, int),
                    void(*door_open)(int), void(*door_close)(int)) {

                        
                        pthread_mutex_lock(&Elevator[elevator].lock);
                        
                        if( Elevator[elevator].occupancy > 0 &&
                            Elevator[elevator].destination == at_floor){
                            
                            door_open(elevator);
                            pthread_cond_signal( &Elevator[elevator].ext);
                            
                            pthread_cond_wait( &Elevator[elevator].closeDoor,
                                               &Elevator[elevator].lock);
                            door_close(elevator);
                        }
                        else if(Elevator[elevator].occupancy == 0 &&
                            Elevator[elevator].passengers[at_floor] > 0){
                            
                            door_open(elevator);
                            pthread_cond_signal( &Elevator[elevator].arrived[at_floor]);
                            pthread_cond_wait( &Elevator[elevator].closeDoor, 
                                               &Elevator[elevator].lock);
                            door_close(elevator);
                        } 
                        else {
                            if(at_floor==0 || at_floor==FLOORS-1) 
                            Elevator[elevator].direction*=-1;
                            move_direction(elevator, Elevator[elevator].direction);
                            Elevator[elevator].current_floor = at_floor + Elevator[elevator].direction;
                        }
                        pthread_mutex_unlock( &Elevator[elevator].lock);
}

