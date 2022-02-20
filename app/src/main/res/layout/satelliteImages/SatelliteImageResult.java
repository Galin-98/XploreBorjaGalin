package com.example.xplore.satelliteImages;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xplore.R;

import java.util.ArrayList;

public class SatelliteImageResult extends AppCompatActivity
{

    public static ArrayList<ArrayList<String>> matrix = new ArrayList<ArrayList<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_satellite_image_result);

        RecyclerView recyclerView = findViewById(R.id.RecyclerSatellite);


        ThreadSatellite thread = new ThreadSatellite();
        matrix.clear();
        thread.start();

        try {
            thread.join();

            if (matrix.isEmpty())
            {
                Toast.makeText(this, "Invalid coordinates", Toast.LENGTH_SHORT).show();
            }

            CustomAdapterSatellite adapter = new CustomAdapterSatellite(this);
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));



            adapter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Intent i = new Intent(SatelliteImageResult.this , SatelliteBigImage.class );

                    i.putExtra("link" , matrix.get(recyclerView.getChildAdapterPosition(view)).get(1));
                    startActivity(i);


                }
            });



        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}