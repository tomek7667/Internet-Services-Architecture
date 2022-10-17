package com.tomaszdabrowski.lab1.ctftask.dto;

import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.model.Task;
import java.util.Optional;
import java.util.UUID;
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
public class UpdateTaskDto {

  private Optional<String> name;
  private Optional<String> description;
  private Optional<Integer> points;
  private Optional<String> flag;
  private Optional<UUID> categoryId;

  public static BiFunction<Task, UpdateTaskDto, Task> dtoToEntityUpdater(
    Optional<Category> category
  ) {
    return (task, dto) -> {
      dto.getName().ifPresent(task::setName);
      dto.getDescription().ifPresent(task::setDescription);
      dto.getPoints().ifPresent(task::setPoints);
      dto.getFlag().ifPresent(task::setFlag);
      if (category != null) {
        category.ifPresent(task::setCategory);
      }
      return task;
    };
  }
}
