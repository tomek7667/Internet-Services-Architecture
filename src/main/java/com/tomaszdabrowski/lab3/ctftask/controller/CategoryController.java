package com.tomaszdabrowski.lab3.ctftask.controller;

import com.tomaszdabrowski.lab3.ctftask.dto.CreateCategoryDto;
import com.tomaszdabrowski.lab3.ctftask.dto.GetCategoriesDto;
import com.tomaszdabrowski.lab3.ctftask.dto.GetCategoryDto;
import com.tomaszdabrowski.lab3.ctftask.dto.UpdateCategoryDto;
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
import org.springframework.web.bind.annotation.PutMapping;
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
  public ResponseEntity<Void> createCategory(
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
      .build();
  }

  @PutMapping("{categoryId}")
  public ResponseEntity<Void> updateCategory(
    @PathVariable("categoryId") UUID categoryId,
    @RequestBody UpdateCategoryDto createCategoryDto
  ) {
    Optional<Category> category = categoryService.findOne(categoryId);
    if (category.isPresent()) {
      Category updatedCategory = UpdateCategoryDto
        .dtoToEntityUpdater()
        .apply(category.get(), createCategoryDto);
      categoryService.updateOne(updatedCategory);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("{categoryId}")
  public ResponseEntity<Void> deleteCategory(
    @PathVariable("categoryId") UUID categoryId
  ) {
    Optional<Category> category = categoryService.findOne(categoryId);
    if (category.isPresent()) {
      categoryService.deleteOne(categoryId);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
