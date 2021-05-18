package com.example.diplomaproject.ui.mainpage;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.diplomaproject.R;
import com.example.diplomaproject.ui.web.MyRunTimesData;
import com.example.diplomaproject.ui.web.WebFragment;


public class MainPageFragment extends Fragment implements View.OnClickListener {

    ImageButton speciality;
    ImageButton campaign;
    ImageButton regulations;
    ImageButton sokr;
    ImageButton excursia;
    ImageButton admission;
    View root;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        root = inflater.inflate(R.layout.fragment_main_page, container, false);

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
                    MyRunTimesData.setMyStringData("https://abiturient.gstu.by/specialties");
                    Fragment fragment = new WebFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_main_page, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.campaign:
                try {
                    MyRunTimesData.setMyStringData("https://abiturient.gstu.by/course-of-documents-acceptance");
                    Fragment fragment = new WebFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_main_page, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.regulations:
                try {
                    MyRunTimesData.setMyStringData("https://abiturient.gstu.by/regulations");
                    Fragment fragment = new WebFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_main_page, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.sokr:
                try {
                    MyRunTimesData.setMyStringData("https://abiturient.gstu.by/higher-education-in-reduced-terms-of-training");
                    Fragment fragment = new WebFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_main_page, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.excursia:
                try {
                    MyRunTimesData.setMyStringData("https://abiturient.gstu.by/excursion");
                    Fragment fragment = new WebFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_main_page, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.admission:
                try {
                    MyRunTimesData.setMyStringData("https://abiturient.gstu.by/how-to-enter-the-GSTU-PO-Sukhoi");
                    Fragment fragment = new WebFragment();
                    FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
                    transaction.replace(R.id.fragment_main_page, fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }
                catch (Exception e) {
                    Toast.makeText(getActivity().getApplicationContext(), "Can't connect to Internet",
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }

    }
}