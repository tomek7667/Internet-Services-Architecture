package com.tomaszdabrowski.lab1.ctftask.repository.inmemory;

import com.tomaszdabrowski.lab1.ctftask.model.Task;
import com.tomaszdabrowski.lab1.repository.inmemory.InMemoryRepositoryInterface;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface TaskRepositoryInterface extends InMemoryRepositoryInterface<Task, Long> {
    Optional<Task> findById(Long id);
    List<Task> findMany();
    Task save(Task task);
    void deleteById(Long id);
}
