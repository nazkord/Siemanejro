package json;

import java.time.LocalDateTime;

public class UtcConverter {
    public static LocalDateTime utcConverter(String utcDate){
        int year = Integer.parseInt(utcDate.substring(0,4));
        int month = Integer.parseInt(utcDate.substring(5,7));
        int day = Integer.parseInt(utcDate.substring(8,10));
        int hour = Integer.parseInt(utcDate.substring(11,13));
        int minute = Integer.parseInt(utcDate.substring(14,16));
        LocalDateTime localDateTime = LocalDateTime.of(year,month,day,hour,minute);
        return localDateTime;
    }
}
