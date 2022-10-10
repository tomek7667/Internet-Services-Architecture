package com.tomaszdabrowski.lab1.ctftask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomaszdabrowski.lab1.ctftask.model.Task;
import com.tomaszdabrowski.lab1.ctftask.repository.inmemory.TaskRepositoryInterface;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private TaskRepositoryInterface taskRepository;

    @Autowired
    public TaskService(TaskRepositoryInterface taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Task> findOne(Long id) {
        return taskRepository.findById(id);
    }

    public List<Task> findMany() {
        return taskRepository.findMany();
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
}