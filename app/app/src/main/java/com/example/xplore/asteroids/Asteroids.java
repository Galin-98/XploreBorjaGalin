package com.example.xplore.asteroids;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.xplore.R;

import java.util.ArrayList;

public class Asteroids extends AppCompatActivity {

    static ArrayList<String> asteroidsName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asteroids);
        ListView list = findViewById(R.id.list_asteroids);

        AsteroidThread thread = new AsteroidThread();
        thread.start();
        try {
            thread.join();

            CustomAdapterAsteroids customAdapter = new CustomAdapterAsteroids(this);
            list.setAdapter(customAdapter);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}