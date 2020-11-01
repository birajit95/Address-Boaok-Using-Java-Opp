public class Address{
    private String address, city, state, zip;
    public Address(String address, String city, String state, String zip){
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
  
  