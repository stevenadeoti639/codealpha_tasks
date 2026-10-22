package com.codealpha.operations.controller;

import com.codealpha.operations.model.OperationalTask;
import com.codealpha.operations.model.TaskPriority;
import com.codealpha.operations.model.TaskStatus;
import com.codealpha.operations.model.TaskType;
import com.codealpha.operations.service.OperationalTaskService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tasks")
public class OperationalTaskController {

    private final OperationalTaskService service;

    public OperationalTaskController(OperationalTaskService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<OperationalTask> create(
            @Valid @RequestBody OperationalTask task) {

        OperationalTask created = service.create(task);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    public List<OperationalTask> getAll(
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) TaskType type,
            @RequestParam(required = false) TaskPriority priority) {

        if (status != null) {
            return service.getByStatus(status);
        }

        if (type != null) {
            return service.getByType(type);
        }

        if (priority != null) {
            return service.getByPriority(priority);
        }

        return service.getAll();
    }

    @GetMapping("/{id}")
    public OperationalTask getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @PutMapping("/{id}")
    public OperationalTask update(
            @PathVariable Long id,
            @Valid @RequestBody OperationalTask details) {

        return service.update(id, details);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}