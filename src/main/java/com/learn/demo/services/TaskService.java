package com.learn.demo.services;

import com.learn.demo.exceptions.TodoExceptions;
import com.learn.demo.mapper.TaskInDTOToTask;
import com.learn.demo.persistence.entity.Task;
import com.learn.demo.persistence.entity.TaskStatus;
import com.learn.demo.persistence.repository.TaskRepository;
import com.learn.demo.services.dto.TaskInDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    private final TaskRepository repository;
    private final TaskInDTOToTask mapper;

    public TaskService(TaskRepository repository, TaskInDTOToTask mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Task createdTask(TaskInDto taskIn){
        Task task = mapper.map(taskIn);
        return this.repository.save(task);
    }

    public List<Task> findAll(){
        return this.repository.findAll();
    }

    public List<Task> findAllByTaskStatus(TaskStatus status){
        return this.repository.findAllByTaskStatus(status);
    }

    @Transactional
    public void updateTaskAsFinished(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(!optionalTask.isPresent()){
            throw new TodoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.markTaskAsFinished(id);
    }

    public void deleteById(Long id){
        Optional<Task> optionalTask = this.repository.findById(id);
        if(!optionalTask.isPresent()){
            throw new TodoExceptions("Task not found", HttpStatus.NOT_FOUND);
        }
        this.repository.deleteById(id);
    }
}
