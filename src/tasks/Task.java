package tasks;

public class Task {
    private static int TaskCounter;
    private final int id;
    private boolean completed = false;
    private final String description;

    public Task(String description) {
        TaskCounter++;
        this.id = TaskCounter;
        this.description = description;
    }

    public void completeTask() {
        if (!completed) {
            this.completed = true;
        }
    }

    public int getId() {
        return this.id;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public String toString(){
        return "ID: " + getId() + " | " + getDescription() + " (default task type)";
    }
}


