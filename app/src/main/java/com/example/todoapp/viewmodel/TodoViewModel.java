package com.example.todoapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.todoapp.data.TodoRepository;
import com.example.todoapp.model.Task;

import java.util.List;

public class TodoViewModel extends AndroidViewModel {
    private TodoRepository mTodoRepository;
    private LiveData<List<Task>> mAllTodos;

    public TodoViewModel(@NonNull Application application) {
        super(application);

        mTodoRepository = new TodoRepository(application);
        mAllTodos=mTodoRepository.getmAllTodoList();
    }
    public void insert(Task todo){
        mTodoRepository.insert(todo);
    }
    public void update(Task todo){
        mTodoRepository.update(todo);
    }
    public LiveData<List<Task>> getAllTodos() {
        return mAllTodos;
    }
    public Task getTodoById(int id){
        return mTodoRepository.getTodoById(id);
    }

    public void deleteById(Task todo){
        mTodoRepository.deleteById(todo);
    }
    public void deleteAll(){
        mTodoRepository.deleteAll();
    }
    public void deleteCompleted(){
        mTodoRepository.deleteCompleted();
    }
}
