package com.example.todoapp;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.todoapp.model.Task;
import com.example.todoapp.viewmodel.TodoViewModel;

import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class EditTodoFragment extends Fragment {


    private TodoViewModel todoViewModel;
    private int todoId;
    //view elements
    View rootView;
    EditText txtTitle, txtDescription, txtDate;
    Button addButton,cancelButton;
    AlertDialog.Builder alertDialog;
    DatePickerDialog datePicker;



    @SuppressLint({"ClickableViewAccessibility", "SimpleDateFormat"})
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for the fragment
        rootView = inflater.inflate(R.layout.fragment_edit_todo, container, false);
        todoViewModel = ViewModelProviders.of(this).get(TodoViewModel.class);
        txtTitle = rootView.findViewById(R.id.edit_txt_title);
        txtDescription = rootView.findViewById(R.id.edit_txt_description);
        txtDate = rootView.findViewById(R.id.edit_txt_date);
        addButton = rootView.findViewById(R.id.edit_btn_save);
        cancelButton = rootView.findViewById(R.id.edit_btn_cancel);
        txtDate = rootView.findViewById(R.id.edit_txt_date);


        //adding click listener on save button for adding new todo
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddTodo();
            }
        });

        //adding click listener n cancel button
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DisplayAlertDialog();
            }
        });
        txtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction()==MotionEvent.ACTION_DOWN)
                {
                    DisplayTodoDate();
                }

                return false;
            }
        });
        //for updating the todo
        todoId=getActivity().getIntent().getIntExtra("TodoId",-1);
        if (todoId!=-1){
            addButton.setText(getText(R.string.edit_update));
            Task todo = todoViewModel.getTodoById(todoId);
            txtTitle.setText(todo.getTitle());
            txtDescription.setText(todo.getDescription());
            DateFormat formatter;
            formatter=new SimpleDateFormat("yyyy-MM-dd");
            txtDate.setText(formatter.format(todo.getCreatedDate()));

        }
        return rootView;

    }
    //Alert Dailog Box Cancellation
    void DisplayAlertDialog(){
        alertDialog = new AlertDialog.Builder(getContext());
        alertDialog.setMessage(getString(R.string.edit_cancel_promt))
                .setCancelable(false)
                .setTitle(getString(R.string.app_name));

        alertDialog.setPositiveButton(getString(R.string.ok), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(getActivity(),MainActivity.class);
                startActivity(intent);
            }
        });
        alertDialog.setNegativeButton(getString(R.string.cancel), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alertDialog.show();
    }
    //For intent displaying calender
    void DisplayTodoDate(){
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        datePicker=new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                txtDate.setText(year+"-"+month+"-"+dayOfMonth);
            }
        },year, month, day);
        datePicker.show();
    }

    @SuppressLint({"SimpleDateFormat", "NonConstantResourceId"})
        //class created for adding todo with details
    void AddTodo(){
        boolean validate = true;
        Task todo= new Task();
        Date todoDate;

        //textfield validations
        if (txtTitle.getText().toString().trim().equals("")){
            txtTitle.setError("Please insert title.");
            txtTitle.requestFocus();
            validate = false;
        }
        if (txtDescription.getText().toString().trim().equals("")){
            txtDescription.setError("Please insert description.");
            txtTitle.requestFocus();
            validate = false;
        }
        if (txtDate.getText().toString().trim().equals("")){
            txtDate.setError("Please pick a date.");
            txtTitle.requestFocus();
            validate = false;
        }

        todo.setTitle(txtTitle.getText().toString());
        todo.setDescription(txtDescription.getText().toString());
        try {
            DateFormat formatter;
            formatter=new SimpleDateFormat("yyyy-MM-dd");
            todoDate=(Date)formatter.parse(txtDate.getText().toString());
            todo.setCreatedDate(todoDate);
        }
        catch(ParseException e){
            e.printStackTrace();
        }


        //add todo if the todo is new and update if it already exists
        if (validate){
            if (todoId!= -1){
                todo.setId(todoId);
                todoViewModel.update(todo);
                Toast.makeText(getActivity(),getText(R.string.crud_update),Toast.LENGTH_SHORT).show();
            }else{

                todoViewModel.insert(todo);
                Toast.makeText(getActivity(),getText(R.string.crud_save),Toast.LENGTH_SHORT).show();
            }
            //After saving or updating return back to main activity
            Intent intent = new Intent(getActivity(),MainActivity.class);
            startActivity(intent);
        }
    }
}