package com.example.alessandro.apontemobile;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class PopGrActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_gr);

        DisplayMetrics dmp = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dmp);

        int width = dmp.widthPixels;
        int height = dmp.heightPixels;

        getWindow().setLayout((int)(width*.6),(int)(height*.4));

    }
}
