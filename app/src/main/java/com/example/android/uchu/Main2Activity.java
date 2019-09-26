package com.example.android.uchu;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements View.OnClickListener {

    Context context;
    ExitDialog exitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        context = Main2Activity.this;
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Hi!", Toast.LENGTH_LONG).show();
            }
        });

        Button profileButton = findViewById(R.id.menu_profile);
        Button searchButton = findViewById(R.id.menu_search);
        Button communicationButton = findViewById(R.id.menu_communication);
        Button contactsButton = findViewById(R.id.menu_saved_contacts);
        Button settingsButton = findViewById(R.id.menu_settings);
        Button exitButton = findViewById(R.id.menu_exit);

        profileButton.setOnClickListener(this);
        searchButton.setOnClickListener(this);
        communicationButton.setOnClickListener(this);
        contactsButton.setOnClickListener(this);
        settingsButton.setOnClickListener(this);
        exitButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.menu_profile:
                intent = new Intent(context, ProfileActivity.class);
                break;
            case R.id.menu_search:
                intent = new Intent(context, SearchActivity.class);
                break;
            case R.id.menu_communication:
                intent = new Intent(context, CommunicationActivity.class);
                break;
            case R.id.menu_saved_contacts:
                intent = new Intent(context, SavedContactsActivity.class);
                break;
            case R.id.menu_settings:
                intent = new Intent(context, SettingsActivity.class);
                break;
            case R.id.menu_exit:
                exitDialog = new ExitDialog();
                exitDialog.show(getSupportFragmentManager(), "dialog");
                break;
        }
        if(intent != null)
            startActivity(intent);
    }
}