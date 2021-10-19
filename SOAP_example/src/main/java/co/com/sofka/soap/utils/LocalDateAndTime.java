package co.com.sofka.soap.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalDateAndTime {
    private LocalDateAndTime() {
    }

    public static String getLocalTime(String format){
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(format);
        LocalDateTime dateTime = LocalDateTime.now();
        LocalTime currentTime = dateTime.toLocalTime();

        return dateTimeFormatter.format(currentTime);
    }

    public static String getLocalDate(String format){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        return date.format(formatter);
    }
}
