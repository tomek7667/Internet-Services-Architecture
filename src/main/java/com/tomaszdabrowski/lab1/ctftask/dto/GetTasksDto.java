package com.tomaszdabrowski.lab1.ctftask.dto;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

import com.tomaszdabrowski.lab1.ctftask.model.Task;

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
public class GetTasksDto {
    @Singular
    private List<String> tasks;

    public static Function<Collection<Task>, GetTasksDto> entityToDtoMapper() {
        return tasks -> {
            GetTasksDtoBuilder response = GetTasksDto.builder();
            tasks.stream()
                    .map(Task::getName)
                    .forEach(response::task);
            return response.build();
        };
    }

}
