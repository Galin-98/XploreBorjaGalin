package com.example.xplore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.xplore.asteroids.Asteroids;
import com.example.xplore.marsPhotos.MarsPhotos;
import com.example.xplore.news.News;
import com.example.xplore.pictureOfTheDay.PictureOfTheDay;
import com.example.xplore.satelliteImages.SatelliteImages;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView apod = findViewById(R.id.menu1);
        ImageView asteroids = findViewById(R.id.asteroids);
        ImageView news = findViewById(R.id.news);
        ImageView mars = findViewById(R.id.mars);
        ImageView Satellite = findViewById(R.id.menuSatellite);



        apod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent( MainActivity.this , PictureOfTheDay.class));
            }
        });

        asteroids.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this , Asteroids.class));
            }
        });

        news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent( MainActivity.this , News.class));
            }
        });

        mars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent( MainActivity.this , MarsPhotos.class));
            }
        });

        Satellite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( MainActivity.this , SatelliteImages.class));
            }
        });
    }
}