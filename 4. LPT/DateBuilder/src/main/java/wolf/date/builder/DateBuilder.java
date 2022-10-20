package wolf.date.builder;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateBuilder {

    public static Date dateBuild(int year, int month, int day, int hours, int minutes) {
        return calendarBuild(year, month, day, hours, minutes).getTime();
    }

    public static Calendar calendarBuild(int year, int month, int day, int hours, int minutes) {
        return new GregorianCalendar(year, month, day, hours, minutes);
    }
}
