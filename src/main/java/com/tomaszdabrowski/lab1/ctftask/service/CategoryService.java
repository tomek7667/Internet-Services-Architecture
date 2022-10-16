package com.tomaszdabrowski.lab1.ctftask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.repository.h2.CategoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryService {
    private CategoryRepository categoryRepository;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findOne(UUID id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findMany() {
        return categoryRepository.findAll();
    }

    public Category createOne(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateOne(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteOne(UUID id) {
        categoryRepository.deleteById(id);
    }

    public List<Category> findManyByName(String name) {
        return categoryRepository.findByName(name);
    }
}
