package com.example.diplomaproject.ui.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CreateDialogFragment extends DialogFragment {
    private Creatable creatable;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        creatable = (Creatable) context;
    }
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        return builder
                .setTitle("Подтвердите действие")
                .setIcon(android.R.drawable.ic_dialog_info)
                .setMessage("После отправки редактирование данных невозможно. При успешной регистрации вы будете перенаправлены на страницу авторизации")
                .setPositiveButton("OK", (dialog, which) -> {
                    creatable.create();
                })
                .setNegativeButton("Отмена", null)
                .create();
    }

}
