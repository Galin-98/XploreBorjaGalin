package com.example.xplore.satelliteImages;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;
import com.squareup.picasso.Picasso;

public class SatelliteBigImage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satellite_big_image);
        ImageView image = findViewById(R.id.SatelliteBigImage);

        String link = getIntent().getStringExtra("link");

        Picasso.get().load(link).into(image);

    }
}