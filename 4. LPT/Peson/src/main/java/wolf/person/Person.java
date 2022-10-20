package wolf.person;

import java.security.PublicKey;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Person {

    private final Calendar birthDay;

    public Calendar getBirthDay() {
        return birthDay;
    }

    public Person(Calendar birthDay) {
        this.birthDay = birthDay;
    }

    public Person(int year, int month, int day) {
        this(new GregorianCalendar(year, month + 1, day));
    }


    @Override
    public String toString() {
        return toString("dd-MM-yyyy");
    }

    public String toString(String pattern) {
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        StringBuffer stringBuffer = dateFormat.format(birthDay.getTime(), new StringBuffer(), new FieldPosition(1));
        return new String(stringBuffer);
    }




}
