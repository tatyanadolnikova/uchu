package com.example.android.uchu;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class RegistrationActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText userName;
    private EditText userSurname;
    private EditText userEmail;
    private RadioGroup userGender;
    private Button userBirthday;
    private Spinner userSkill; // сделать множественный выбор

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        toolbar = findViewById(R.id.toolbar);
        userName = findViewById(R.id.registration_name);
        userSurname = findViewById(R.id.registration_surname);
        userEmail = findViewById(R.id.registration_email);
        userGender = findViewById(R.id.registration_gender);
        userBirthday = findViewById(R.id.registration_birthday);
        userSkill = findViewById(R.id.registration_spinner);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = createUser();
                Toast.makeText(RegistrationActivity.this, String.format("%s %s знает %s!", user.getName(), user.getSurname(), user.getSkill()), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(RegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    public User createUser(){
        User user = new User(
                userName.getText().toString().trim(),
                userSurname.getText().toString().trim(),
                userEmail.getText().toString().trim(),
                true,
                "30.07.1996",
                "английский");
        //checkUser(user);
        //saveUser(user);
        return user;
    }

    public boolean checkUser(User user){
        return false;
    }

    public int saveUser(User user){
        int userId = 0;
        return userId;
    }

}
