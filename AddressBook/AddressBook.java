import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;


public class AddressBook{
    
    Scanner nameSc = new Scanner(System.in);
    Scanner phoneSc = new Scanner(System.in);
    Scanner addressSc = new Scanner(System.in);

    private Person person;
    static final ArrayList<AddressBook> addBookList = new ArrayList<>();
    private int recordId;
    private static int recordCounter=100;

  
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
    public void updatePersonName(Person personObj){
           System.out.print("Enter First Name: ");
           String firstName = nameSc.nextLine();
           System.out.print("Enter Last Name: ");
           String lastName=nameSc.nextLine();
           personObj.setName(firstName,lastName);
     }
    public Person getPerson(){
         return this.person;
      }
    public int getRecordId(){
      return this.recordId;
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

    
  public void addRecord(Scanner inputResponse){
    
    this.addPerson();
    this.recordId=AddressBook.recordCounter;
    AddressBook.recordCounter++;

    System.out.print("Want to skip for now? Y/N : ");
    String skipStatus = inputResponse.next();

    if(skipStatus.equals("N")){
      Person personObj = this.getPerson();
      this.addPhoneNumber(personObj);
      this.addAddress(personObj);
    }
    addBookList.add(this);

  }

  public static void updateRecord(){
      Scanner inputResponse=new Scanner(System.in);
      System.out.print("Enter the record ID: ");
      int recordId=inputResponse.nextInt();
      
      for (AddressBook addressBook : addBookList) {
           if(addressBook.getRecordId()==recordId){
             System.out.println("Record updation for "+addressBook.getPerson().getFullName());
             System.out.print("Need to update the name also? 1=Yes/2=No: ");
             int status=inputResponse.nextInt();
             if(status==1){
               addressBook.updatePersonName(addressBook.getPerson());
             }
             addressBook.addPhoneNumber(addressBook.getPerson());
             addressBook.addAddress(addressBook.getPerson());
             break;
           }
      }
  }

 
  public static void personDetails(){
    Scanner scObj = new Scanner(System.in);
    System.out.print("Enter First Name: ");
    String firstName=scObj.nextLine();
    for (AddressBook addressBook : addBookList) {
        if(addressBook.getPerson().firstName().equals(firstName)){
          try{
            System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getPersonDetails());
            }
          catch(NullPointerException e){
            System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getFullName()+" --- "+" --- "+" --- "+" --- "+" --- ");
            }
        }
    }

        
  }
  
  public static void showAllRecords(){
    System.out.println(" - All records are listed bellow - ");
    for (AddressBook addressBook : addBookList) {
        try{
        System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getPersonDetails());
        }
        catch(NullPointerException e){
        System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getFullName()+" --- "+" --- "+" --- "+" --- "+" --- ");
        }
    }
  }
   public static void main(String []args){
    welcomeMessage();
    Scanner inputResponse=new Scanner(System.in);
    while(true){

      System.out.println("1. Add Record");
      System.out.println("2. Update Record");
      System.out.println("3. Delete Record");
      System.out.println("4. Person Details");
      System.out.println("5. All Records ");
      System.out.println("6. exit ");

      System.out.print("Choose Your Option: ");
      int response = inputResponse.nextInt();

      switch(response){
        case 1:
              AddressBook addressbook = new AddressBook();
              addressbook.addRecord(inputResponse);
              break;
        case 2:
              AddressBook.updateRecord();
              break;       
        case 4:
              AddressBook.personDetails(); 
              break;    
        case 5:    
              AddressBook.showAllRecords();
              break;
        default:
               inputResponse.close();
               System.exit(0);
               break;

      }

      }
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
  public void setName(String firstName, String lastName) {
             this.firstName=firstName;
             this.lastName=lastName;
        }
  @Override
  public String toString(){
        return String.format(this.getFullName()+", "+this.phoneNumber+", "+this.address.getAddress());
  }

  public String getPersonDetails(){
        return this.toString();
  }

  public String getFullName(){
        return String.format(this.firstName+" "+this.lastName);
  }

  public String firstName(){
       return this.firstName;
    }

  public String lastName(){
       return this.lastName;
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


