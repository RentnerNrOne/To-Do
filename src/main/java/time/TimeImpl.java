package time;
import java.time.LocalDateTime;

public class TimeImpl implements Time {
	private boolean isOverdue;
    
    
	public boolean isTimeOver(LocalDateTime date) {
		LocalDateTime overdueDate = date.plusMinutes(1);
	    if (LocalDateTime.now().isAfter(overdueDate)) {
	    	isOverdue = true;
	    }return isOverdue;
	}
}
