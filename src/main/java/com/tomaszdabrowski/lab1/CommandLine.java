package com.tomaszdabrowski.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tomaszdabrowski.lab1.ctftask.service.TaskService;
import com.tomaszdabrowski.lab1.datastore.DataStore;
import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.model.Task;
import com.tomaszdabrowski.lab1.ctftask.service.CategoryService;

import java.util.Optional;
import java.util.Scanner;

/**
 * Component for interaction with user using command line. Components annotated
 * with {@link @Component} implementing
 * {@link CommandLineRunner} are executed automatically.
 */
@Component
public class CommandLine implements CommandLineRunner {

    private CategoryService categoryService;
    private TaskService taskService;
    private DataStore store;

    @Autowired
    public CommandLine(CategoryService categoryService, TaskService taskService, DataStore store) {
        this.categoryService = categoryService;
        this.taskService = taskService;
        this.store = store;
    }

    @Override
    public void run(String... args) throws Exception {
        printSpacer();
        System.out.println("All of the tasks:");
        printSpacer();
        taskService.findMany().forEach(System.out::println);
        printSpacer();
        System.out.println("All of the categories:");
        printSpacer();
        categoryService.findMany().forEach(System.out::println);
        printSpacer();
        printMenu();
        printSpacer();
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String input = scanner.nextLine();
            System.out.println("You entered: " + input);
            switch (input) {
                case "1":
                    System.out.println("Enter task name:");
                    String taskName = scanner.nextLine();
                    System.out.println("Enter task description:");
                    String taskDescription = scanner.nextLine();
                    System.out.println("Enter task category id:");
                    Long categoryId = scanner.nextLong();
                    // Verify whether the category exists
                    Optional<Category> taskCategory = categoryService.findOne(categoryId);
                    if (!taskCategory.isPresent()) {
                        System.out.println("ERR: Category with id " + categoryId + " does not exist.");
                        break;
                    }
                    scanner.nextLine();
                    System.out.println("Enter task points:");
                    Integer taskPoints = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("And finally, enter task flag:");
                    String taskFlag = scanner.nextLine();
                    Task task = Task.builder()
                            .name(taskName)
                            .description(taskDescription)
                            .category(taskCategory.get())
                            .points(taskPoints)
                            .flag(taskFlag)
                            .build();
                    taskService.createOne(task);
                    break;
                case "2":
                    System.out.println("Enter category name:");
                    String categoryName = scanner.nextLine();
                    System.out.println("Enter category description:");
                    String categoryDescription = scanner.nextLine();
                    Category category = Category.builder()
                            .id(this.store.nextCategoryId())
                            .name(categoryName)
                            .description(categoryDescription)
                            .build();
                    categoryService.createOne(category);
                    System.out.println("Category created.");
                    break;
                case "3":
                    System.out.println("Enter task id:");
                    Long taskId = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Task> taskToDelete = taskService.findOne(taskId);
                    if (!taskToDelete.isPresent()) {
                        System.out.println("ERR: Task with id " + taskId + " does not exist.");
                        break;
                    }
                    taskService.deleteOne(taskId);
                    System.out.println("Task deleted.");
                    break;
                case "4":
                    System.out.println("Enter category id:");
                    Long categoryIdToDelete = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Category> categoryToDelete = categoryService.findOne(categoryIdToDelete);
                    if (!categoryToDelete.isPresent()) {
                        System.out.println("ERR: Category with id " + categoryIdToDelete + " does not exist.");
                        break;
                    }
                    categoryService.deleteOne(categoryIdToDelete);
                    System.out.println("Category deleted.");
                    break;
                case "5":
                    printSpacer();
                    System.out.println("All of the tasks:");
                    printSpacer();
                    taskService.findMany().forEach(System.out::println);
                    printSpacer();
                    break;
                case "6":
                    printSpacer();
                    System.out.println("All of the categories:");
                    printSpacer();
                    categoryService.findMany().forEach(System.out::println);
                    printSpacer();
                    break;
                case "m":
                    printSpacer();
                    printMenu();
                    printSpacer();
                    break;
                case "q":
                    scanner.close();
                    System.out.println("Bye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid command");
                    break;
            }
            printSpacer();
            printSpacer();
            printMenu();
            printSpacer();
        }
        return;
    }

    private void printSpacer() {
        System.out.println("-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-");
    }

    private void printMenu() {
        System.out.println("1. Add new task");
        System.out.println("2. Add new category");
        System.out.println("3. Delete task");
        System.out.println("4. Delete category");
        System.out.println("5. Show all tasks");
        System.out.println("6. Show all categories");
        System.out.println("'m' - show this menu");
        System.out.println("'q' - Exit");
    }
}
