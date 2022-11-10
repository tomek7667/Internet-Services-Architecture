package com.tomaszdabrowski.lab3.ctftask.dto;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import com.tomaszdabrowski.lab3.ctftask.model.MinifiedCategory;
import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.Singular;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetCategoriesDto {

  @Singular
  private List<MinifiedCategory> categories;

  public static Function<Collection<Category>, GetCategoriesDto> entityToDtoMapper() {
    return categories -> {
      GetCategoriesDtoBuilder response = GetCategoriesDto.builder();
      categories
        .stream()
        .map(Category::asMinifiedCategory)
        .forEach(response::category);
      return response.build();
    };
  }
}
