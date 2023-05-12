package com.example.todoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.todoapp.viewmodel.TodoViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton fabAddNew;

    Fragment fragment;
    FragmentManager fragmentManager;
    AlertDialog.Builder AlterDialog;
    TodoViewModel TodoViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment=new TodoListFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.list_container,fragment)
                .commit();
        fabAddNew = findViewById(R.id.fab_add_new_todo);

        //fab icon for referring to edit todo page while clicking
        fabAddNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EditTodoActivity.class);
                startActivity(intent);
            }
        });
 
        TodoViewModel= ViewModelProviders.of(this).get(TodoViewModel.class);
    }
    //for menu options
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    //code for extra options
    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_delete_all:
                //confirmation dailog box
                AlterDialog = new AlertDialog.Builder(this);
                AlterDialog.setMessage("Are you sure want to delete all??")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name));

                AlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        TodoViewModel.deleteAll();
                    }
                });
                AlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlterDialog.show();
            break;
            //for exiting the app
            case R.id.menu_logout:
                AlterDialog = new AlertDialog.Builder(this);
                AlterDialog.setMessage("Are you sure want to exit?")
                        .setCancelable(false)
                        .setTitle(getString(R.string.app_name));

                AlterDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences preferences = getApplicationContext().getSharedPreferences("todo_pref", 0);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.apply();
                        finish();
                    }
                });
                AlterDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlterDialog.show();
                break;


        }
        return super.onOptionsItemSelected(item);
    }
}
