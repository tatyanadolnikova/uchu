package com.example.android.uchu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.android.uchu.ui.database.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistrationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText userName;
    private EditText userSurname;
    private EditText userEmail;
    private EditText userPassword;
    private Button userBirthday;
    private EditText userCity;
    private Spinner userSkill;
    private FloatingActionButton addUserButton;

    private String chosenSkill;
    private String chosenBirthday;
    private boolean bdIsChosen = false;

    int DIALOG_DATE = 1;
    private int year = 2020;
    private int month = 01;
    private int day = 01;

    private View.OnTouchListener touchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        findAllViews();
        setOnClickListeners();
    }

    public void findAllViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userName = findViewById(R.id.registration_name);
        userSurname = findViewById(R.id.registration_surname);
        userEmail = findViewById(R.id.registration_email);
        userPassword = findViewById(R.id.registration_password);
        userBirthday = findViewById(R.id.registration_birthday);
        userCity = findViewById(R.id.registration_city);
        userSkill = findViewById(R.id.registration_spinner);
        addUserButton = findViewById(R.id.fab);
    }

    public void setOnClickListeners() {
        userBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });
        userSkill.setOnTouchListener(touchListener);
        setupSpinner(userSkill);
        addUserButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bdIsChosen && !chosenSkill.equals(getString(R.string.skill_0))) {
                    saveUser();
                    Log.i("my_log", "id = " + DatabaseHandler.USER_ID);
                    Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public void saveUser() {
        User user = new User(userEmail.getText().toString().trim(),
                userPassword.getText().toString().trim(),
                userName.getText().toString().trim(),
                userSurname.getText().toString().trim(),
                chosenBirthday,
                userCity.getText().toString().trim(),
                chosenSkill);
        DatabaseHandler dbHandler = new DatabaseHandler(this);
        dbHandler.addUser(user);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpd = new DatePickerDialog(this, myCallBack, year, month, day);
            return tpd;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBack = new DatePickerDialog.OnDateSetListener() {

        public void onDateSet(DatePicker view, int receivedYear, int monthOfYear,
                              int dayOfMonth) {
            chosenBirthday = dayOfMonth + "." + monthOfYear + "." + receivedYear;
            userBirthday.setText(chosenBirthday);
            bdIsChosen = true;
        }
    };

    private void setupSpinner(Spinner spinner) {
        ArrayAdapter importanceSpinnerAdapter = ArrayAdapter.createFromResource(this,
                R.array.skills, android.R.layout.simple_spinner_item);
        importanceSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(importanceSpinnerAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = (String) parent.getItemAtPosition(position);
                if (!TextUtils.isEmpty(selection)) {
                    chosenSkill = selection;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                chosenSkill = getResources().getString(R.string.skill_0);
            }
        });
    }
}
