import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class AddressBook{
    
    Scanner nameSc = new Scanner(System.in);
    Scanner phoneSc = new Scanner(System.in);
    Scanner addressSc = new Scanner(System.in);

    private Person person;
    static final ArrayList<AddressBook> addBookList = new ArrayList<>();

    public static void welcomeMessage(){
      System.out.println(" Welcome to Address Book--The time is "+LocalTime.now());
    }
     
    public void addPerson(){
           System.out.print("Enter First Name: ");
           String firstName = nameSc.nextLine();
           System.out.print("Enter Last Name: ");
           String lastName=nameSc.nextLine();
           
           Person personObj = new Person(firstName,lastName);
           this.person=personObj;
      }
    
      public Person getPerson(){
         return this.person;
      }
      
    public void addPhoneNumber(Person personObj){
      System.out.print("Enter Phone Number: ");
      long phoneNumber = phoneSc.nextLong();

      personObj.setPhoneNumber(phoneNumber);
    }

    public void addAddress(Person personObj){

      System.out.print("Enter Address: ");
      String address=addressSc.nextLine();

      System.out.print("Enter City: ");
      String city=addressSc.nextLine();

      System.out.print("Enter State: ");
      String state=addressSc.nextLine();

      System.out.print("Enter Zip Code: ");
      int zip=addressSc.nextInt();

      Address addressObj = new Address(address,city,state,zip);
      personObj.setAddress(addressObj);
    }

    
  
 
  public static void main(String []args){
    welcomeMessage();
    // while(true){
      
      AddressBook addressbook = new AddressBook();
      addressbook.addPerson();
      addBookList.add(addressbook);

      Person personObj = addBookList.get(0).getPerson();
      

      addBookList.get(0).addPhoneNumber(personObj);
      addBookList.get(0).addAddress(personObj);
    // }
    
    // for (AddressBook addressBook: addBookList) {

    // System.out.println(addressBook.getPerson().getPersonDetails());
      
    // }
    
    // System.out.println(addBookList.get(0).getPerson().getPersonDetails());
    

    

  }
}


class Person{
  private String firstName, lastName;
  private Address address;
  private long phoneNumber;

  public Person(String firstName, String lastName){
          this.firstName=firstName;
          this.lastName=lastName;
        }
  public void setAddress(Address address){
           this.address=address;
        }
  public void setPhoneNumber(long phoneNumber){
        this.phoneNumber=phoneNumber;
  }
  
  @Override
  public String toString(){
        return String.format(this.firstName+" "+this.lastName+", "+this.phoneNumber+", "+this.address.getAddress());
  }

  public String getPersonDetails(){
        return this.toString();
  }


}


class Address{
  private String address, city, state;
  private int zip;
  public Address(String address, String city, String state, int zip){
        this.address=address;
        this.city=city;
        this.state=state;
        this.zip=zip;
       }
  @Override
  public String toString(){
         return String.format(this.address+", "+this.city+", "+this.state+", "+this.zip);
  }     
  public String getAddress(){
         return this.toString();
  }     
}


