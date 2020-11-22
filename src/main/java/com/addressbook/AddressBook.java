package com.addressbook;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.*;
import java.util.Collections;

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
    
    public static String userInputValidation(String pattern , String inputTitle ){
         Scanner nameSc = new Scanner(System.in);
         String userInput;
         while(true){
                 System.out.print("Enter "+inputTitle+": ");
                 userInput = nameSc.nextLine();
                 if(Pattern.matches(pattern,String.valueOf(userInput))){
                   break;
                  }

                 else if(inputTitle.equals("Phone Number") || inputTitle.equals("ZIP")){
                        System.out.println("Opps!" + " Invalid "+ inputTitle);
                 }
                 else{
                   System.out.println("Opps! " + inputTitle + " should start with Capital letter and"+
                    " should not contain any space!");
                 }
           }
           return userInput;
    }
    public void addPerson(){
           String pattern="^[A-Z][a-zA-Z]+$";
           String firstName = AddressBook.userInputValidation(pattern,"First Name");
           String lastName = AddressBook.userInputValidation(pattern,"Last Name");
           
           Person personObj = new Person(firstName,lastName);
           this.person=personObj;
      }
    public void updatePersonName(Person personObj){

           String pattern="^[A-Z][a-zA-Z]+$";
           String firstName = AddressBook.userInputValidation(pattern,"First Name");
           String lastName = AddressBook.userInputValidation(pattern,"Last Name");
           personObj.setName(firstName,lastName);
     }
    public Person getPerson(){
         return this.person;
      }
    public int getRecordId(){
      return this.recordId;
    }  
      
    public void addPhoneNumber(Person personObj){
      String pattern="^(\\+91|91|0)?([6-9]{1}[0-9]{9})$";
      String phoneNumber = AddressBook.userInputValidation(pattern,"Phone Number");
      personObj.setPhoneNumber(phoneNumber);
    }

    public void addAddress(Person personObj){
      String pattern="^[A-Z][a-zA-Z]+$";
      String zipPattern="^[1-9]{1}[0-9]{2}(-| |)[0-9]{3}$";
      String address = AddressBook.userInputValidation(pattern,"Address");
      String city = AddressBook.userInputValidation(pattern,"City");
      String state = AddressBook.userInputValidation(pattern,"State");
      String zip = AddressBook.userInputValidation(zipPattern,"ZIP");

      Address addressObj = new Address(address,city,state,zip);
      personObj.setAddress(addressObj);
    }

    
  public void addRecord(Scanner inputResponse){
    
    this.addPerson();
    this.recordId=AddressBook.recordCounter;
    AddressBook.recordCounter++;

    System.out.print("Want to skip for now? y/n : ");
    String skipStatus = inputResponse.next();

    if(skipStatus.equals("n")){
      Person personObj = this.getPerson();
      this.addPhoneNumber(personObj);
      this.addAddress(personObj);
    }
    addBookList.add(this);

  }

  public static void updateRecord(){
      final Scanner inputResponse=new Scanner(System.in);
      System.out.print("Enter the record ID: ");
      int recordId=inputResponse.nextInt();
      boolean recordFound=false;
      for (final AddressBook addressBook : addBookList) {
           if(addressBook.getRecordId()==recordId){
             recordFound=true;
             System.out.println("Record updation for "+addressBook.getPerson().getFullName());
             System.out.print("1. Update Name \n2. Update Phone Number \n3. Update Address \n4.Update Phone and Addrees \n5.Update All");
             System.out.print("\nChoose your option: ");
            
             class SwitchCase{
               void selctOption(){
               int status=inputResponse.nextInt();
               switch(status){
                   case 1:addressBook.updatePersonName(addressBook.getPerson());break;
                   case 2:addressBook.addPhoneNumber(addressBook.getPerson());break;
                   case 3:addressBook.addAddress(addressBook.getPerson());break;
                   case 4:addressBook.addPhoneNumber(addressBook.getPerson());
                          addressBook.addAddress(addressBook.getPerson());break;      
                   case 5:addressBook.updatePersonName(addressBook.getPerson());
                          addressBook.addPhoneNumber(addressBook.getPerson());
                          addressBook.addAddress(addressBook.getPerson());break;
                   default:
                           System.out.print("Invalid ! Select again :");
                           new SwitchCase().selctOption();
                           break;       
                          }
                  }
              }
            
              new SwitchCase().selctOption();
              break;
           }
      }
      if(!recordFound){
        System.out.println("\nOpps! Record Not Found! ");
      }
  }

 
  public static void personDetails(){
    Scanner scObj = new Scanner(System.in);
    System.out.print("Enter First Name: ");
    String firstName=scObj.nextLine();
    boolean recordFound=false;
    for (AddressBook addressBook : addBookList) {
        if(addressBook.getPerson().firstName().equals(firstName)){
            recordFound=true;
            System.out.println("\nDetails of "+addressBook.getPerson().getFullName()+"\n");
          try{
            System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getPersonDetails());
            }
          catch(NullPointerException e){
            System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getFullName()+" --- "+" --- "+" --- "+" --- "+" --- ");
            }
        }
    }
    if(!recordFound){
           System.out.println("Opps! Record Not Found ");
    }      
  }
  
  public static void showAllRecords(){
    System.out.println(" - All records are listed bellow - \n");
    boolean emptyFlag=true;
    for (AddressBook addressBook : addBookList) {
        try{
        emptyFlag=false;
        System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getPersonDetails());
        }
        catch(NullPointerException e){
        System.out.println(addressBook.getRecordId()+" "+addressBook.getPerson().getFullName()+" --- "+" --- "+" --- "+" --- "+" --- ");
        }
    }
    if(emptyFlag){
      System.out.println(" Address Book is empty! ");
    }
  }

  public static void deleteRecord(){
       Scanner sc = new Scanner(System.in);
       System.out.print("Want to delete all records: 1=Yes/2=No:  ");
       int status=sc.nextInt();
       if(status==1)
       {
        System.out.print("Delete Confirm?: y/n: ");
        String confirmStatus=sc.next();
        if(confirmStatus.equals("y")){
              addBookList.clear();
              System.out.println("All records has been deleted ! ");
        }
       }
       else if(status==2){
           System.out.print("Enter the record ID: ");
           int id=sc.nextInt();
           for (AddressBook record : addBookList){
                if(record.getRecordId()==id){
                    System.out.println(record.getRecordId()+" "+record.getPerson().getFullName()); 
                    System.out.print("Delete Confirm?: y/n : ");
                    String confirmStatus=sc.next();
                    if(confirmStatus.equals("y")){
                           addBookList.remove(record);
                           System.out.println(record.getPerson().getFullName()+"'s record has been deleted ! ");
                           break;
                    }
                }
           }
         }
       else{
         System.out.println("Sorry! Could not understand your response! ");
         deleteRecord();
       }  
       
  }


  public static void sortByFirstName(){
    Collections.sort(addBookList, new FirstNameComparator());
    System.out.println("\n----List Sorted by First Name-----\n");
    AddressBook.showAllRecords();

  }
  public static void sortByLastName(){
    Collections.sort(addBookList, new LastNameComparator());
    System.out.println("\n----List Sorted by Last Name-----\n");
    AddressBook.showAllRecords();
  }

  public static void sortById(){
    Collections.sort(addBookList, new RecordIdComparator());
    System.out.println("\n----List Sorted by Record Id-----\n");
    AddressBook.showAllRecords();
  }
   public static void main(String []args){
    welcomeMessage();
    Scanner inputResponse=new Scanner(System.in);
    while(true){

      System.out.println("\n\n1. Add Record");
      System.out.println("2. Update Record");
      System.out.println("3. Delete Record");
      System.out.println("4. Person Details");
      System.out.println("5. All Records ");
      System.out.println("6. Sort By First Name ");
      System.out.println("7. Sort By Last Name ");
      System.out.println("8. Sort By Record ID");
      System.out.println("9. exit ");

      System.out.print("Choose Your Option: ");
      int response = inputResponse.nextInt();
      System.out.print("\n\n");
      
      switch(response){
        case 1:
              AddressBook addressbook = new AddressBook();
              addressbook.addRecord(inputResponse);
              break;
        case 2:
              AddressBook.updateRecord();
              break;
        case 3:
              AddressBook.deleteRecord();
              break;         
        case 4:
              AddressBook.personDetails(); 
              break;    
        case 5:    
              AddressBook.showAllRecords();
              break;
        case 6:
              AddressBook.sortByFirstName();
              break; 
        case 7:
              AddressBook.sortByLastName();
              break; 
        case 8:
              AddressBook.sortById();
              break;       
        case 9:
              inputResponse.close();
              System.exit(0);
              break;  
        default:
               System.out.println("Sorry! Could not understand. Please choose the correct option!");
               break;             

      }
    }
  }
}