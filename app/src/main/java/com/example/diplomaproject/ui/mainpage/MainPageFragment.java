package com.example.diplomaproject.ui.mainpage;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.diplomaproject.R;


public class MainPageFragment extends Fragment implements View.OnClickListener {

    ImageButton speciality;
    ImageButton campaign;
    ImageButton regulations;
    ImageButton sokr;
    ImageButton excursia;
    ImageButton admission;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main_page, container, false);

        speciality = root.findViewById(R.id.speciality);
        campaign = root.findViewById(R.id.campaign);
        regulations = root.findViewById(R.id.regulations);
        sokr = root.findViewById(R.id.sokr);
        excursia = root.findViewById(R.id.excursia);
        admission = root.findViewById(R.id.admission);

        speciality.setOnClickListener(this);
        campaign.setOnClickListener(this);
        regulations.setOnClickListener(this);
        sokr.setOnClickListener(this);
        excursia.setOnClickListener(this);
        admission.setOnClickListener(this);
        return root;
    }


    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.speciality:
                try {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://abiturient.gstu.by/specialties"));
                    startActivity(viewIntent);
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.campaign:
                try {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://abiturient.gstu.by/course-of-documents-acceptance"));
                    startActivity(viewIntent);
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.regulations:
                try {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://abiturient.gstu.by/regulations"));
                    startActivity(viewIntent);
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.sokr:
                try {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://abiturient.gstu.by/higher-education-in-reduced-terms-of-training"));
                    startActivity(viewIntent);
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.excursia:
                try {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://abiturient.gstu.by/excursion"));
                    startActivity(viewIntent);
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.admission:
                try {
                    Intent viewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://abiturient.gstu.by/how-to-enter-the-GSTU-PO-Sukhoi"));
                    startActivity(viewIntent);
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}