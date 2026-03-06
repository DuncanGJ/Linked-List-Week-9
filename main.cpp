#include <iostream>
#include <stdexcept>
#include "linkedMngr.h"
//extra credit
// main function for xor linked list testing
//basic xor linked list 
//utilizes the fact that a^b^b = a 
//a unintptr_t stores the xor (prev^next) and by tracking prev or next we can access either node symmetrically 
//credit for xor linked list goes to tsoding: https://www.youtube.com/watch?v=4KdvcQKNfbQ&t=531s
int main() {
    XORLinkedList list;

    list.append("Alice", "Smith", "1 Main St", "Springfield",  "555-0001");
    list.append("Bob",   "Jones", "2 Oak Ave", "Shelbyville",  "555-0002");
    list.append("Carol", "White", "3 Elm Rd",  "Capital City", "555-0003");
    std::cout << "Append:        " << list.toString() << "\n";

    list.insert(1, "Zara", "Lee", "4 Pine Blvd", "Ogdenville", "555-0004");
    std::cout << "Insert head:   " << list.toString() << "\n";

    list.insert(3, "Mike", "Gray", "5 Maple Dr", "Shelbyville", "555-0005");
    std::cout << "Insert middle: " << list.toString() << "\n";

    list.remove(2);
    std::cout << "Remove middle: " << list.toString() << "\n";

    list.remove(0);
    std::cout << "Remove head:   " << list.toString() << "\n";

    list.remove(2);
    std::cout << "Remove tail:   " << list.toString() << "\n";

    list.remove(0);
    list.remove(0);
    std::cout << "Remove all:    " << list.toString() << "\n";

    try { list.remove(0); }
    catch (const std::out_of_range& e) { std::cout << "Out of bounds: " << e.what() << "\n"; }

    return 0;
}
