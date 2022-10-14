package com.tomaszdabrowski.lab1.ctftask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomaszdabrowski.lab1.ctftask.model.Task;
import com.tomaszdabrowski.lab1.ctftask.repository.h2.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepository taskRepository;

    @Autowired
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> findOne(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findMany() {
        return taskRepository.findAll();
    }

    public Task createOne(Task task) {
        return taskRepository.save(task);
    }

    public Task updateOne(Task task) {
        return taskRepository.save(task);
    }

    public void deleteOne(Long id) {
        taskRepository.deleteById(id);
    }

    public List<Task> findManyByCategoryId(Long categoryId) {
        return taskRepository.findByCategoryId(categoryId);
    }

    public List<Task> findManyByCategoryName(String categoryName) {
        return taskRepository.findByCategoryName(categoryName);
    }
}
