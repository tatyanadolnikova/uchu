package com.example.android.uchu.ui.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.uchu.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DataHelper extends AsyncTask<Void, Void, Void> {

    private User user;
    private Connection connection;

    public DataHelper(User user) {
        this.user = user;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        //"jdbc:postgresql://hostname:port/dbname"
        String url = "jdbc:postgresql://kvm3.79831382332.10371.vps.myjino.ru:49295/uchu";
        try {
            Class.forName("org.postgresql.Driver");
            Log.i("myinfo", "Class.forName");
            connection = DriverManager.getConnection(url,"tdolnikova", "12129595Vv");
            if (connection != null) {
                Log.i("myinfo", "Connected to the database!");
            } else {
                Log.i("myinfo", "Failed to make connection!");
            }
            connection.close();
        } catch (SQLException e) {
            Log.i("myinfo", String.format("SQL State: %s\nSQL Message: %s\nSQL Error: %s", e.getSQLState(), e.getMessage(), e.getErrorCode()));
        } catch (ClassNotFoundException e) {
            Log.i("myinfo", String.format("Class not found message: %s", e.getMessage()));
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        //Log.i("myinfo", "Создан новый пользователь.");
    }

    private void addUserToDB(User user) {
        String insertTableSQL = "INSERT INTO USERS"
                + "(email, password, name, surname, birthday, city) "
                + "VALUES('"
                + user.getEmail() + "', "
                + user.getPassword() + "', '"
                + user.getName() + "', '"
                + user.getSurname() + "', '"
                + user.getBirthday() + "', '"
                + user.getCity() + "')";
        //insert into users ("email", "password", "name", "surname", "birthday", "city")
        //values ('a@mail.ru', '123', 'Юлия', 'Червоткина', '1995-03-05', 'Томск')

        Log.i("myinfo", insertTableSQL);

        try {
            Statement statement = connection.createStatement();
            Log.i("myinfo", "Statement created");
            statement.executeUpdate(insertTableSQL);
            Log.i("myinfo", "Update executed");
        } catch (SQLException e) {
        }
    }
}
