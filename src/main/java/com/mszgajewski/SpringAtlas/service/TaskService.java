package com.mszgajewski.SpringAtlas.service;

import com.mszgajewski.SpringAtlas.model.Task;
import com.mszgajewski.SpringAtlas.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TaskService {

    @Autowired
    private TaskRepository repository;

    public Task addTask(Task task){
        task.setTaskId(UUID.randomUUID().toString().split("-")[0]);
        return repository.save(task);
    }

    public List<Task> findAllTasks(){
        return repository.findAll();
    }

    public Task getTaskById(String taskId){
        return repository.findById(taskId).get();
    }

    public List<Task> getTaskBySeverity(int severity){
        return repository.findBySeverity(severity);
    }

    public List<Task> getTaskByAssignee(String assignee){
        return repository.getTaskByAssignee(assignee);
    }

    public Task updateTask(Task taskRequest){
        Task existingTask = repository.findById(taskRequest.getTaskId()).get();

        existingTask.setDescription(taskRequest.getDescription());
        existingTask.setSeverity(taskRequest.getSeverity());
        existingTask.setAssignee(taskRequest.getAssignee());
        existingTask.setStoryPoint(taskRequest.getStoryPoint());
        return repository.save(existingTask);
    }

    public String deleteTask(String taskId){
        repository.deleteById(taskId);
        return taskId + " zadanie usunieto";
    }
}