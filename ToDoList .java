import java.util.ArrayList;
import java.util.Scanner;

class Task {
    String description;
    boolean isCompleted;

    Task(String description) {
        this.description = description;
        this.isCompleted = false;
    }

    @Override
    public String toString() {
        return (isCompleted ? "[✓] " : "[ ] ") + description;
    }
}

public class ToDoList {
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean running = true;

        System.out.println("Welcome to the To-Do List!");

        while (running) {
            System.out.println("\nMenu:");
            System.out.println("1. Add task");
            System.out.println("2. View tasks");
            System.out.println("3. Complete task");
            System.out.println("4. Remove task");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  // consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> completeTask();
                case 4 -> removeTask();
                case 5 -> {
                    System.out.println("Exiting. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice, try again.");
            }
        }

        scanner.close();
    }

    private static void addTask() {
        System.out.print("Enter task description: ");
        String description = scanner.nextLine();
        tasks.add(new Task(description));
        System.out.println("Task added.");
    }

    private static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks in the list.");
            return;
        }
        System.out.println("Your tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    private static void completeTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to mark as complete: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        Task task = tasks.get(taskNumber - 1);
        if (task.isCompleted) {
            System.out.println("Task is already completed.");
        } else {
            task.isCompleted = true;
            System.out.println("Task marked as complete.");
        }
    }

    private static void removeTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int taskNumber = scanner.nextInt();
        scanner.nextLine();

        if (taskNumber < 1 || taskNumber > tasks.size()) {
            System.out.println("Invalid task number.");
            return;
        }
        tasks.remove(taskNumber - 1);
        System.out.println("Task removed.");
    }
}
