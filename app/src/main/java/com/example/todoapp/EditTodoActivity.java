package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

public class EditTodoActivity extends AppCompatActivity {

    Fragment frag;
    FragmentManager frag_manager;
    //adds new fragment to the activity and commits the transaction and display the fragment in activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_todo);

        frag=new EditTodoFragment();
        frag_manager=getSupportFragmentManager();
        frag_manager.beginTransaction()
                .add(R.id.main_container,frag)
                .commit();
    }
}