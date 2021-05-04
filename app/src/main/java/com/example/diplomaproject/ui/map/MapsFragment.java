package com.example.diplomaproject.ui.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;

public class MapsFragment  extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){

        View root = inflater.inflate(R.layout.fragment_maps, container, false);
        Intent intent = new Intent(getActivity(), MapsActivity.class);
        startActivity(intent);
        return root;
    }
}
