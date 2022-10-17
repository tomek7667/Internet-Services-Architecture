package com.tomaszdabrowski.lab1.ctftask.controller;

import com.tomaszdabrowski.lab1.ctftask.dto.CreateCategoryDto;
import com.tomaszdabrowski.lab1.ctftask.dto.GetCategoriesDto;
import com.tomaszdabrowski.lab1.ctftask.dto.GetCategoryDto;
import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.service.CategoryService;
import com.tomaszdabrowski.lab1.ctftask.service.TaskService;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

  private TaskService taskService;
  private CategoryService categoryService;

  @Autowired
  public CategoryController(
    TaskService taskService,
    CategoryService categoryService
  ) {
    this.taskService = taskService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<GetCategoriesDto> getCategories() {
    return ResponseEntity.ok(
      GetCategoriesDto.entityToDtoMapper().apply(categoryService.findMany())
    );
  }

  @GetMapping("{categoryId}")
  public ResponseEntity<GetCategoryDto> getCategory(
    @PathVariable("categoryId") UUID categoryId
  ) {
    return categoryService
      .findOne(categoryId)
      .map(
        value ->
          ResponseEntity.ok(GetCategoryDto.entityToDtoMapper().apply(value))
      )
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<GetCategoryDto> createCategory(
    @RequestBody CreateCategoryDto createCategoryDto,
    UriComponentsBuilder builder
  ) {
    Category category = CreateCategoryDto
      .dtoToEntityMapper()
      .apply(createCategoryDto);
    category = categoryService.createOne(category);
    return ResponseEntity
      .created(
        builder
          .pathSegment("api", "v1", "categories", "{categoryId}")
          .buildAndExpand(category.getId())
          .toUri()
      )
      .body(GetCategoryDto.entityToDtoMapper().apply(category));
  }
}
