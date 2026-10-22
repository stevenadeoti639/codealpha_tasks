package com.codealpha.operations.repository;

import com.codealpha.operations.model.OperationalTask;
import com.codealpha.operations.model.TaskStatus;
import com.codealpha.operations.model.TaskType;
import com.codealpha.operations.model.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationalTaskRepository
        extends JpaRepository<OperationalTask, Long> {

    List<OperationalTask> findByStatus(TaskStatus status);

    List<OperationalTask> findByType(TaskType type);

    List<OperationalTask> findByPriority(TaskPriority priority);
}