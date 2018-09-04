package com.example.helenapopova.whether;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.helenapopova.whether.fragments.WheatherCard;


public class MainActivity extends AppCompatActivity {

    private WheatherCard wheatherCard;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wheatherCard = new WheatherCard();
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction
                .replace(R.id.general_window, wheatherCard)
                .addToBackStack(wheatherCard.getClass().getSimpleName())
                .commit();
    }
}
