package management;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages an Array of 10 tasks
 */
final public class TaskManager {
    private static final Task[] tasks = new Task[10];

    private int taskCount = 0;

    public void addTask(Task task) {

        tasks[taskCount] = task;

        taskCount++;
        if (taskCount > 9) {
            taskCount = 0;
        }

    }

    public void completeTask(int id) {
        for (Task task : tasks) {

            if (task != null) {
                if (task.getId() == id) {
                    task.completeTask();
                }
            }

        }
    }

    /**
     * Print task info of tasks with certain "completed" status
     * @param completed
     * @return description, additional parameters
     */
    public Task[] getTasksByStatus(boolean completed) {
        List<Task> filteredList = new ArrayList<>();
        for (Task task : tasks) {
            if (task != null) {
                if (task.isCompleted() == completed) {
                    filteredList.add(task);
                }
            }
        }
        Task[] filteredArray = new Task[filteredList.size()];
        for (int i = 0; i < filteredList.size(); i++) {
            filteredArray[i] = filteredList.get(i);
        }
        return filteredArray;
    }
}
