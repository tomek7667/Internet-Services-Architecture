package com.tomaszdabrowski.lab1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tomaszdabrowski.lab1.ctftask.service.TaskService;
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

    @Autowired
    public CommandLine(CategoryService categoryService, TaskService taskService) {
        this.categoryService = categoryService;
        this.taskService = taskService;
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
            printSpacer();
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
                    System.out.println("Task created successfully.");
                    break;
                case "2":
                    System.out.println("Enter category name:");
                    String categoryName = scanner.nextLine();
                    System.out.println("Enter category description:");
                    String categoryDescription = scanner.nextLine();
                    Category category = Category.builder()
                            .name(categoryName)
                            .description(categoryDescription)
                            .build();
                    categoryService.createOne(category);
                    System.out.println("Category created successfully.");
                    break;
                case "3":
                    System.out.println("Enter task id:");
                    Long taskId = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Task> taskToDelete = taskService.findOne(taskId);
                    if (!taskToDelete.isPresent()) {
                        System.out.println("ERR: Task with id " + taskId + " does not exist.");
                    } else {
                        taskService.deleteOne(taskId);
                        System.out.println("Task deleted successfully.");
                    }
                    break;
                case "4":
                    System.out.println("Enter category id:");
                    Long categoryIdToDelete = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Category> categoryToDelete = categoryService.findOne(categoryIdToDelete);
                    if (!categoryToDelete.isPresent()) {
                        System.out.println("ERR: Category with id " + categoryIdToDelete + " does not exist.");
                    } else {
                        categoryService.deleteOne(categoryIdToDelete);
                        System.out.println("Category deleted.");
                    }
                    break;
                case "5":
                    System.out.println("All of the tasks:");
                    taskService.findMany().forEach(System.out::println);
                    break;
                case "6":
                    System.out.println("All of the categories:");
                    categoryService.findMany().forEach(System.out::println);
                    break;
                case "7":
                    System.out.println("Enter task id to find:");
                    Long taskIdToFind = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Task> taskToFind = taskService.findOne(taskIdToFind);
                    if (!taskToFind.isPresent()) {
                        System.out.println("ERR: Task with id " + taskIdToFind + " does not exist.");
                        break;
                    }
                    System.out.println(taskToFind.get());
                    break;
                case "8":
                    System.out.println("Enter category id to find:");
                    Long categoryIdToFind = scanner.nextLong();
                    scanner.nextLine();
                    Optional<Category> categoryToFind = categoryService.findOne(categoryIdToFind);
                    if (!categoryToFind.isPresent()) {
                        System.out.println("ERR: Category with id " + categoryIdToFind + " does not exist.");
                    }
                    System.out.println(categoryToFind.get());
                    break;
                case "m":
                    printMenu();
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
        System.out.println("7. Find task by id");
        System.out.println("8. Find category by id");
        System.out.println("'m' - show this menu");
        System.out.println("'q' - Exit");
    }
}
