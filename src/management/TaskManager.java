package management;

import tasks.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages an Array of up to 10 tasks
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

    /**
     * Make task with specific ID ompleted
     * @param id of the task to mark as completed
     */

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
     * Return tasks based on completion status
     *  @param completed {@code true} to include only completed tasks,
     *                   {@code false} to include only incomplete tasks
     * @return array of filtered Tasks
     * @see Task
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
