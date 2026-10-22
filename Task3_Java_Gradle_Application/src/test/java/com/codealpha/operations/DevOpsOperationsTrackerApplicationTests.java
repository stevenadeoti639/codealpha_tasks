package com.codealpha.operations;

import com.codealpha.operations.model.OperationalTask;
import com.codealpha.operations.model.TaskPriority;
import com.codealpha.operations.model.TaskStatus;
import com.codealpha.operations.model.TaskType;
import com.codealpha.operations.repository.OperationalTaskRepository;
import com.codealpha.operations.service.OperationalTaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DevOpsOperationsTrackerApplicationTests {

    @Mock
    private OperationalTaskRepository repository;

    private OperationalTaskService service;

    @BeforeEach
    void setUp() {
        service = new OperationalTaskService(repository);
    }

    @Test
    void createTaskShouldSaveTaskWithOpenStatus() {
        OperationalTask task = new OperationalTask(
            "Deploy production API",
            "Deploy the operations tracker API",
            TaskType.DEPLOYMENT,
            TaskPriority.HIGH
        );

        when(repository.save(any(OperationalTask.class)))
            .thenAnswer(invocation -> invocation.getArgument(0));

        OperationalTask result = service.create(task);

        assertEquals("Deploy production API", result.getTitle());
        assertEquals(TaskStatus.OPEN, result.getStatus());
        verify(repository).save(task);
    }

    @Test
    void getAllTasksShouldReturnTasksFromRepository() {
        OperationalTask task = new OperationalTask(
            "Investigate server alert",
            "Review the server alert and logs",
            TaskType.INCIDENT,
            TaskPriority.CRITICAL
        );

        when(repository.findAll()).thenReturn(List.of(task));

        List<OperationalTask> result = service.getAll();

        assertEquals(1, result.size());
        assertEquals("Investigate server alert", result.get(0).getTitle());
        verify(repository).findAll();
    }

    @Test
    void getTaskByIdShouldReturnTaskWhenItExists() {
        OperationalTask task = new OperationalTask(
            "Apply security updates",
            "Update server packages",
            TaskType.MAINTENANCE,
            TaskPriority.MEDIUM
        );

        when(repository.findById(1L)).thenReturn(Optional.of(task));

        OperationalTask result = service.getById(1L);

        assertEquals("Apply security updates", result.getTitle());
        verify(repository).findById(1L);
    }

    @Test
    void getTaskByIdShouldThrowWhenTaskDoesNotExist() {
        when(repository.findById(99L)).thenReturn(Optional.empty());

        assertThrows(
            java.util.NoSuchElementException.class,
            () -> service.getById(99L)
        );
    }
}