package com.tomaszdabrowski.lab3.ctftask.dto;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
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
public class CreateCategoryDto {

  private String name;
  private String description;

  public static Function<CreateCategoryDto, Category> dtoToEntityMapper() {
    return dto ->
      Category
        .builder()
        .name(dto.getName())
        .description(dto.getDescription())
        .build();
  }
}
