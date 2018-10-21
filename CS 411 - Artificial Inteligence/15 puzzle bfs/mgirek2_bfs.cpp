#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <stack>
#include <queue>
#include <cstdlib>
#include <ctime>
#include <stdio.h>
#include <ctype.h>

using namespace std;

struct Node{
    string state;
    string path;
};

bool checkIfSolved(Node node){
    return (node.state.compare("010203040506070809101112131415xx")==0);
}

string swapBoardPieces(string state, unsigned int a, unsigned int b){ // function tthat swaps elements in a string state
    string board(state);
    string temp = board.substr(a,2); // temp is a character we search for
    board[a] = board[b];
    board[a+1] = board[b+1];
    board[b] = temp[0];
    board[b+1] = temp[1];
    return board;
}

Node bfs(Node startNode){
    queue<Node> frontier; //list for next nodes to expand
    frontier.push(startNode);
    map<string,int> expanded; //keeps track of nodes visited
    int numExpanded = 0;
    int maxFrontier = 0;
    while(!frontier.empty()){
        Node curNode = frontier.front();
        frontier.pop();
        numExpanded += 1;
        expanded[curNode.state] = 1;
        vector<Node> nextNodes;
        int emptyPiece = curNode.state.find("xx"); // location of the empty piece
        Node newNode;
        string state = curNode.state;
        
        if(emptyPiece > 7){ // UP
            newNode.state = swapBoardPieces(state, emptyPiece, emptyPiece-8);
            newNode.path = curNode.path;
            newNode.path += 'U';
            nextNodes.push_back(newNode);
        }
        if (emptyPiece < 24){ //DOWN
            newNode.state = swapBoardPieces(state, emptyPiece, emptyPiece+8);
            newNode.path = curNode.path;
            newNode.path += 'D';
            nextNodes.push_back(newNode);
        }
        if (emptyPiece%8 < 6){ //RIGHT
            newNode.state = swapBoardPieces(state, emptyPiece, emptyPiece+2);
            newNode.path = curNode.path;
            newNode.path += 'R';
            nextNodes.push_back(newNode);
        }
        if (emptyPiece%8 > 1){ //LEFT
            newNode.state = swapBoardPieces(state, emptyPiece, emptyPiece-2);
            newNode.path = curNode.path;
            newNode.path += 'L';
            nextNodes.push_back(newNode);
        }
        
        for(unsigned int i = 0; i < nextNodes.size(); i++){
            if (checkIfSolved(nextNodes[i])){
                cout << "Number of Nodes expanded: " << numExpanded << endl;
                return nextNodes[i];
            }
            if (expanded.find(nextNodes[i].state) != expanded.end()){
                continue;
            }
            frontier.push(nextNodes[i]);
            if(frontier.size() > maxFrontier){
                maxFrontier = frontier.size();
            }
        }
    }
}

int main(int argc, char* argv[]){
    int start_s=clock();
    Node startNode;
    int array[16];
    int number = 0;
    string start = "";
    cout << "Please enter the 16 numbers " << endl;
    
    for(int i =0; i <16; ++ i) {
        cin >> array[i];
        if (isalpha(array[i])) {
            cout << "Cant find solution";
            return 0;
        }
        if(array[i] == 0) {
            start += "xx";
        }
        else if(array[i] / 10 == 0) {
            start += "0";
            start += to_string(array[i]);
        }
        else{
           start += to_string(array[i]);
        }
    }

    startNode.state = start;
    Node solved;
    solved = bfs(startNode);


    if (!checkIfSolved(solved)){
        cout << "Solution cannot be found" << endl;
        return 1;
    }
    cout << "MOVES: " << solved.path << endl;
    int stop_s=clock();
    cout << "Time taken: " << (stop_s-start_s)/double(CLOCKS_PER_SEC)*1000 << endl;

    return 0;
}
