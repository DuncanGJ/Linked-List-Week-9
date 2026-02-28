class LinkedListOutOfBoundsException extends Exception{
    public LinkedListOutOfBoundsException(String m){
        super(m);
    }
}

public class linkedMngr {
    linkedObj first;
    linkedObj last;

    //constructors
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
            target = pGet(n);
            obj.next = target.next;
            target.next = obj;
        } catch (Exception e) {
            System.out.println(e);
        } 
    }

    //public getter method 
    public linkedObj get(int n)throws LinkedListOutOfBoundsException{
        return pGet(n);
    }

    //gets object at place n, list starts at n = 0 
    private linkedObj pGet(int n) throws LinkedListOutOfBoundsException{
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

    public static void print(linkedObj obj){
        System.out.println(obj.fName + " " + obj.lName + " " + obj.city + " " + obj.address + " " + obj.phoneNumber);
    }

}