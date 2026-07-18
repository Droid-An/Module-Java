package tasks;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Task with deadline
 */
public class TimedTask extends Task {
    private final LocalDateTime deadline;

    public TimedTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String getStatus(){
        return "ID: " + getId() + " | " + getDescription() + " | due by: " + deadline.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")) + " (timed task type)";
    }

}
