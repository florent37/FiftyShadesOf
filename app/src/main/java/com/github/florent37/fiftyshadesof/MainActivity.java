package com.github.florent37.fiftyshadesof;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FiftyShadesOf.with(this).on(R.id.text, R.id.image).start();
    }
}
