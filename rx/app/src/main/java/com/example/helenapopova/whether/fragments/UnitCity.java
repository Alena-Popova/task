package com.example.helenapopova.whether.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helenapopova.whether.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;


public class UnitCity extends Fragment {
    private View fragmentSettings;
    private LayoutInflater inflaterSett;
    private Unbinder unbinder;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        inflaterSett = inflater;
        fragmentSettings = inflater.inflate(R.layout.fragment_unit_city, container, false);
        unbinder = ButterKnife.bind(this, fragmentSettings);
        return fragmentSettings;
    }

}
