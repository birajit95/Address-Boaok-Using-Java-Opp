package com.addressbook;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class AddressBookList{
    
    Scanner nameSc = new Scanner(System.in);
    Scanner phoneSc = new Scanner(System.in);
    Scanner addressSc = new Scanner(System.in);
    private String AddressBookName;
    static final HashMap<String, AddressBookList> bookList = new HashMap<String, AddressBookList>();
    final ArrayList<AddressBook> addBookList = new ArrayList<>();
    
    public AddressBookList(String addressBookKey){
            this.AddressBookName = addressBookKey;
    }
    public static void welcomeMessage(){
            System.out.println(" Welcome to Address Book--The time is "+LocalTime.now());
    }
    
    public static void addNewAddressBook(String addressBookKey, AddressBookList list){
            AddressBookList.bookList.put(addressBookKey, list);
           
    }
    
    public static boolean  ShowAllAddressBooks(){
          boolean flag = false;
          for(Map.Entry<String, AddressBookList> entryObj : bookList.entrySet()){
            System.out.println(entryObj.getKey());
            flag = true;
          }
          return flag;
    }
    public static void deleteAddressBook(String key){
          bookList.remove(key);
    }
    
    public static AddressBookList getAddressBook(String addbookName) {    
    	return bookList.get(addbookName);           
      }
    
    public void AddRecord(AddressBook addressBook){
           this.addBookList.add(addressBook);
    }
  
    public void updateRecord(){
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

 
  public void personDetails(){
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
  
  public void showAllRecords(){
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

  public void deleteRecord(){
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


  public void sortByFirstName(){
    Collections.sort(addBookList, new FirstNameComparator());
    System.out.println("\n----List Sorted by First Name-----\n");
    this.showAllRecords();

  }
  public void sortByLastName(){
    Collections.sort(addBookList, new LastNameComparator());
    System.out.println("\n----List Sorted by Last Name-----\n");
    this.showAllRecords();
  }

  public void sortById(){
    Collections.sort(addBookList, new RecordIdComparator());
    System.out.println("\n----List Sorted by Record Id-----\n");
    this.showAllRecords();
  }

  @Override
  public String toString(){
    return this.AddressBookName;
  }
   public static void main(String []args){

    welcomeMessage();

    Scanner inputResponse=new Scanner(System.in);
    while(true){
              System.out.println("1. Add new AddreesBook");
              System.out.println("2. Delete AddreesBook");
              System.out.println("3. Goto AddreesBook");
              System.out.print("Enter Your Option: ");

              Scanner inputRes = new Scanner(System.in);
              int chooseSc = inputRes.nextInt();
              AddressBookList bookList = null;
              boolean flag = false;
              if(chooseSc == 1){
                flag = true;
                System.out.print("\n\nEnter the Address Book Name: ");
                String AddBookNameSc = inputRes.next();
                bookList = new AddressBookList(AddBookNameSc);
                AddressBookList.addNewAddressBook(AddBookNameSc, bookList);
              }
              else if(chooseSc == 2){
            	AddressBookList.ShowAllAddressBooks() ; 
                System.out.print("\n\nEnter the Address Book Name: ");
                String AddBookNameSc = inputRes.next();
                AddressBookList.deleteAddressBook(AddBookNameSc);
              }
              else if(chooseSc == 3){
                if(AddressBookList.ShowAllAddressBooks()){
                    System.out.print("\n\nEnter the Address Book Name: ");
                    String AddBookNameSc = inputRes.next();
                    bookList = AddressBookList.getAddressBook(AddBookNameSc);
                    if(bookList != null){
                          flag = true;
                    }
                    else{
                      System.out.println("\nSuch AddressBook Not found! Try again!\n");
                    }
                  }
                  else{
                    System.out.println("\nNo Address Book Yet!\n");
                  }    

              }
              else{
                System.out.print("\nOpps! Wrong input! Enter again!\n");
              }

              boolean listFlag = false;
              while(flag){
                if(listFlag)
                    break;
                System.out.println("\n\nAddress Book Name: "+bookList);
                System.out.println("\n1. Add Record");
                System.out.println("2. Update Record");
                System.out.println("3. Delete Record");
                System.out.println("4. Person Details");
                System.out.println("5. All Records ");
                System.out.println("6. Sort By First Name ");
                System.out.println("7. Sort By Last Name ");
                System.out.println("8. Sort By Record ID");
                System.out.println("9. <-- Back");
                System.out.println("10. Exit ");

                System.out.print("Choose Your Option: ");
                int response = inputResponse.nextInt();
                System.out.print("\n\n");
                

                switch(response){
                  case 1:
                        AddressBook addressbook = new AddressBook();
                        addressbook.addRecord(inputResponse);
                        bookList.AddRecord(addressbook);
                        break;
                  case 2:
                        bookList.updateRecord();
                        break;
                  case 3:
                        bookList.deleteRecord();
                        break;         
                  case 4:
                        bookList.personDetails(); 
                        break;    
                  case 5:    
                        bookList.showAllRecords();
                        break;
                  case 6:
                        bookList.sortByFirstName();
                        break; 
                  case 7:
                        bookList.sortByLastName();
                        break; 
                  case 8:
                        bookList.sortById();
                        break;       
                  case 9:
                        listFlag = true;
                        break;
                  case 10:
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
}



