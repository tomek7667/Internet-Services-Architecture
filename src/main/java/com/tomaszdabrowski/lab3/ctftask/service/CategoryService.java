package com.tomaszdabrowski.lab3.ctftask.service;

import com.tomaszdabrowski.lab3.ctftask.event.repository.CategoryEventRepository;
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
  private CategoryEventRepository categoryEventRepository;

  @Autowired
  public CategoryService(
    CategoryRepository categoryRepository,
    CategoryEventRepository categoryEventRepository
  ) {
    this.categoryRepository = categoryRepository;
    this.categoryEventRepository = categoryEventRepository;
  }

  public Optional<Category> findOne(UUID id) {
    return categoryRepository.findById(id);
  }

  public List<Category> findMany() {
    return categoryRepository.findAll();
  }

  public Category createOne(Category category, Boolean shouldSendEvent) {
    Category createdCategory = categoryRepository.save(category);
    if (shouldSendEvent) {
      categoryEventRepository.create(createdCategory);
    }
    return createdCategory;
  }

  public Category updateOne(Category category) {
    return categoryRepository.save(category);
  }

  public void deleteOne(Category category, Boolean shouldSendEvent) {
    if (shouldSendEvent) {
      categoryEventRepository.delete(category);
    }
    categoryRepository.deleteById(category.getId());
  }

  public List<Category> findManyByName(String name) {
    return categoryRepository.findByName(name);
  }
}
