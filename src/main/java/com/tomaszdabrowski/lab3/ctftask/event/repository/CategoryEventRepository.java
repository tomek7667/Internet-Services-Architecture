package com.tomaszdabrowski.lab3.ctftask.event.repository;

import com.fasterxml.jackson.databind.JsonNode;
import com.tomaszdabrowski.lab3.ctftask.event.dto.CreateCategoryDto;
import com.tomaszdabrowski.lab3.ctftask.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

@Repository
public class CategoryEventRepository {

  private RestTemplate restTemplate;

  @Autowired
  public CategoryEventRepository(@Value("${ctftask.task.url}") String baseUrl) {
    restTemplate = new RestTemplateBuilder().rootUri(baseUrl).build();
  }

  public void delete(Category category) {
    restTemplate.delete("/categories/{id}", category.getId());
  }

  public void create(Category category) {
    CreateCategoryDto dto = CreateCategoryDto
      .entityToDtoMapper()
      .apply(category);
    HttpEntity<CreateCategoryDto> request = new HttpEntity<>(dto);
    restTemplate.postForEntity("/categories", request, JsonNode.class);
  }
}
