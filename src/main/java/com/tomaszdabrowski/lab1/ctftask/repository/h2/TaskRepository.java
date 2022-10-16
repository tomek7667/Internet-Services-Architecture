package com.tomaszdabrowski.lab1.ctftask.repository.h2;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.tomaszdabrowski.lab1.ctftask.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, UUID> {
    List<Task> findByCategoryId(UUID categoryId);

    List<Task> findByCategoryName(String categoryName);
}
