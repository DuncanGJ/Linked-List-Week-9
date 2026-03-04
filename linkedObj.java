public class linkedObj {
    //attributes 
    String fName; 
    String lName;
    String address; 
    String city;
    String phoneNumber;

    //linked list object
    linkedObj next;
    linkedObj last;

    //constructor
    public linkedObj(String fName, String lName, String address, String city, String phoneNumber, linkedObj next){
        this.fName = fName;
        this.lName = lName;
        this.address = address;
        this.city = city;
        this.phoneNumber = phoneNumber; 
        this.next = next;
    }

}
