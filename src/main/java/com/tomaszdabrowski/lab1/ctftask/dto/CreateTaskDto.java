package com.tomaszdabrowski.lab1.ctftask.dto;

import java.util.UUID;
import java.util.function.Function;

import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.model.Task;

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
public class CreateTaskDto {
    private String name;
    private UUID categoryId;
    private String description;
    private int points;
    private String flag;

    public static Function<CreateTaskDto, Task> dtoToEntityMapper(Category category) {
        return dto -> Task.builder()
                .name(dto.getName())
                .category(category)
                .description(dto.getDescription())
                .points(dto.getPoints())
                .flag(dto.getFlag())
                .build();
    }
}
