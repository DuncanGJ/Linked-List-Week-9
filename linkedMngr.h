#pragma once
#include "linkedObj.h"
#include <stdexcept>

//struct for storing our node in get and ptr of the previous node so that we can minimize the number of traversals when accessing arbitrary nodes
struct XORpair{
    linkedObj* node;
    uintptr_t prev;
};


class XORLinkedList{
private:
    linkedObj* head;
    linkedObj* tail;
    int size;

public:
    XORLinkedList() : head(nullptr), tail(nullptr), size(0){} //default constructor

    //destructor - loops through each node and deletes 
    ~XORLinkedList(){
        linkedObj* target = head; 
        linkedObj* next; 
        uintptr_t prev = 0; 
        for(int i = 0; i <= size-1; i++){
            next = (linkedObj*)(target->xorptr ^ prev); //xorptr ^ prev = next 
            prev = (uintptr_t) target; //casting target as uintptr_t which will become prev on the next iteration
            delete(target); //delete target
            target = next; //target is next, ready for next iteration
        }
    }

    //very basic append method
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

    //insert method 
    void insert(int place, const string& fName, const string& lName, const string& address, const string& city, const string& phoneNumber){
        if(place <= 0 || place > size){
            throw std::out_of_range("index out of bounds");//fails early
        }

        linkedObj* node = new linkedObj(fName, lName, address, city, phoneNumber); //instantiates new list node 
        linkedObj* target;
        uintptr_t prev;
        XORpair pair = get(place-1);
        target = pair.node;
        prev = pair.prev;
        
        target->xorptr ^= prev ^ (uintptr_t) node; //replaces prev ptr with node's pointer in target. The beauty of XOR is that this is symmetric for head and tail
        node-> xorptr = prev ^ (uintptr_t) target; //inserts prev and next node in our XORed ptr, again symmetric in case of head or tail
        if(prev == 0 && place ==1){
            head = node;
        } else{
              ((linkedObj*) prev)->xorptr ^=   (uintptr_t) target ^ (uintptr_t) node; //for non-head/tail nodes we also clear the target node from it's xorptr and xor in the new node 
        }
        size++;
    }
    //Returns node n, return XORpair struct, which allows us to access nodes at an arbitrary index with just 1 traversal. Adds additional complexity in exchange for reducing the # of traversals required.
    XORpair get(int n){
        if (n < 0 || n >= size) {
        throw std::out_of_range("index out of bounds"); //fails early
        }

        linkedObj* current;
        uintptr_t prev = 0; //starts at 0, 0^a = a, head and tail xorptr store value a (next and last nodes respectively)
        //int steps; 
        
        /* commenting out optimization because it is broken and I can't get the tail traversal to return correctly
        if (n < size/2){
            current = head; //if n is in the first half of the list start from the head 
            steps = n;
        } else {
            current = tail; //if n is in the last half start from the tail 
            steps = size - 1 - n; 
        }*/
        
        //uintptr_t next;

        for (int i = 0; i < n; i++){
            uintptr_t next = current -> xorptr ^ prev; //each xorptr stores (prev^next), (prev^next)^prev = next, symmetric works going backwards and forwards
            prev = (uintptr_t) current; //casting pointer of current node as (uintptr_t) and setting it as our previous ptr for next loop
            current = (linkedObj*) next; //casting the uintptr_t typed pointer next as a linkedObj* so we can access it's xorptr value in the next loop
        }
        
        return XORpair{current, prev};
    }

    //removes node n
    void remove(int n){
        if (n < 0 || n >= size) {
            throw std::out_of_range("index out of bounds"); //fails early
        }
 
        linkedObj* prev;
        linkedObj* next;
        linkedObj* target;
        XORpair pair = get(n);
        target = pair.node;
        prev = (linkedObj*) pair.prev;

        //handles case of deleting node from single element list 
        if (size ==1){
            delete target;
            head = nullptr;
            tail = nullptr;
            size--;
            return; 
        }       

        if(n==0){//case if removing head
            next = (linkedObj*) (target->xorptr ^ (uintptr_t) prev); //gathering next from prev & target
            next -> xorptr ^= (uintptr_t)target ^0;
            head = next;
        } else if(n == size-1){//case if removing tail
            prev -> xorptr ^= (uintptr_t)target ^0;
            tail = prev;
        } else{ //all other cases
            next = (linkedObj*) (target->xorptr ^ (uintptr_t) prev); //gathering next from prev & target
            prev -> xorptr ^= (uintptr_t)target ^ (uintptr_t) next;
            next -> xorptr ^= (uintptr_t)target ^ (uintptr_t) prev;
        }
        delete target;
        size--;
    }   

    //to string public implementation
    string toString(){
        return toString(head, 0);
    }
    
private:

    //private implementation of to string, recursive
    string toString(linkedObj* obj, uintptr_t prev){
        if (obj == nullptr){
            return "null";
        }
        return obj->fName + " <--> " + toString((linkedObj*)(obj->xorptr^prev), (uintptr_t) obj);
    }
};

