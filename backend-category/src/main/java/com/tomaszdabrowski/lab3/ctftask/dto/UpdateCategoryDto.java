package com.tomaszdabrowski.lab3.ctftask.dto;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import java.util.Optional;
import java.util.function.BiFunction;
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
public class UpdateCategoryDto {

  private Optional<String> name;
  private Optional<String> description;

  public static BiFunction<Category, UpdateCategoryDto, Category> dtoToEntityUpdater() {
    return (category, dto) -> {
      dto.getName().ifPresent(category::setName);
      dto.getDescription().ifPresent(category::setDescription);
      return category;
    };
  }
}
