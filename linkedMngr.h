#include "linkedObj.h"
#pragma once
class XORLinkedList{
private:
    linkedObj* head;
    linkedObj* tail;
    int size;

public:
    XORLinkedList() : head(nullptr), tail(nullptr), size(0){} //default constructor
};
