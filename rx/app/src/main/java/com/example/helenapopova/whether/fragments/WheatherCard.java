package com.example.helenapopova.whether.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.helenapopova.whether.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;


public class WheatherCard extends Fragment {

    @BindView(R.id.weather_main_city)
    TextView city_title;

    @BindView(R.id.references_to_list)
    Button button_to_list;

    private View fragment;

    private Unbinder unbinder;
    private CityesList cityesList;

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment = inflater.inflate(R.layout.fragment_wheather_card, container, false);
        unbinder = ButterKnife.bind(this, fragment);
        city_title.setText(getCityName(savedInstanceState));
        getSetLissener();
        return fragment;

    }

    private String getCityName(Bundle bundle) {
        String result = "St. Petersburg";
        if (bundle != null) {
            System.out.println("not null ");
            result = bundle.getString("TITLENAME", "St. Petersburg");
        }
        return result;
    }

    private void getSetLissener() {
        button_to_list.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentManager = getActivity().getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                cityesList = new CityesList();
                fragmentTransaction
                        .replace(R.id.general_window, cityesList)
                        .addToBackStack(cityesList.getClass().getSimpleName())
                        .commit();
            }
        });
    }
}
