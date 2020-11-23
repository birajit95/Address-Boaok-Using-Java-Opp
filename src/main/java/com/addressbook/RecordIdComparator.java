package com.addressbook;

import java.util.Comparator;

public class RecordIdComparator implements Comparator<AddressBook>{
  public int compare(AddressBook record1, AddressBook record2){
    return record1.getRecordId()-record2.getRecordId();
  }
}
