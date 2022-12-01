package com.tomaszdabrowski.lab3.ctftask.dto;

import com.tomaszdabrowski.lab3.ctftask.model.Category;
import com.tomaszdabrowski.lab3.ctftask.model.Task;
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
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateTaskDto {

  private String name;
  private String categoryId;
  private String description;
  private Integer points;
  private String flag;

  public static Function<CreateTaskDto, Task> dtoToEntityMapper(
    Category category
  ) {
    return dto ->
      Task
        .builder()
        .id(UUID.randomUUID())
        .name(dto.getName())
        .category(category)
        .description(dto.getDescription())
        .points(dto.getPoints())
        .flag(dto.getFlag())
        .build();
  }
}
