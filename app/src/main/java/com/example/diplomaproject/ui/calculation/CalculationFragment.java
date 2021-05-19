package com.example.diplomaproject.ui.calculation;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;
import com.google.android.material.snackbar.Snackbar;

public class CalculationFragment extends Fragment implements View.OnClickListener{

    Button calculateBtn;
    EditText subj1;
    EditText subj2;
    EditText subj3;
    EditText certificate;
    TextView result;
    ConstraintLayout CLCalculation;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_calculator, container, false);

        subj1 = root.findViewById(R.id.subj1Edit);
        subj2 = root.findViewById(R.id.subj2Edit);
        subj3 = root.findViewById(R.id.subj3Edit);
        certificate = root.findViewById(R.id.certificateEdit);
        result = root.findViewById(R.id.resultText);
        calculateBtn = root.findViewById(R.id.calculateBtn);
        CLCalculation = root.findViewById(R.id.calculateFragment);
        calculateBtn.setOnClickListener(this);
        return root;
    }

    @SuppressLint({"NonConstantResourceId", "SetTextI18n"})
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculateBtn) {
            if(Integer.parseInt(subj1.getText().toString()) > 0 &&
                    Integer.parseInt(subj1.getText().toString()) < 101 &&
                    Integer.parseInt(subj2.getText().toString()) > 0 &&
                    Integer.parseInt(subj2.getText().toString()) < 101 &&
                    Integer.parseInt(subj3.getText().toString()) > 0 &&
                    Integer.parseInt(subj3.getText().toString()) < 101 &&
                    Integer.parseInt(certificate.getText().toString()) > 0 &&
                    Integer.parseInt(certificate.getText().toString()) < 101)
            {
                int res = Integer.parseInt(subj1.getText().toString()) +
                        Integer.parseInt(subj2.getText().toString()) +
                        Integer.parseInt(subj3.getText().toString()) +
                        Integer.parseInt(certificate.getText().toString());
                result.setText(Integer.toString(res));
            }
            else {
                showSnackBar("Вы ввели неверные значения. Диапазон: от 1 до 100.");
            }
        }

    }
    private void showSnackBar(String msg) {
        Snackbar snackbar = Snackbar
                .make(CLCalculation, msg, Snackbar.LENGTH_LONG);
        snackbar.show();
    }
}
