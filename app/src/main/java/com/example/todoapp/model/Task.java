package com.example.todoapp.model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.example.todoapp.util.DateConvertor;

import org.jetbrains.annotations.NotNull;

import java.util.Date;

@Entity(tableName = "tasks")
public class Task {
    @PrimaryKey(autoGenerate = true)
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    @ColumnInfo(name="title")
    private String title;

    @ColumnInfo(name="description")
    private String description;

    @ColumnInfo(name = "created_date")
    @TypeConverters({DateConvertor.class})
    private Date createdDate;

    @Ignore
    public Task(){
    }
    public Task(@NotNull String title, String description, Date createdDate){
        this.title = title;
        this.description = description;
        this.createdDate = createdDate;

    }

    @NonNull
    public String getTitle() {
        return title;
    }

    public void setTitle(@NonNull String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

}
