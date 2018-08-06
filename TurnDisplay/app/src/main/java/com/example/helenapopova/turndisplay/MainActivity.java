package com.example.helenapopova.turndisplay;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.helenapopova.turndisplay.utils.TimeLoader;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>, View.OnClickListener {
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.button)
    Button button;

    @BindView(R.id.checkbox_change)
    CheckBox rgFormat;


    final String LOG_TAG = "myLogs";
    static int lastCheckedId = 0;
    static final int LOADER_TIME_ID = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.d("qwe", "create MainActivity: " + this.hashCode());

        startOn();

    }

    private void startOn() {
        Bundle bndl = new Bundle();
        bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
        getSupportLoaderManager().initLoader(LOADER_TIME_ID, bndl, this);
        lastCheckedId = rgFormat.isChecked() ? 1 : 0;
    }

    @Override
    @OnClick(R.id.button)
    public void onClick(View view) {
        getTimeClick();
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        Loader<String> loader = null;
        if (id == LOADER_TIME_ID) {
            loader = new TimeLoader(this, args);
            Log.d(LOG_TAG, "onCreateLoader: " + loader.hashCode());
        }
        return loader;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d(LOG_TAG, "onLoadFinished for loader " + loader.hashCode()
                + ", result = " + data);
        tv.setText(data + " " + String.valueOf(rgFormat.isChecked()));
    }


    @Override
    public void onLoaderReset(Loader<String> loader) {
        Log.d(LOG_TAG, "onLoaderReset for loader " + loader.hashCode());
    }

    public void getTimeClick() {
        Loader<String> loader;
        int id = rgFormat.isChecked() ? 1 : 0;
        if (id == lastCheckedId) {
            System.out.println(" equals");
            loader = getSupportLoaderManager().getLoader(LOADER_TIME_ID);
        } else {
            System.out.println(" change");
            Bundle bndl = new Bundle();
            bndl.putString(TimeLoader.ARGS_TIME_FORMAT, getTimeFormat());
            loader = getSupportLoaderManager().restartLoader(LOADER_TIME_ID, bndl,
                    this);
            lastCheckedId = id;
        }

        loader.forceLoad();
    }

    String getTimeFormat() {
        String result = TimeLoader.TIME_FORMAT_SHORT;
        int id = rgFormat.isChecked() ? 1 : 0;
        switch (id) {
            case 0:
                result = TimeLoader.TIME_FORMAT_SHORT;
                break;
            case 1:
                result = TimeLoader.TIME_FORMAT_LONG;
                break;
        }
        return result;

    }

    public void observerClick(View v) {
    }
}

