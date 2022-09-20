package wolf;

import java.util.Calendar;
import java.util.Vector;

public class Person {

    private String firsName;
    private String lastName;
    private Calendar birthDay;
    private Address address;

    public Person(String firsName, String lastName, Calendar birthDay, Address address) {
        this.firsName = firsName;
        this.lastName = lastName;
        this.birthDay = birthDay;
        this.address = address;
    }
    
    public Person(String firsName, String lastName, int year, int month, int day, Address address) {
        this(firsName, lastName, Calendar.getInstance(), address);
        this.birthDay.set(year, month - 1, day);
    }

    public String getFirsName() {
        return firsName;
    }

    public void setFirsName(String firsName) {
        this.firsName = firsName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }


}
