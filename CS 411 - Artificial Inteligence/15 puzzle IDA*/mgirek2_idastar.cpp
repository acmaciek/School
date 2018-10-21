// Maciej Girek
// UIC CS 411 Fall 2018
//5 2 4 8 10 3 11 14 6 0 9 12 13 1 15 7

#include <iostream>
#include <iomanip>
#include <vector>
#include <queue>
#include <cmath>
#include <fstream>
#include <unistd.h>

using namespace std;

int map[4][4];
const int dr[4] = {1, 0, -1, 0};
const int dc[4] = {0, 1, 0, -1};
const int opp[4] = {2, 3, 0, 1};
int tr[15];
int tc[15];
int upper;
const char moves[4] = {'D', 'R', 'U', 'L'};
bool pass;
char path[50];
int numNodesExpanded = 0;


void process_mem_usage(double& vm_usage, double& resident_set)
{
    vm_usage     = 0.0;
    resident_set = 0.0;
    unsigned long vsize;
    long rss;
    {
        std::string ignore;
        std::ifstream ifs("/proc/self/stat", std::ios_base::in);
        ifs >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore
        >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore >> ignore
        >> ignore >> ignore >> vsize >> rss;
    }
    
    long page_size_kb = sysconf(_SC_PAGE_SIZE) / 1024;
    vm_usage = vsize / 1024.0;
    resident_set = rss * page_size_kb;
}

bool isSolvable(const int& grid)
{
    int line[4*4], cnt = 0, inversion = 0;
    
    for(int i = 0; i < 4; i++)
        for(int j = 0; j < 4; j++)
            line[cnt++] = map[i][j];
    
    for(int i = 0; i < 16; i++)
        if(line[i] < 15)
        {
            for(int j = i+1; j < 16; j++)
                if(line[i] > line[j])
                    inversion++;
        }
    return (grid&1)^(inversion&1);
}

void IDA(int depth, int a, int c, int est, int pre)
{
    if(pass) return;
    if(est == 0)
    {
        cout << path<<endl;
        pass = true;
        return ;
    }
    
    for(int i = 0; i < 4; i++)
        if(i != pre)
        {
            numNodesExpanded++;
            int nr = a + dr[i];
            int nc = c + dc[i], oCost, nCost, temp_map;
            
            if(nr >= 0 && nr < 4 && nc >= 0 && nc < 4)
            {
                //calculate the cost of manhattan distance
                temp_map = map[nr][nc];
                oCost = abs(nr-tr[temp_map]) + abs(nc-tc[temp_map]);
                nCost = abs(a-tr[temp_map]) + abs(c-tc[temp_map]);
                
                if(depth + est + nCost - oCost + 1 <= upper ) // swap
                {
                    map[a][c] = temp_map, map[nr][nc] = 15;
                    path[depth] = moves[i];
                    IDA(depth+1, nr, nc, est + nCost - oCost, opp[i]);
                    map[a][c] = 15, map[nr][nc] = temp_map;
                    if(pass) return;
                }
            }
        }
}

int main()
{
    clock_t totalT=0; //total processing time
    //Start timer
    clock_t t = clock();
    for(int i = 0; i < 15; i++){
        tr[i] = i/4, tc[i] = i%4;
    }
    double vm, rss;
    pass = false;
    int sr, sc;
    for(int i = 0; i < 4; i++)
        for(int j = 0; j < 4; j++)
        {
            cin >> map[i][j];
            if(map[i][j]) map[i][j]--;
            else          map[i][j] = 15, sr = i, sc = j;
        }
    
    if( !isSolvable(sr) )
        cout << "This puzzle is not solvable" << endl;
    else
    {
        int ret = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 4; j++)
                if(map[i][j] < 15)
                    ret += abs(i-tr[ map[i][j] ]) + abs(j - tc[ map[i][j] ]);
        
        upper = ret+5 <= 50 ? ret+5 : 50;
        
        while( !pass )
        {
            IDA(0, sr, sc, ret, -1);
            upper = upper+5 <= 50 ? upper+5 : 50;
        }
    }
    cout << "Number of Nodes expanded: "<<numNodesExpanded<<endl;
    //End timer and print time of this case
    t = clock() - t;
    
    totalT += t;
    cout << "Time Taken:  " << setiosflags(ios::fixed) << setprecision(6) << ((double)totalT)/CLOCKS_PER_SEC << "s" << endl;
    process_mem_usage(vm, rss);
    cout << "Memory used: "<<endl;
    cout << "VM: " << vm << "; RSS: " << rss << endl;
    return 0;
}
