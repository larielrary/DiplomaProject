package com.example.diplomaproject.ui.web;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;


public class SiteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_site, container, false);
        Intent intent = new Intent(getActivity(), WebActivity.class);
        startActivity(intent);
        return root;
    }
}