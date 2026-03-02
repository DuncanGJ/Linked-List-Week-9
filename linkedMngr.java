class LinkedListOutOfBoundsException extends Exception{
    public LinkedListOutOfBoundsException(String m){
        super(m);
    }
}

public class linkedMngr {
    linkedObj first;
    linkedObj last;

    /////////////////
    //constructors//
    ///////////////

    public linkedMngr(){
        //empty linked list
    }

    public linkedMngr(linkedObj first){
        //linked list with 1 element
        this.first = first;
        this.last = first; 
        this.first.next = null;
    }

    public linkedMngr(linkedObj first, linkedObj last){
        //linkedlist with 2 elements 
        this.first = first;
        this.last = last; 
        this.first.next = this.last;
        this.last.next = null;
    }

    //EC passing array of linked list objects into array
    public linkedMngr(linkedObj[] listArr){
        for(int i = 0; i <= listArr.length - 1; i++){
            if(i == 0 ){
                this.first = listArr[i];
                this.last = listArr[i];
                this.first.next = null;
            }
            else{
                add(listArr[i]);
            }
        }
    }
    /////////////////////////////////////
    //linked list manipulation methods//
    ///////////////////////////////////

    //appends to end
    public void add(linkedObj obj){
        this.last.next = obj;
        this.last = obj;
        this.last.next = null;
    }

    //inserts object at place n, list starts at n = 0 
    public void insert(linkedObj obj, int n){
        try {
            linkedObj target;
            target = get(n);
            obj.next = target.next;
            target.next = obj;
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

    ////////////////////////
    //instance methods//
    //////////////////////

    //gets object at place n, list starts at n = 0 
    public linkedObj get(int n) throws LinkedListOutOfBoundsException{
        linkedObj iter = this.first;
        int i = 0;
        while (!(iter == null)&& (i < n)){
            iter = iter.next;
            i++;
        }
        //checks for out of bounds 
        if (iter == null){
            throw new LinkedListOutOfBoundsException("Out of bounds traversal of linked list");
        }
            return iter;
    }


    //TODO deprecate when finished, test print method
    public void print(){
        linkedObj iter = this.first;
        int i = 0;
        System.out.println("Element #: " + i + " First Name: " + iter.fName);
        
        i++;
        while(!(iter.next == null)){
            iter = iter.next;
            System.out.println("Element #: " + i + " First Name: " + iter.fName);
            i++;
        }
    }

    //instance method of size 
    public int size(){
        return linkedMngr.size(this.first);
    }

    //instance method of clear
    public void clear(){
        linkedMngr.clear(this);
    }

    //instance method of toString
    public String toString(){
        return linkedMngr.toString(this.first);
    }

    //instance method of indexOf 
    public int indexOf(String targetFName){
        return linkedMngr.indexOf(this.first, targetFName, 0);
    }

    //instance method for contains, returns true if !(indexOf == -1) 
    public boolean contains(String targetFName){
        if(!(this.indexOf(targetFName) == -1)){
            return true;
        }else{
            return false;
        }
    }

    ///////////////////
    //Static methods//
    /////////////////
    
    //Print linkedObj 
    public static void print(linkedObj obj){ //TODO make private
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
        linkedMngr.clear(obj.first);
        obj.first = null;
        obj.last = null;
    }

    //recursively clear each link at in each node
    private static void clear(linkedObj obj){
        if (obj == null){
            return;
        }
        clear(obj.next);
        obj.next = null;
    }

    //to string, builds a string recursively with each node mapped out from front to back 
    private static String toString(linkedObj obj){
        if (obj == null){
            return "null";
        }
        return obj.fName + " --> " + toString(obj.next);
    }

    //index of returns the index of an object based on it's first name value, traverses from front to back and returns first match
    private static int indexOf(linkedObj obj, String target, int index){
        if(obj == null){
            return -1; 
        }
        if(obj.fName.matches(target)){
            return index;
        }
        return indexOf(obj.next, target, index +1); 
    }

    //TODO doubly linked list
}