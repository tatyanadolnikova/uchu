package com.example.android.uchu.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.uchu.DataType;
import com.example.android.uchu.EditorActivity;
import com.example.android.uchu.MainActivity;
import com.example.android.uchu.R;
import com.example.android.uchu.ui.database.DatabaseHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private TextView profileBirthdayData;
    private TextView profileCityData;
    private TextView profileEmailData;
    private TextView profileSkillData;
    private TextView profileAboutData;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);


        DatabaseHandler dbHandler = new DatabaseHandler(getContext());

        getActivity().setTitle(dbHandler.getUserData(DataType.NAME) + " " + dbHandler.getUserData(DataType.SURNAME));

        profileBirthdayData = root.findViewById(R.id.profile_birthday_data);
        profileBirthdayData.setText(dbHandler.getUserData(DataType.BIRTHDAY));

        profileCityData = root.findViewById(R.id.profile_city_data);
        profileCityData.setText(dbHandler.getUserData(DataType.CITY));

        profileEmailData = root.findViewById(R.id.profile_email_data);
        profileEmailData.setText(dbHandler.getUserData(DataType.EMAIL));

        profileSkillData = root.findViewById(R.id.profile_skill_data);
        profileSkillData.setText(dbHandler.getUserData(DataType.SKILL));

        profileAboutData = root.findViewById(R.id.profile_about_data);
        profileAboutData.setText(dbHandler.getUserData(DataType.PERSONAL_INFO));

        FloatingActionButton fab = root.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), EditorActivity.class);
                startActivity(intent);
            }
        });
        return root;
    }


}