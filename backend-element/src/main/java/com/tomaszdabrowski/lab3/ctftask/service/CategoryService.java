package com.tomaszdabrowski.lab3.ctftask.service;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import com.tomaszdabrowski.lab3.ctftask.repository.h2.CategoryRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
