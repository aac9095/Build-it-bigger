package com.example.displayjoke;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class JokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_joke);
        TextView jokeView = (TextView) findViewById(R.id.joke);
        Bundle bundle = getIntent().getExtras();
        String joke = bundle.getString("joke");
        if (joke != null && joke.length() != 0) {
            jokeView.setText(joke);
        }
    }
}
