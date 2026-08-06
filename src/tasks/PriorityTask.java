package tasks;

/**
 * Task with priority
 */
public class PriorityTask extends Task {
    private final int priority;

    public PriorityTask(String description, int priority) {
        super(description);
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "ID: " + getId() + " | " + getDescription() + " | priority: " + priority + " (priority task type)";
    }
}
