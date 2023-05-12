package com.example.todoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapp.data.TodoRepository;
import com.example.todoapp.model.Task;

import java.util.List;

//manages data into the UI
public class TodoViewModel extends AndroidViewModel {

    private TodoRepository todoRepo;

    private LiveData<List<Task>> todoList;

    public TodoViewModel(@NonNull Application application) {
        super(application);
        //obj to interact with the database and returns data to the TodoViewModel
        todoRepo = new TodoRepository(application);
        //holds the data return by the repo
        todoList=todoRepo.getmAllTodoList();
    }
    //methods to interact with the database
    public void insert(Task todo){
        todoRepo.insert(todo);
    }
    public void update(Task todo){
        todoRepo.update(todo);
    }
    //holds lists of the Task objects
    public LiveData<List<Task>> getAllTodos() {
        return todoList;
    }
    //returns tasks obj by id
    public Task getTodoById(int id){
        return todoRepo.getTodoById(id);
    }

    public void deleteById(Task todo){
        todoRepo.deleteById(todo);
    }
    public void deleteAll(){
        todoRepo.deleteAll();
    }
    public void deleteCompleted(){
        todoRepo.deleteCompleted();
    }
}
