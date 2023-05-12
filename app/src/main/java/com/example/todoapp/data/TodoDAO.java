package com.example.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todoapp.model.Task;

import java.util.List;

@Dao
public interface TodoDAO {
    @Insert
    void insert(Task todo);
    @Delete
    void deleteById(Task todo);
    @Query("DELETE FROM tasks")
    void deleteAll();
    @Update(onConflict = OnConflictStrategy.REPLACE)
    void update(Task... todos);

    @Query("SELECT * FROM tasks ORDER BY created_date")
    LiveData<List<Task>> getAllTodos();

    @Query("SELECT * FROM tasks WHERE id=:id")
    Task getTodoById(int id);


}
