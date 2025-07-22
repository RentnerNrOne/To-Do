package time;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class TimeImpl implements Time {
    private boolean isOverdue;


    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    public boolean isTimeOver(String timeString) {
        if (timeString == null || timeString.isBlank()) {
            System.err.println("Fehler: Zeit-String ist null oder leer.");
            return false;
        }
        
        try {
            
        	LocalDateTime parsedDate = LocalDateTime.parse(timeString, FORMATTER);
        	System.out.println(parsedDate);
        	LocalDateTime overdueDate = parsedDate.plusMinutes(5);
        	System.out.println(overdueDate);
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
