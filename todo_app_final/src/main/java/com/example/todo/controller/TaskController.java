package com.example.todo.controller;

import com.example.todo.model.Task;
import com.example.todo.repo.TaskRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*") // Allows frontend calls from any origin
public class TaskController {

    private final TaskRepository repo;

    public TaskController(TaskRepository repo) {
        this.repo = repo;
    }

    // Fetch all tasks
    @GetMapping
    public List<Task> all() {
        return repo.findAll();
    }

    // Fetch single task by ID
    @GetMapping("/{id}")
    public ResponseEntity<Task> getOne(@PathVariable Long id) {
        return repo.findById(id)
                   .map(ResponseEntity::ok)
                   .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new task
    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task) {
        task.setCreatedAt(LocalDateTime.now());
        task.setUpdatedAt(LocalDateTime.now());
        Task saved = repo.save(task);
        return ResponseEntity.ok(saved);
    }

    // Update an existing task
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task incoming) {
        return repo.findById(id).map(t -> {
            if (incoming.getTitle() != null) t.setTitle(incoming.getTitle());
            if (incoming.getDescription() != null) t.setDescription(incoming.getDescription());
            t.setCompleted(incoming.isCompleted());
            if (incoming.getPriority() > 0) t.setPriority(incoming.getPriority());
            if (incoming.getDueDate() != null) t.setDueDate(incoming.getDueDate());
            t.setUpdatedAt(LocalDateTime.now());
            Task saved = repo.save(t);
            return ResponseEntity.ok(saved);
        }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete a task by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!repo.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repo.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
