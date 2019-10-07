package com.example.android.uchu.ui.exit;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.example.android.uchu.R;
import com.example.android.uchu.ui.database.DatabaseHandler;
import com.example.android.uchu.ui.ui.login.LoginActivity;

public class ExitFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity())
                .setTitle(R.string.exit_question).setPositiveButton(R.string.exit_yes, this)
                .setNegativeButton(R.string.exit_no, this);
        return adb.create();
    }

    public void onClick(DialogInterface dialog, int which) {
        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                DatabaseHandler.USER_ID = -1;
                Log.i("my_log", "id = " + DatabaseHandler.USER_ID);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                break;
            case Dialog.BUTTON_NEGATIVE:
                dialog.cancel();
                break;
        }
    }

    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
    }

    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
    }

}
