package com.tomaszdabrowski.lab1.ctftask.repository.inmemory;

import org.springframework.beans.factory.annotation.Autowired;
import com.tomaszdabrowski.lab1.ctftask.model.Task;
import com.tomaszdabrowski.lab1.datastore.DataStore;

import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public class TaskRepository implements TaskRepositoryInterface {
    private DataStore store;

    @Autowired
    public TaskRepository(DataStore store) {
        this.store = store;
    }

    public Optional<Task> findById(Long id) {
        return store.findOneTaskById(id);
    }

    public List<Task> findMany() {
        return store.findManyTasks();
    }

    public Task save(Task task) {
        return store.saveTask(task);
    }

    public void deleteById(Long id) {
        store.deleteTaskById(id);
    }
}
