#pragma once 
#include <string>
using std::string;
class linkedObj{
public:
    string fName;
    string lName;
    string address;
    string city;
    string phoneNumber;
    uintptr_t xorptr; //storing pointers as integers so that XOR math can be done to them
    
    //constructor
    linkedObj(const string& fName, const string& lName, const string& address, const string& city, const string& phoneNumber) : fName(fName), lName(lName), address(address), city(city), phoneNumber(phoneNumber), xorptr(0){}
};