package codesample.java_se_api.time;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;

/**
 * Shows time package examples
 */
class TimeExample {
    public static void main(String[] args) {

        /* LocalDate, LocalTime and LocalDateTime classes all use static fabric methods to get new instances
        * All this classes are immutable */
        LocalDate date = LocalDate.now();
        System.out.println(date);

        LocalTime time = LocalTime.now();
        System.out.println(time);

        LocalDateTime dateTime = time.atDate(date);
        System.out.println(dateTime);

        /* You can print date in any of predefined formats */
        System.out.println(dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT)));

        /* Or even use your own format */
        System.out.println(dateTime.format(DateTimeFormatter.ofPattern("d MMMM YYYY ")));

    }

}
