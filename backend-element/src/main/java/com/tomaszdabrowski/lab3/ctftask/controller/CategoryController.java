package com.tomaszdabrowski.lab3.ctftask.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.tomaszdabrowski.lab3.ctftask.dto.GetTasksDto;
import com.tomaszdabrowski.lab3.ctftask.event.dto.CreateCategoryDto;
import com.tomaszdabrowski.lab3.ctftask.model.Category;
import com.tomaszdabrowski.lab3.ctftask.service.CategoryService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

  private CategoryService categoryService;

  @Autowired
  public CategoryController(CategoryService categoryService) {
    this.categoryService = categoryService;
  }

  @GetMapping("{id}/tasks")
  public ResponseEntity<GetTasksDto> getTasks(@PathVariable("id") UUID id) {
    Optional<Category> category = categoryService.findOne(id);
    if (category.isPresent()) {
      return ResponseEntity.ok(
        GetTasksDto.entityToDtoMapper().apply(category.get().getTasks())
      );
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Void> deleteCategory(@PathVariable("id") UUID id) {
    Optional<Category> category = categoryService.findOne(id);
    if (category.isPresent()) {
      categoryService.deleteOne(id);
      return ResponseEntity.accepted().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PostMapping
  public ResponseEntity<Void> postCategory(
    @RequestBody JsonNode body,
    UriComponentsBuilder builder
  ) {
    CreateCategoryDto createCategoryDto = CreateCategoryDto
      .jsonToDtoMapper()
      .apply(body);
    Category category = CreateCategoryDto
      .dtoToEntityMapper()
      .apply(createCategoryDto);
    categoryService.createOne(category);
    return ResponseEntity
      .created(
        builder
          .pathSegment("api", "v1", "categories", "{id}")
          .buildAndExpand(category.getId())
          .toUri()
      )
      .build();
  }
}
