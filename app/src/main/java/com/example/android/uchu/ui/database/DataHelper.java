package com.example.android.uchu.ui.database;

import android.os.AsyncTask;
import android.util.Log;

import com.example.android.uchu.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DataHelper extends AsyncTask<Void, Void, Void> {

    private String resultString;
    private String server = "uchu.ru";
    private User user;

    public DataHelper(User user) {
        this.user = user;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            String myURL = "https://" + server + "/server.php";
            String parameters = "email=" + user.getEmail()
                    + "&password=" + user.getPassword()
                    + "&name=" + user.getName()
                    + "&surname=" + user.getSurname()
                    + "$birthday=" + user.getBirthday()
                    + "&city=" + user.getCity()
                    + "&skill=" + user.getSkill();
            try {
                URL url = new URL(myURL);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setReadTimeout(10000);
                connection.setConnectTimeout(15000);
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                connection.setRequestProperty("Content-Length", "" + parameters.getBytes().length);
                connection.setDoOutput(true);
                connection.setDoInput(true);

                // конвертируем передаваемую строку в UTF-8
                byte[] data = parameters.getBytes(StandardCharsets.UTF_8);
                OutputStream os = connection.getOutputStream();
                os.write(data);
                os.flush();
                os.close();
                connection.connect();
                int responseCode = connection.getResponseCode();

                // передаем ответ на сервер
                ByteArrayOutputStream baos = new ByteArrayOutputStream();

                if (responseCode == 200) {    // Если все ОК (ответ 200)
                    InputStream is = connection.getInputStream();
                    byte[] buffer = new byte[8192]; // размер буфера

                    // Далее так читаем ответ
                    int bytesRead;
                    while ((bytesRead = is.read(buffer)) != -1) {
                        baos.write(buffer, 0, bytesRead);
                    }
                    data = baos.toByteArray();
                    resultString = new String(data, StandardCharsets.UTF_8);  // сохраняем в переменную ответ сервера, у нас "OK"
                }
                connection.disconnect();

            } catch (MalformedURLException e) {
                resultString = "MalformedURLException:" + e.getMessage();
                Log.w("info", resultString);
            } catch (IOException e) {
                resultString = "IOException:" + e.getMessage();
                Log.w("info", resultString);
            } catch (Exception e) {
                resultString = "Exception:" + e.getMessage();
                Log.w("info", resultString);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        Log.i("info", "Создан новый пользователь.");
    }
}
