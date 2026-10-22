package com.codealpha.operations.service;

import com.codealpha.operations.model.OperationalTask;
import com.codealpha.operations.model.TaskPriority;
import com.codealpha.operations.model.TaskStatus;
import com.codealpha.operations.model.TaskType;
import com.codealpha.operations.repository.OperationalTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class OperationalTaskService {

    private final OperationalTaskRepository repository;

    public OperationalTaskService(OperationalTaskRepository repository) {
        this.repository = repository;
    }

    public OperationalTask create(OperationalTask task) {
        task.setStatus(TaskStatus.OPEN);
        return repository.save(task);
    }

    @Transactional(readOnly = true)
    public List<OperationalTask> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public OperationalTask getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException(
                                "Operational task not found with ID: " + id));
    }

    @Transactional(readOnly = true)
    public List<OperationalTask> getByStatus(TaskStatus status) {
        return repository.findByStatus(status);
    }

    @Transactional(readOnly = true)
    public List<OperationalTask> getByType(TaskType type) {
        return repository.findByType(type);
    }

    @Transactional(readOnly = true)
    public List<OperationalTask> getByPriority(TaskPriority priority) {
        return repository.findByPriority(priority);
    }

    public OperationalTask update(Long id, OperationalTask details) {
        OperationalTask existing = getById(id);

        existing.setTitle(details.getTitle());
        existing.setDescription(details.getDescription());
        existing.setType(details.getType());
        existing.setPriority(details.getPriority());
        existing.setStatus(details.getStatus());

        return repository.save(existing);
    }

    public void delete(Long id) {
        OperationalTask existing = getById(id);
        repository.delete(existing);
    }
}