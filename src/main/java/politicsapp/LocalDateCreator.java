package politicsapp;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateCreator {
    public static LocalDate fromString(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(date, formatter);
    }
}
