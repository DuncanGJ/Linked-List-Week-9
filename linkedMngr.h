#pragma once
#include "linkedObj.h"
#include <stdexcept>
class XORLinkedList{
private:
    linkedObj* head;
    linkedObj* tail;
    int size;

public:
    XORLinkedList() : head(nullptr), tail(nullptr), size(0){} //default constructor

    //very basic append function
    void append(const string& fName, const string& lName, const string& address, const string& city, const string& phoneNumber){
        linkedObj* node = new linkedObj(fName, lName, address, city, phoneNumber); //instantiates new list node 
        if(head==nullptr){ //empty list case 
            head = node; //sets head & tail = to new node
            tail = node; 
        } else{
            tail->xorptr = tail->xorptr ^ (uintptr_t)node; //XORs tail's xorptr with node's address
            node->xorptr = node->xorptr ^ (uintptr_t)tail; //XORS node's xorptr with tail's address, xorptr should be 0 at this point (ha)
            tail = node; //sets tail to be the new node
        }
        size++; //increases our size
    }

    //Returns node n 
    linkedObj* get(int n){
        if (n < 0 || n >= size) {
        throw std::out_of_range("index out of bounds"); //fails early
        }

        linkedObj* current;
        uintptr_t prev = 0; //starts at 0, 0^a = a, head and tail xorptr store value a (next and last nodes respectively)
        int steps; 
        
        if (n < size/2){
            current = head; //if n is in the first half of the list start from the head 
            steps = n;
        } else {
            current = tail; //if n is in the last half start from the tail 
            steps = size - 1 - n; 
        }
        
        for (int i = 0; i < steps; i++){
            uintptr_t next = current -> xorptr ^ prev; //each xorptr stores (prev^next), (prev^next)^prev = next, symmetric works going backwards and forwards
            prev = (uintptr_t) current; //casting pointer of current node as (uintptr_t) and setting it as our previous ptr for next loop
            current = (linkedObj*) next; //casting the uintptr_t typed pointer next as a linkedObj* so we can access it's xorptr value in the next loop
        }
        return current; 
    }

    //removes node n
    void remove(int n){
        if (n < 0 || n >= size) {
            throw std::out_of_range("index out of bounds"); //fails early
        }
        if (size == 0 ){
            throw std::out_of_range("empty list"); //fails early
        }

        linkedObj* target = get(n); 
        if (size ==1){
            delete target;
            head = nullptr;
            tail = nullptr;
            size--;
            return; 
        }

        linkedObj* prev = (n==0) ? nullptr : get(n-1);
        linkedObj* next = (n==size-1) ? nullptr : get(n+1);
        

        if(n==0){//case if removing head
            next -> xorptr ^= (uintptr_t)target ^0;
            head = next;
        } else if(n == size-1){//case if removing tail
            prev -> xorptr ^= (uintptr_t)target ^0;
            tail = prev;
        } else{ //all other cases
            prev -> xorptr ^= (uintptr_t)target ^ (uintptr_t) next;
            next -> xorptr ^= (uintptr_t)target ^ (uintptr_t) prev;
        }
        delete target;
        size--;
    }    
};
