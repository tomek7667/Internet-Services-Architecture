package com.tomaszdabrowski.lab1.ctftask.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.repository.inmemory.CategoryRepositoryInterface;

import java.util.List;
import java.util.Optional;


@Service
public class CategoryService {
    private CategoryRepositoryInterface categoryRepository;

    @Autowired
    public CategoryService(CategoryRepositoryInterface categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> findOne(Long id) {
        return categoryRepository.findById(id);
    }

    public List<Category> findMany() {
        return categoryRepository.findMany();
    }

    public Category createOne(Category category) {
        return categoryRepository.save(category);
    }

    public Category updateOne(Category category) {
        return categoryRepository.save(category);
    }

    public void deleteOne(Long id) {
        categoryRepository.deleteById(id);
    }
}
