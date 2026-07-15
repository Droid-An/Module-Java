package tasks;

public class PriorityTask extends Task {
    private int priority;

    public PriorityTask(String description, int priority) {
        super(description);
        this.priority = priority;
    }
}
