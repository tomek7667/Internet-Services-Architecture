package com.tomaszdabrowski.lab3.ctftask.event.dto;

import com.fasterxml.jackson.databind.JsonNode;
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

  private UUID id;

  public static Function<CreateCategoryDto, Category> dtoToEntityMapper() {
    return request -> Category.builder().id(request.getId()).build();
  }

  public static Function<JsonNode, CreateCategoryDto> jsonToDtoMapper() {
    return json ->
      CreateCategoryDto
        .builder()
        .id(UUID.fromString(json.get("category_id").asText()))
        .build();
  }
}
