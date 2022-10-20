package wolf.person;

import java.text.FieldPosition;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.InputMismatchException;
import java.util.TimeZone;


public class Main {

    public static void main(String[] args) {
        Person vadim = new Person(2002, Calendar.OCTOBER, 10);
        Person mike = new Person(new GregorianCalendar(1996, Calendar.MAY, 1, 12, 32));


        System.out.println(vadim.toString("dd-MM-yyyy"));
        System.out.println(mike.toString("dd-MM-yyyy"));
        System.out.println(mike.toString("yyyy.MM.dd 'at' HH:mm z"));
    }

}
