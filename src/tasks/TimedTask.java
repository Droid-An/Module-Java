package tasks;

import java.time.LocalDateTime;

public class TimedTask extends Task {
    private final LocalDateTime deadline;

    public TimedTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }


}
