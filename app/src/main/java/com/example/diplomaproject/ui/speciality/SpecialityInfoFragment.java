package com.example.diplomaproject.ui.speciality;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;

public class SpecialityInfoFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_speciality_info, container, false);
        Intent intent = new Intent(getActivity(), SpecialityActivity.class);
        startActivity(intent);
        return root;
    }

}