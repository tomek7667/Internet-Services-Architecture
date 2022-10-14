package com.tomaszdabrowski.lab1.ctftask.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tomaszdabrowski.lab1.ctftask.dto.GetTasksDto;
import com.tomaszdabrowski.lab1.ctftask.service.TaskService;

@RestController
@RequestMapping("/api/v1/tasks")
public class TaskController {
    private TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public ResponseEntity<GetTasksDto> getTasks() {
        return ResponseEntity.ok(GetTasksDto.entityToDtoMapper().apply(taskService.findMany()));
    }

    @GetMapping("{taskId}")
    public ResponseEntity<GetTasksDto> getTask(@PathVariable("taskId") Long taskId) {
        return taskService.findOne(taskId).map()
    }
}
