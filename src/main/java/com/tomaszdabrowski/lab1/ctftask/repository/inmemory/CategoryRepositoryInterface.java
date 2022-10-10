package com.tomaszdabrowski.lab1.ctftask.repository.inmemory;

import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.repository.inmemory.InMemoryRepositoryInterface;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Repository
public interface CategoryRepositoryInterface extends InMemoryRepositoryInterface<Category, Long> {
    Optional<Category> findById(Long id);
    List<Category> findMany();
    Category save(Category category);
    void deleteById(Long id);
}
