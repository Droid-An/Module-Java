package main;

import management.TaskManager;
import tasks.PriorityTask;
import tasks.Task;
import tasks.TimedTask;

import java.time.LocalDateTime;

public class Main {

    private static void logTasks(Task[] tasks) {
        for (Task task : tasks) {
            System.out.println(task.getStatus());
        }
    }

    private static void showTasksByStatus(Task[] toDo, Task[] completed) {
        System.out.println("To Do:");
        logTasks(toDo);
        System.out.println("Completed:");
        logTasks(completed);
    }

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task eat = new Task("eat");
        taskManager.addTask(eat);

        LocalDateTime timeNow = LocalDateTime.now();
        TimedTask sleep = new TimedTask("sleep", timeNow);
        taskManager.addTask(sleep);

        PriorityTask prioritisedTask = new PriorityTask("repeat", 2);
        taskManager.addTask(prioritisedTask);

        taskManager.completeTask(eat.getId());
        taskManager.completeTask(sleep.getId());

        Task[] toDo = taskManager.getTasksByStatus(false);
        Task[] completed = taskManager.getTasksByStatus(true);

        showTasksByStatus(toDo, completed);


    }
}
