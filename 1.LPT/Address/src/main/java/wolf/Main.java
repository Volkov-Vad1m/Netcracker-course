package wolf;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Person[] people = new Person[5];
        //
        people[0] = new Person("Vadim", "Volkov",
                new GregorianCalendar(2002, Calendar.OCTOBER, 10),
                new Address("Russia", "Livny", "Mira", "200"));

        //// same street
        people[1] = new Person("Mark", "Petrov",
                new GregorianCalendar(2001, Calendar.APRIL, 21),
                new Address("Russia", "Moscow", "Arbatskaya", "17"));

        people[2] = new Person("Eugene", "Ivanov",
                new GregorianCalendar(1998, Calendar.MAY, 1),
                new Address("Russia", "Moscow", "Arbatskaya", "16"));
        /////////////////////////

        //the oldest
        people[3] = new Person("Ivan", "Spinov",
                new GregorianCalendar(1966, Calendar.JANUARY, 29),
                new Address("Russia", "Saint-Petersburg", "Dumskaya", "99"));

        people[4] = new Person("Ivan", "Spinov",
                new GregorianCalendar(2011, Calendar.JANUARY, 29),
                new Address("Russia", "Saint-Petersburg", "Dumskaya", "99"));

        searchSurname(people, "Volkov");
        searchAddress(people, new Address("Russia", "Moscow", "Arbatskaya", "17"));
        dumpBetweenDates(people, new GregorianCalendar(1997, Calendar.JANUARY, 1), new GregorianCalendar(2010, Calendar.JANUARY, 1));
        searchOldest(people);
        searchSameStreets(people);
    }


    public static void searchSurname(Person[] people, String surname) {
        System.out.println("---------searching surname---------");
        for(Person person : people) {
            if(person.getSurname().equals(surname)) {
                System.out.println(person);
            }
        }
    }

    public static void searchAddress(Person[] people, Address address) {
        System.out.println("---------searching address---------");
        for(Person person : people) {
            if(person.getAddress().equals(address)) {
                System.out.println(person);
            }
        }
    }

    public static void dumpBetweenDates(Person[] people, Calendar calendar1, Calendar calendar2) {
        System.out.println("---------between dates---------");
        for(Person person : people) {
            if( person.getBirthDay().compareTo(calendar1) > 0 && person.getBirthDay().compareTo(calendar2) < 0) {
                System.out.println(person);
            }
        }
    }

    public static void searchOldest(Person[] people) {
        System.out.println("---------the oldest---------");
        Person oldest = people[0];
        for(Person person : people) {
            if(oldest.getBirthDay().compareTo(person.getBirthDay()) < 0) {
                oldest = person;
            }
        }
        System.out.println(oldest);
    }

    public static void searchSameStreets(Person[] people) {
        System.out.println("---------same streets---------");
        for(int i = 0; i < people.length; i++) {
            for (int j = i + 1; j < people.length; j++) {
                if(people[i].getAddress().getStreet().
                        equals(people[j].getAddress().getStreet())) {
                    System.out.println(people[i] + ", " + people[j]);
                }
            }

        }

    }
}
