package com.example.helenapopova.whether.screen.wheatherlist;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.helenapopova.whether.R;
import com.example.helenapopova.whether.model.City;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Artur Vasilov
 */
public class CitiesAdapter extends RecyclerView.Adapter<CityHolder> {

    private final List<City> mCities;


    private final View.OnClickListener mInternalListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            City city = (City) view.getTag();
        }
    };

    public CitiesAdapter(@NonNull List<City> cities) {
        mCities = new ArrayList<>(cities);
    }

    public void changeDataSet(@NonNull List<City> cities) {
        mCities.clear();
        mCities.addAll(cities);
        notifyDataSetChanged();
        notifyItemChanged(1);
    }

    @Override
    public CityHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.fragment_unit_city, parent, false);
        return new CityHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CityHolder holder, int position) {
        City city = mCities.get(position);
        holder.bind(city);
        holder.itemView.setTag(city);
        holder.itemView.setOnClickListener(mInternalListener);
    }

    @Override
    public int getItemCount() {
        return mCities.size();
    }

}
