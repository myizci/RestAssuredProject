package pojo;

// create spartan objects to be added to spartan app with three fields
//encapsulated fields
//no argument constructor
//Optionally
// we will add all constructor for creating object in one shot
public class Spartan {

    private String name;
    private String gender;
    private long phone;

    public Spartan() {

    }

    public Spartan(String name, String gender, long phone) {
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
