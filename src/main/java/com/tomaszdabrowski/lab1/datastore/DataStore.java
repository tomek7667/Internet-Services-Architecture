package com.tomaszdabrowski.lab1.datastore;

import org.springframework.stereotype.Component;
import com.tomaszdabrowski.lab1.ctftask.model.Task;
import com.tomaszdabrowski.lab1.ctftask.model.Category;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Optional;

@Component
public class DataStore {
    private Set<Task> tasks = new HashSet<>();
    private Set<Category> categories = new HashSet<>();

    public Task saveTask(Task task) {
        task.setId(this.nextTaskId());
        tasks.add(task);
        return task;
    }

    public Category saveCategory(Category category) {
        category.setId(this.nextCategoryId());
        categories.add(category);
        return category;
    }

    public void deleteTaskById(Long id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public void deleteCategoryById(Long id) {
        categories.removeIf(category -> category.getId().equals(id));
    }

    public Optional<Task> findOneTaskById(Long id) {
        return tasks.stream().filter(task -> task.getId().equals(id)).findFirst();
    }

    public Optional<Category> findOneCategoryById(Long id) {
        return categories.stream().filter(category -> category.getId().equals(id)).findFirst();
    }

    public List<Task> findManyTasks() {
        return List.copyOf(tasks);
    }

    public List<Task> findManyTasksByCategoryId(Long categoryId) {
        return List.copyOf(tasks);
    }

    public List<Category> findManyCategories() {
        return List.copyOf(categories);
    }

    private Long nextTaskId() {
        Long maxId = 0L;
        for (Task task : tasks) {
            if (task.getId() > maxId) {
                maxId = task.getId();
            }
        }
        return maxId + 1;
    }

    private Long nextCategoryId() {
        Long maxId = 0L;
        for (Category category : categories) {
            if (category.getId() > maxId) {
                maxId = category.getId();
            }
        }
        return maxId + 1;
    }

    public void clear() {
        tasks.clear();
        categories.clear();
    }
}
