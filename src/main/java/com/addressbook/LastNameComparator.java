package com.addressbook;

import java.util.Comparator;
public class LastNameComparator implements Comparator<AddressBook>{
    public int compare(AddressBook record1, AddressBook record2){
      return record1.getPerson().lastName().compareTo(record2.getPerson().lastName());
    }
  }