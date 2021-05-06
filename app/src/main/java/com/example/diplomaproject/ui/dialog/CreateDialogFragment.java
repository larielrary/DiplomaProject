package com.example.diplomaproject.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

public class CreateDialogFragment extends DialogFragment {
    private Creatable creatable;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        creatable = (Creatable) context;
    }
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final String phone = getArguments().getString("phone");
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Подтвердите действие")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMessage("После отправки редактирование данных невозможно.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       // creatable.();
                    }
                })
                .setNegativeButton("Отмена", null)
                .create();
    }
}
