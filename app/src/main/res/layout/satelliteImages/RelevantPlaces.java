package com.example.xplore.satelliteImages;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;

public class RelevantPlaces extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relevant_places);

        ImageView area51 = findViewById(R.id.buttonRelevant1);
        ImageView chernobyl = findViewById(R.id.buttonRelevant2);
        ImageView giza = findViewById(R.id.buttonRelevant3);
        ImageView madrid = findViewById(R.id.buttonRelevant4);
        ImageView kennedySpaceCenter= findViewById(R.id.buttonRelevant5);

        area51.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SatelliteImages.latitudeText.setText("37.234332396");
                SatelliteImages.longitudeText.setText("-115.80666344");

                finish();
            }
        });

        chernobyl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SatelliteImages.latitudeText.setText("51.388333333333");
                SatelliteImages.longitudeText.setText("30.101388888889");

                finish();
            }
        });

        giza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SatelliteImages.latitudeText.setText("29.9792345");
                SatelliteImages.longitudeText.setText("31.1342019");

                finish();
            }
        });

        madrid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SatelliteImages.latitudeText.setText("40.416729");
                SatelliteImages.longitudeText.setText("-3.703339");

                finish();
            }
        });

        kennedySpaceCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SatelliteImages.latitudeText.setText("28.573469");
                SatelliteImages.longitudeText.setText("80.651070");

                finish();
            }
        });

    }
}