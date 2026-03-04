//CS 145
//Duncan Jackson
//Linked List Lab
//Extra credit: 
//      linkedMngr() constructor passing array of linked list objects into array
//Extra credit: implementation of doubly linked list at the following methods
//      addEnd() doubly linked implementation
//      addFirst() doubly linked implementation
//      addN() doubly linked implementation
//      removeFirst() doubly linked implementation
//      removeLast() doubly linked implementation
//      removeN() doubly linked implementation
//      get() doubly linked implementation, traverses front-to-mid or back-to-mid based on n

public class linkedMngr {
    linkedObj head;
    linkedObj tail;
    int size;
    /////////////////
    //constructors//
    ///////////////

    public linkedMngr(){
        //empty linked list
    }

    public linkedMngr(linkedObj first){
        //linked list with 1 node
        addFirst(first);
    }

    public linkedMngr(linkedObj first, linkedObj last){
        //linkedlist with 2 nodes 
        addFirst(first);
        addEnd(last);
    }

    //EC passing array of linked list objects into array
    public linkedMngr(linkedObj[] listArr){
        for(int i = 0; i <= listArr.length - 1; i++){
            if(i == 0 ){
                addFirst(listArr[i]);
            }
            else{
                addEnd(listArr[i]);
            }
        }
    }
    /////////////////////////////////////
    //linked list manipulation methods//
    ///////////////////////////////////
    
    //Add to end 
    //EC addEnd() doubly linked implementation
    private void addEnd(linkedObj obj){
        if (size == 0){
            this.head = obj; 
            this.head.last = null;
            this.head.next = null;
            size++;
            return;
        } else if (size == 1){
            this.tail = obj;
            this.tail.last = this.head;
            this.head.next = this.tail;
            this.tail.next = null;
            size++;
            return;
        }
        obj.last = this.tail;
        this.tail.next = obj;
        this.tail = obj;
        this.tail.next = null;
        size++;
    }
    //Add to first
    //EC addFirst() doubly linked implementation
    private void addFirst(linkedObj obj){
        if (!(this.head == null)) this.head.last = obj;
        obj.next = this.head;
        obj.last = null; 
        this.head = obj;
        size++;
    }

    //Inserts object at place n, first node of list is n = 0 
    //EC addN() doubly linked implementation 
    private void addN(linkedObj obj, int n){
        if (n==0) {
            addFirst(obj);
            return;
        } else if (n == size){
            addEnd(obj);
            return;
        }
        linkedObj target;
        target = get(n-1);
        obj.next = target.next;
        if (!(obj.next == null)) obj.next.last = obj;
        obj.last = target;
        target.next = obj; 
        size++;
    }

    //remove first
    //EC removeFirst() doubly linked implementation
    public void removeFirst(){
        linkedObj oldHead = this.head;
        if (!(this.head.next == null)){ 
            this.head = this.head.next;
            this.head.last = null;
            if (size == 2) this.tail = null;
        }else{
                this.head = null;
            }
        delete(oldHead);
        size--;
    }

    //remove end
    //EC removeLast() doubly linked implementation
    public void removeLast(){
        linkedObj oldTail = this.tail;
        if (size==2) {
                this.head.next = null;
                this.tail = null;
        } else if(!(this.tail.last == null)){
            this.tail = this.tail.last;
            this.tail.next = null;
        }else{
            this.tail = null;
        }
        delete(oldTail);
        size--;
    }

    //remove at object n
    //EC removeN() doubly linked implementation
    private void removeN(int n){
        if(n==0){
            removeFirst();
        }else if(n == size-1){
            removeLast();
        }else {
        linkedObj target = get(n);
        linkedObj tHead = target.last;
        linkedObj tTail = target.next;
        tHead.next = tTail;
        tTail.last = tHead;
        delete(target);
        size--; }
    }
    
    //appends to end
    public void add(linkedObj obj){
        addEnd(obj);
    }

    //public insert method
    public void insert(linkedObj obj, int n){
        addN(obj, n);
    }

    //public remove method
    public void remove(int n){
        removeN(n);
    }

    /////////////////////
    //instance methods//
    ///////////////////

    //gets object at place n, list starts at n = 0 
    //EC get() doubly linked implementation, traverses front-to-mid or back-to-mid based on n
    public linkedObj get(int n) {
        linkedObj iter;
        int i;
        if (n <= size/2){ //traversal from start to middle
            i = 0;
            iter = this.head;
            while (i < n){
            iter = iter.next;
            i++; } 
        } else { //traversal from end to middle + 1
            i = size-1;
            iter =this.tail;
            while(i > n){
                iter = iter.last; 
                i--;
            }

        }
        return iter;
    }

    //instance method of print
    public void print(){
       linkedMngr.print(this);
    }

    //instance method of print node n 
    public void print(int n){
        linkedMngr.print(this.get(n));
    }

    //instance method of size 
    public int size(){
        assert size == linkedMngr.size(this.head);
        return size;
    }

    //instance method of clear
    public void clear(){
        linkedMngr.clear(this);
    }

    //instance method of toString
    @Override
    public String toString(){
        return linkedMngr.toString(this.head);
    }

    //instance method of indexOf 
    public int indexOf(String targetFName){
        return linkedMngr.indexOf(this.head, targetFName, 0);
    }

    //instance method for contains, returns true if !(indexOf == -1) 
    public boolean contains(String targetFName){
        return !(this.indexOf(targetFName) == -1);
    }

    ///////////////////
    //Static methods//
    /////////////////

    //print linkedMngr, starts at head and prints each node until end of list is reached
    private static void print(linkedMngr obj){
        assert  obj.head != null;
        linkedObj iter = obj.head;
        int i = 0;
        System.out.println("Node #: " + i + " First Name: " + iter.fName + " Last Name: " + iter.lName + " City: " + iter.city + " Address: " + iter.address + " Phone Number: " + iter.phoneNumber);
        i++;
        while(!(iter.next == null)){
            iter = iter.next;
            System.out.println("Node #: " + i + " First Name: " + iter.fName + " Last Name: " + iter.lName + " City: " + iter.city + " Address: " + iter.address + " Phone Number: " + iter.phoneNumber);
            i++;
        }
    }

    //Print linkedObj 
    private static void print(linkedObj obj){ 
        System.out.println(obj.fName + " " + obj.lName + " " + obj.city + " " + obj.address + " " + obj.phoneNumber);
    }
    
    //recursize implementation of size when passing in a node goes from front --> end
    private static int size(linkedObj obj){
        if(obj == null){
            return 0;
        }
        return size(obj.next)+ 1;
    }

    //clear(), clears each link between nodes and clears out the head and tail of the list
    private static void clear(linkedMngr obj){
        linkedMngr.clear(obj.head);
        obj.head = null;
        obj.tail = null;
        obj.size = 0;
    }

    //recursively clear each link at in each node, should not be called directly
    private static void clear(linkedObj obj){
        if (obj == null){
            return;
        }
        clear(obj.next);
        obj.next = null;
        obj.last = null;
    }

    //deletes list links in an object 
    private static void delete(linkedObj obj){
        obj.next = null;
        obj.last = null;
    }

    //to string, builds a string recursively with each node mapped out from front to back 
    private static String toString(linkedObj obj){
        if (obj == null){
            return "null";
        }
        return obj.fName + " <--> " + toString(obj.next);
    }

    //index of returns the index of an object based on it's first name value, traverses from front to back and returns first match
    private static int indexOf(linkedObj obj, String target, int index){
        if(obj == null){
            return -1; 
        }
        if(obj.fName.equals(target)){
            return index;
        }
        return indexOf(obj.next, target, index +1); 
    }
}
