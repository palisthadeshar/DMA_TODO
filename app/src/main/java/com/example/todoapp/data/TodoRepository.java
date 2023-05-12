package com.example.todoapp.data;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.todoapp.model.Task;

import java.util.List;

public class TodoRepository {
    private TodoDAO mTodoDAO;
    private LiveData<List<Task>> mAllTodoList;

    public TodoRepository(Application application) {
        TodoRoomDatabase database = TodoRoomDatabase.getDatabase(application);
        mTodoDAO=database.mTodoDAO();
        mAllTodoList=mTodoDAO.getAllTodos();

    }

    public LiveData<List<Task>> getmAllTodoList() {
        return mAllTodoList;
    }

    public Task getTodoById(int id){
        return mTodoDAO.getTodoById(id);
    }

    public void insert(Task todo){
        new insertTodoAsynchTask(mTodoDAO).execute(todo);
    }

    public void deleteAll(){
        new deleteAllTodoAsynchTask(mTodoDAO).execute();
    }
    public void update(Task todo){
        new updateTodoAsynchTask(mTodoDAO).execute(todo);
    }

    public void deleteById(Task todo){
        new deleteByIdTodoAsynchTask(mTodoDAO).execute(todo);
    }
    public void deleteCompleted(){
        new deleteCompletedTodoAsynchTask(mTodoDAO).execute();
    }

    private static class deleteCompletedTodoAsynchTask extends AsyncTask<Task, Void, Void> {
        private TodoDAO mTodoDAO;
        private deleteCompletedTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(Task... todos) {

//            mTodoDAO.deleteCompleted();
            return null;
        }
    }

    private static class insertTodoAsynchTask extends AsyncTask<Task, Void, Void> {
        private TodoDAO mTodoDAO;
        private insertTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(Task... todos) {
           mTodoDAO.insert(todos[0]);
            return null;
        }
    }
    private static class updateTodoAsynchTask extends AsyncTask<Task, Void, Void> {
        private TodoDAO mTodoDAO;
        private updateTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(Task... todos) {
            mTodoDAO.update(todos[0]);
            return null;
        }
    }
    private static class deleteAllTodoAsynchTask extends AsyncTask<Task, Void, Void> {
        private TodoDAO mTodoDAO;
        private deleteAllTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(Task... todos) {

            mTodoDAO.deleteAll();
            return null;
        }
    }
    private static class deleteByIdTodoAsynchTask extends AsyncTask<Task, Void, Void> {
        private TodoDAO mTodoDAO;
        private deleteByIdTodoAsynchTask(TodoDAO todoDAO){
            mTodoDAO = todoDAO;
        }

        @Override
        protected Void doInBackground(Task... todos) {
            mTodoDAO.deleteById(todos[0]);
            return null;
        }
    }
}
