package com.example.xplore.asteroids;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;
import com.example.xplore.asteroids.Threads.AsteroidThread;

import java.util.ArrayList;

public class Asteroids extends AppCompatActivity {



    public static ArrayList<ArrayList<String>> asteroidsMatrix = new ArrayList<>() ;

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


            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {
                    Intent i = new Intent(Asteroids.this , AsteroidDetails.class);
                    i.putExtra("position" ,position);
                    startActivity(i);

                }
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}