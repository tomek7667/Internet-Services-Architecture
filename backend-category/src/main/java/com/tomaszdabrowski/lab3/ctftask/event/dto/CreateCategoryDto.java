package com.tomaszdabrowski.lab3.ctftask.event.dto;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import java.util.UUID;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class CreateCategoryDto {

  private UUID category_id;

  public static Function<Category, CreateCategoryDto> entityToDtoMapper() {
    return category ->
      CreateCategoryDto.builder().category_id(category.getId()).build();
  }
}
