package wolf;

import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Vector;

public class Main {

    public static void main(String[] args) {
        Calendar cl = Calendar.getInstance();
        cl.set(2002, Calendar.OCTOBER, 10);
        Calendar cl2 = Calendar.getInstance();
        cl2.set(2001, Calendar.OCTOBER, 10);
        Calendar cl3 = Calendar.getInstance();
        cl3.set(1970, Calendar.OCTOBER, 10);

        Calendar[] calendars = { cl, cl2, cl3};
        Arrays.sort(calendars);
        Vector v;
        v.contains()
        for(Calendar a: calendars) {
            System.out.println(a.getTime());
        }


    }
}
