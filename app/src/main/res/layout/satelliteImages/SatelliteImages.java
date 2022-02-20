package com.example.xplore.satelliteImages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;

public class SatelliteImages extends AppCompatActivity {

    public static Double latitude ;
    public static Double longitude ;
    public static EditText latitudeText;
    public static EditText longitudeText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satellite_images);

        latitudeText = findViewById(R.id.latitude);
        longitudeText = findViewById(R.id.longitude);
        ImageView buttonSearch = findViewById(R.id.buttonSearch);
        ImageView buttonRelative = findViewById(R.id.buttonRelevant);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (!(latitudeText.getText().toString().equals("") ) && !(longitudeText.getText().toString().equals("")) )
                {
                    latitude = Double.parseDouble(latitudeText.getText().toString());
                    longitude = Double.parseDouble(longitudeText.getText().toString());
                    Toast.makeText(SatelliteImages.this, "It will take 30s, please wait", Toast.LENGTH_SHORT).show();
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(SatelliteImages.this , SatelliteImageResult.class));
                }

                else if ((latitudeText.getText().toString().equals(""))&&(longitudeText.getText().toString().equals("")))
                {
                    Toast.makeText(SatelliteImages.this, "Introduce latitude and longitude", Toast.LENGTH_SHORT).show();
                }

                else if (latitudeText.getText().toString().equals(""))
                {
                    Toast.makeText(SatelliteImages.this, "Introduce latitude", Toast.LENGTH_SHORT).show();
                }

                else if (longitudeText.getText().toString().equals(""))
                {
                    Toast.makeText(SatelliteImages.this, "Introduce longitude", Toast.LENGTH_SHORT).show();
                }


            }
        });


        buttonRelative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(SatelliteImages.this , RelevantPlaces.class));
            }
        });
    }
}