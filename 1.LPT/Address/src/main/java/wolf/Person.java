package wolf;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Vector;

public class Person {

    private String name;
    private String surname;
    private Calendar birthDay;
    private Address address;



    /////////////////////////////////////////////////////////
    public Person(@NotNull String name, @NotNull String surname, @NotNull Calendar birthDay, @NotNull Address address) {
        this.name = name;
        this.surname = surname;
        this.birthDay = birthDay;
        this.address = address;
    }

    public Person(@NotNull String name, @NotNull String surname, int year, int month, int day, @NotNull Address address) {
        this(name, surname, Calendar.getInstance(), address);
        this.birthDay.set(year, month - 1, day);

    }

    /////////////////////////////////////////////////////////
    public String getName() {
        return surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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
    /////////////////////////////////////////////////////////

    public static Person searchLastName(Person[] people, String surname) {
        for(Person person : people) {
            if(person.surname.equals(surname)) {
                return person;
            }
        }
        return null;
    }


    @Override
    public String toString() {
        return name + " " + surname;
    }






}
