package com.example.diplomaproject.ui.calculation;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;

public class CalculationFragment extends Fragment {

    Button calculateBtn;
    EditText subj1;
    EditText subj2;
    EditText subj3;
    EditText certificate;
    TextView result;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_calculator, container, false);
        subj1 = getActivity().findViewById(R.id.subj1Edit);
        subj2 = getActivity().findViewById(R.id.subj2Edit);
        subj3 = getActivity().findViewById(R.id.subj3Edit);
        certificate = getActivity().findViewById(R.id.certificateEdit);
        result = getActivity().findViewById(R.id.resultText);
        calculateBtn = getActivity().findViewById(R.id.calculateBtn);
        return root;
    }

    public void onCalculateBtnClick (View view){
        result.setText((Integer.parseInt(subj1.getText().toString())+
                Integer.parseInt(subj2.getText().toString())+
                Integer.parseInt(subj3.getText().toString())+
                Integer.parseInt(certificate.getText().toString())));
    }

}
