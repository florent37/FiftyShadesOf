package com.github.florent37.fiftyshadesof;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(android.R.id.content).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                load();
            }
        });
    }

    void load(){
        final FiftyShadesOf fiftyShadesOf = FiftyShadesOf.with(this)
            .on(R.id.layout, R.id.layout1, R.id.layout2)
            .start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                fiftyShadesOf.stop();
            }
        }, 2000);
    }
}
