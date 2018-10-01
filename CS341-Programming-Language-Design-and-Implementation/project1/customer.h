/*customer.h*/

//
// Customer class for banking program in modern C++.
//
// Maciej Girek
// U. of Illinois, Chicago
// CS 341: Spring 2018
// Project 01
//

#pragma once

#include <iostream>
#include <string>

using namespace std;


class Customer
{
private:
  int ID;
  int ID2;
  double Balance;
  string Name;
  double Transaction;
  bool IDFound;
  bool Reject;
public:

   Customer(string name, int id, double balance)
    :  ID(id), Balance(balance),Name(name)
  { }

  Customer(int id2,double transaction,bool idFound,bool rejected)
   :  ID2(id2),Transaction(transaction),IDFound(idFound),Reject(rejected)
 { }


   string getName() const
  {
    return Name;
  }

	double getBalance() const
	{
		return Balance;
	}

  void setBalance(double a)
	{
		 Balance = a;
	}

  double getTransaction() const
	{
		return Transaction;
	}

  int getID() const
	{
		return ID;
	}
  int getID2() const
  {
    return ID2;
  }

  bool getIDFound() const
  {
    return IDFound;
  }

  void setIDFound(bool a)
  {
    IDFound = a;
  }
  bool getReject() const
  {
    return Reject;
  }

  void setReject( bool a)
  {
     Reject = a;
  }
};
