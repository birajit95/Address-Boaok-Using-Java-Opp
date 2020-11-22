package com.addressbook;

public class Person{
    private String firstName, lastName, phoneNumber;
    private Address address;
   
  
    public Person(String firstName, String lastName){
            this.firstName=firstName;
            this.lastName=lastName;
          }
    public void setAddress(Address address){
             this.address=address;
          }
    public void setPhoneNumber(String phoneNumber){
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
  