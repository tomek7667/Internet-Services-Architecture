package com.tomaszdabrowski.lab3.ctftask.controller;

import com.tomaszdabrowski.lab3.ctftask.dto.CreateTaskDto;
import com.tomaszdabrowski.lab3.ctftask.dto.GetTaskDto;
import com.tomaszdabrowski.lab3.ctftask.dto.GetTasksDto;
import com.tomaszdabrowski.lab3.ctftask.dto.UpdateTaskDto;
import com.tomaszdabrowski.lab3.ctftask.model.Category;
import com.tomaszdabrowski.lab3.ctftask.model.Task;
import com.tomaszdabrowski.lab3.ctftask.service.CategoryService;
import com.tomaszdabrowski.lab3.ctftask.service.TaskService;
import java.util.Optional;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
  public TaskController(
    TaskService taskService,
    CategoryService categoryService
  ) {
    this.taskService = taskService;
    this.categoryService = categoryService;
  }

  @GetMapping
  public ResponseEntity<GetTasksDto> getTasks() {
    return ResponseEntity.ok(
      GetTasksDto.entityToDtoMapper().apply(taskService.findMany())
    );
  }

  @GetMapping("{taskId}")
  public ResponseEntity<GetTaskDto> getTask(
    @PathVariable("taskId") UUID taskId
  ) {
    return taskService
      .findOne(taskId)
      .map(value ->
        ResponseEntity.ok(GetTaskDto.entityToDtoMapper().apply(value))
      )
      .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<GetTaskDto> createTask(
    @RequestBody CreateTaskDto createTaskDto,
    UriComponentsBuilder builder
  ) {
    Optional<Category> category = categoryService.findOne(
      UUID.fromString(createTaskDto.getCategoryId())
    );
    if (category.isPresent()) {
      Task task = CreateTaskDto
        .dtoToEntityMapper(category.get())
        .apply(createTaskDto);
      task = taskService.createOne(task);
      return ResponseEntity
        .created(
          builder
            .pathSegment("api", "v1", "tasks", "{taskId}")
            .buildAndExpand(task.getId())
            .toUri()
        )
        .body(GetTaskDto.entityToDtoMapper().apply(task));
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @PutMapping("{taskId}")
  public ResponseEntity<Void> updateTask(
    @PathVariable("taskId") UUID taskId,
    @RequestBody UpdateTaskDto updateTaskDto
  ) {
    Optional<Task> task = taskService.findOne(taskId);
    if (task.isPresent()) {
      if (updateTaskDto.getCategoryId().isPresent()) {
        Optional<Category> category = categoryService.findOne(
          updateTaskDto.getCategoryId().get()
        );
        if (category.isPresent()) {
          Task updatedTask = UpdateTaskDto
            .dtoToEntityUpdater(category)
            .apply(task.get(), updateTaskDto);
          taskService.updateOne(updatedTask);
          return ResponseEntity.ok().build();
        } else {
          return ResponseEntity.notFound().build();
        }
      } else {
        Task updatedTask = UpdateTaskDto
          .dtoToEntityUpdater(null)
          .apply(task.get(), updateTaskDto);
        taskService.updateOne(updatedTask);
        return ResponseEntity.ok().build();
      }
    } else {
      return ResponseEntity.notFound().build();
    }
  }

  @DeleteMapping("{taskId}")
  public ResponseEntity<Void> deleteTask(@PathVariable("taskId") UUID taskId) {
    Optional<Task> task = taskService.findOne(taskId);
    if (task.isPresent()) {
      taskService.deleteOne(taskId);
      return ResponseEntity.ok().build();
    } else {
      return ResponseEntity.notFound().build();
    }
  }
}
