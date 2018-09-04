package com.example.helenapopova.whether.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.helenapopova.whether.R;
import com.example.helenapopova.whether.info.CityesInfo;
import com.example.helenapopova.whether.screen.wheatherlist.CitiesAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class CityesList extends Fragment {
    private View fragmentSettings;
    private LayoutInflater inflaterSett;
    private Unbinder unbinder;
    private CitiesAdapter citiesAdapter;
    private View.OnClickListener openCard;
    private View viewCityesList;

    @BindView(R.id.recyclerViewCityes)
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        viewCityesList = inflater.inflate(R.layout.fragment_cityes_list, container, false);
        unbinder = ButterKnife.bind(this, viewCityesList);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(new CitiesAdapter(CityesInfo.titles));

        return viewCityesList;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


}
