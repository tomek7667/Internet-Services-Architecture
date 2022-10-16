package com.tomaszdabrowski.lab1.ctftask.controller;

import com.tomaszdabrowski.lab1.ctftask.dto.CreateTaskDto;
import com.tomaszdabrowski.lab1.ctftask.dto.GetTaskDto;
import com.tomaszdabrowski.lab1.ctftask.dto.GetTasksDto;
import com.tomaszdabrowski.lab1.ctftask.service.CategoryService;
import com.tomaszdabrowski.lab1.ctftask.service.TaskService;
import com.tomaszdabrowski.lab1.ctftask.model.Category;
import com.tomaszdabrowski.lab1.ctftask.model.Task;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {

    private TaskService taskService;
    private CategoryService categoryService;

    @Autowired
    public TaskController(TaskService taskService, CategoryService categoryService) {
        this.taskService = taskService;
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<GetTasksDto> getTasks() {
        return ResponseEntity.ok(
                GetTasksDto.entityToDtoMapper().apply(taskService.findMany()));
    }

    @GetMapping("{taskId}")
    public ResponseEntity<GetTaskDto> getTask(
            @PathVariable("taskId") UUID taskId) {
        return taskService
                .findOne(taskId)
                .map(
                        value -> ResponseEntity.ok(GetTaskDto.entityToDtoMapper().apply(value)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<GetTaskDto> createTask(@RequestBody CreateTaskDto createTaskDto, UriComponentsBuilder builder) {
        Optional<Category> category = categoryService.findOne(createTaskDto.getCategoryId());
        if (category.isPresent()) {
            Task task = CreateTaskDto.dtoToEntityMapper(category.get()).apply(createTaskDto);
            task = taskService.createOne(task);
            return ResponseEntity.created(builder.pathSegment("api", "v1", "tasks", "{taskId}").buildAndExpand(task.getId()).toUri())
                    .body(GetTaskDto.entityToDtoMapper().apply(task));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
