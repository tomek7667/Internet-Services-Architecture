package com.tomaszdabrowski.lab3.ctftask.dto;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import java.util.UUID;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCategoryDto {

  private UUID id;
  private String name;
  private String description;

  public static Function<Category, GetCategoryDto> entityToDtoMapper() {
    return category ->
      GetCategoryDto
        .builder()
        .id(category.getId())
        .name(category.getName())
        .description(category.getDescription())
        .build();
  }
}
