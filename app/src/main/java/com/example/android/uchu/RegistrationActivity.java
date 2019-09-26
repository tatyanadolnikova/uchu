package com.example.android.uchu;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.example.android.uchu.ui.database.DbHelper;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class RegistrationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText userName;
    private EditText userSurname;
    private EditText userEmail;
    private EditText userPassword;
    private RadioGroup userGender;
    private RadioButton userFemale;
    private Button userBirthday;
    private EditText userCity;
    private Spinner userSkill;

    DbHelper dbHelper;

    //для создания юзера
    private String oName;
    private String oSurname;
    private String oEmail;
    private String oPassword;
    private int oGender; // 1: ж; 0: м
    private String oBirthday;
    private String oCity;
    private String oSkill;
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
        dbHelper = new DbHelper(this);
        setContentView(R.layout.activity_registration);

        toolbar = findViewById(R.id.toolbar);
        userName = findViewById(R.id.registration_name);
        userSurname = findViewById(R.id.registration_surname);
        userEmail = findViewById(R.id.registration_email);
        userPassword = findViewById(R.id.registration_password);
        userGender = findViewById(R.id.registration_gender);
        userFemale = findViewById(R.id.registration_female);
        userBirthday = findViewById(R.id.registration_birthday);
        userCity = findViewById(R.id.registration_city);
        userSkill = findViewById(R.id.registration_spinner);

        setSupportActionBar(toolbar);

        userBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(DIALOG_DATE);
            }
        });
        userSkill.setOnTouchListener(touchListener);
        setupSpinner(userSkill);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (bdIsChosen && !oSkill.equals(getString(R.string.skill_0))) {
                    createUser(); //creating, check and adding
                    Intent intent = new Intent(RegistrationActivity.this, Main2Activity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public User createUser() {
        oEmail = userEmail.getText().toString().trim();
        oPassword = userPassword.getText().toString().trim();
        oName = userName.getText().toString().trim();
        oSurname = userSurname.getText().toString().trim();
        if(userFemale.isSelected())
            oGender = 1;
        else oGender = 0;
        oBirthday = userBirthday.getText().toString().trim();
        oCity = userCity.getText().toString().trim();

        User user = new User(oEmail, oPassword, oName, oSurname, oGender, oBirthday, oCity, oSkill);
        saveUser(user);
        return user;
    }

    public long saveUser(User user) {
        long userId;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("email", user.getEmail());
        values.put("password", user.getPassword());
        values.put("name", user.getName());
        values.put("surname", user.getSurname());
        values.put("gender", user.getGender());
        values.put("birthday", user.getBirthday());
        values.put("skill", user.getSkill());
        values.put("city", user.getCity());
        Log.i("chLogin", values.toString());

        userId = db.insert("users", null, values);
        return userId;
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
            String bd;
            bd = dayOfMonth + "." + monthOfYear + "." + receivedYear;
            userBirthday.setText(bd);
            oBirthday = bd;
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
                    if (selection.equals(getString(R.string.skill_0)))
                        oSkill = getResources().getString(R.string.skill_0);
                    else if (selection.equals(getString(R.string.skill_1)))
                        oSkill = getResources().getString(R.string.skill_1);
                    else if (selection.equals(getString(R.string.skill_2)))
                        oSkill = getResources().getString(R.string.skill_2);
                    else if (selection.equals(getString(R.string.skill_3)))
                        oSkill = getResources().getString(R.string.skill_3);
                    else if (selection.equals(getString(R.string.skill_4)))
                        oSkill = getResources().getString(R.string.skill_4);
                    else if (selection.equals(getString(R.string.skill_5)))
                        oSkill = getResources().getString(R.string.skill_5);
                    else if (selection.equals(getString(R.string.skill_6)))
                        oSkill = getResources().getString(R.string.skill_6);
                    else if (selection.equals(getString(R.string.skill_7)))
                        oSkill = getResources().getString(R.string.skill_7);
                    else if (selection.equals(getString(R.string.skill_8)))
                        oSkill = getResources().getString(R.string.skill_8);
                    else if (selection.equals(getString(R.string.skill_9)))
                        oSkill = getResources().getString(R.string.skill_9);
                    else if (selection.equals(getString(R.string.skill_10)))
                        oSkill = getResources().getString(R.string.skill_10);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                oSkill = getResources().getString(R.string.skill_0);
            }
        });
    }

}
