package main;

import management.TaskManager;
import tasks.PriorityTask;
import tasks.Task;
import tasks.TimedTask;

import java.time.LocalDateTime;

public class Main {
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

        System.out.println("To Do: \n" + taskManager.getTasksByStatus(false));
        System.out.println("Completed \n" + taskManager.getTasksByStatus(true));

    }
}
