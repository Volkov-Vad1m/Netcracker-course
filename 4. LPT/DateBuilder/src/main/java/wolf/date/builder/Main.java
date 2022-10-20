package wolf.date.builder;

import java.util.Calendar;

public class Main {
    public static void main(String[] args) {

        System.out.println(DateBuilder.calendarBuild(2002, Calendar.OCTOBER, 10, 8, 23).getTime());
        System.out.println(DateBuilder.dateBuild(2002, Calendar.OCTOBER, 11, 11, 11));
    }

}
