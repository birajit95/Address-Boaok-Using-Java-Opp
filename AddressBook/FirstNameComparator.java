import java.util.Comparator;
public class FirstNameComparator implements Comparator<AddressBook>{
    public int compare(AddressBook record1, AddressBook record2){
         return record1.getPerson().firstName().compareTo(record2.getPerson().firstName());
    }
  }