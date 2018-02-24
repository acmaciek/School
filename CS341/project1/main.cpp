/*main.cpp*/

//
// Banking program in modern C++.
//
// <<YOUR NAME>>
// U. of Illinois, Chicago
// CS 341: Spring 2018
// Project 01
//

#include <iostream>
#include <iomanip>
#include <fstream>
#include <string>
#include <sstream>
#include <vector>
#include <algorithm>
#include <functional>
#include <array>
#include "customer.h"

using namespace std;


int main()
{
  string  customerFilename, transactionsFilename;
  //cin >> customerFilename;
  customerFilename = "customers02.csv";
  cout << customerFilename << endl;

  //cin >> transactionsFilename;
  transactionsFilename = "transactions02.csv";
  cout << transactionsFilename << endl;

  ifstream  custFile(customerFilename);
  ifstream  txFile(transactionsFilename);

  cout << std::fixed;
  cout << std::setprecision(2);

  if (!custFile.good())
  {
    cout << "**ERROR: cannot open customers file: '" << customerFilename << "'" << endl;
    return -1;
  }
  if (!txFile.good())
  {
    cout << "**ERROR: cannot open transactions file: '" << transactionsFilename << "'" << endl;
    return -1;
  }
  //
  // TODO
  //
  string line,line2, name, id, id2,balance,transaction;
  vector<Customer> customers;
  vector<Customer> customers2;

   while (getline(custFile, line))
   {
   stringstream ss(line);
   getline(ss, name, ',');
   getline(ss, id, ',');
   getline(ss, balance);
   Customer S(name, stoi(id),stod(balance));

   customers.push_back(S);
 }
 std::sort(customers.begin(),customers.end(),[](Customer v1, Customer v2){
     if(v1.getName() < v2.getName()) {
       return true;
     }
     else
       return false;

 });

cout <<">> Customers before:"<<endl;
 for(auto& a : customers) {
   cout <<" "<<a.getName()<<" "<<"("<<a.getID()<<")"<<": $" <<a.getBalance()<<endl;
 }
 	//printf("A   %s, %f"  ,students[2].Name, students[2].Balance);
cout <<">> Processing..."<<endl;


   while (getline(txFile, line2))
   {
   stringstream ss(line2);
   getline(ss, id2, ',');
   getline(ss, transaction);
   Customer S(stoi(id2),stod(transaction),false,false);

   customers2.push_back(S);
 }



double bal;
  for(auto& a : customers) {
    for(auto& b : customers2) {
      if(a.getID() == b.getID2()) {
        b.setIDFound(true);
        bal = a.getBalance() + b.getTransaction();
      if(bal < 0) {
        b.setReject(true);
      }else {
        a.setBalance(bal);
      }
      }
    }
}

  for(auto& b : customers2) {
    if(b.getReject() == true) {
      cout<<" **Rejected Tx: "<<b.getID2()<<","<<b.getTransaction()<<endl;
    }
    else if(b.getIDFound() == false) {
      cout<<" **Invalid Tx: "<<b.getID2()<<","<<b.getTransaction()<<endl;
    }
}

std::sort(customers.begin(),customers.end(),[](Customer v1, Customer v2){
    if(v1.getBalance() > v2.getBalance()) {
      return true;
    }
    else
      return false;

});
cout<<">> Customers after:"<<endl;
for(auto& a : customers) {
  cout <<" "<<a.getName()<<" "<<"("<<a.getID()<<")"<<": $" <<a.getBalance()<<endl;
}
   return 0;
}
