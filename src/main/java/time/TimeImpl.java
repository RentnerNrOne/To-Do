package time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeImpl implements Time {
    private boolean isOverdue;


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public boolean isTimeOver(String timeString) {
        try {
            LocalDateTime parsedDate = LocalDateTime.parse(timeString, FORMATTER);
            LocalDateTime overdueDate = parsedDate.plusMinutes(1);
            isOverdue = LocalDateTime.now().isAfter(overdueDate);
            return isOverdue;
        } catch (DateTimeParseException e) {
            System.err.println("Fehler: Ungültiges Zeitformat. Erwartet: yyyy-MM-dd HH:mm:ss");
            return false;
        }
    }

    public boolean isOverdue() {
        return isOverdue;
    }
}
