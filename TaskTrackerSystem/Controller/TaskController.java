package com.example.crud_exercise.TaskTrackerSystem.Controller;

import com.example.crud_exercise.Api.ApiResponse;
import com.example.crud_exercise.TaskTrackerSystem.Model.Task;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/task")
public class TaskController {

    ArrayList<Task> tasks = new ArrayList<>();

    @GetMapping("/get")
    public ArrayList<Task> getTasks(){
        return tasks;
    }

    @PostMapping("/add")
    public ApiResponse addTask(@RequestBody Task task){
        tasks.add(task);
        return new ApiResponse("Task added");
    }

    @PutMapping("/update/{index}")
    public ApiResponse updateTask(@PathVariable int index, @RequestBody Task task){
        try {
            tasks.set(index, task);
            return new ApiResponse("Task updated");
        } catch (IndexOutOfBoundsException e){
            return new ApiResponse("Index out of bound");
        }
    }

    @DeleteMapping("/delete/{index}")
    public ApiResponse deleteTask(@PathVariable int index){
        try {
            tasks.remove(index);
            return new ApiResponse("Task deleted");
        }catch (IndexOutOfBoundsException e){
            return new ApiResponse("Index out of bound");
        }
    }

    @PutMapping("/change/{index}")
    public ApiResponse changeStatus(@PathVariable int index){
        try {
            Task task = tasks.get(index);
            if(task.getStatus().equalsIgnoreCase("not done")){
                task.setStatus("done");
                return new ApiResponse("Task status changed");
            }
            return new ApiResponse("Task status not changed");
        } catch (IndexOutOfBoundsException e){
            return new ApiResponse("Index out of bound");
        }
    }

    @GetMapping("/search/{title}")
    public Task searchTasks(@PathVariable String title){
        for(Task task : tasks){
            if(task.getTitle().equals(title))
                return task;
        }
        return null;
    }
}
