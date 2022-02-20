package com.example.xplore.pictureOfTheDay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.xplore.R;
import com.example.xplore.pictureOfTheDay.threads.ThreadForPictureList;

import java.util.ArrayList;

public class PictureOfTheDay7List extends AppCompatActivity {


    public static ArrayList<String> names = new ArrayList<>();
    public static ArrayList<String> dates = new ArrayList<>();
    public static ArrayList<String> photosLink = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod7days);

        ListView list = findViewById(R.id.list);
        ThreadForPictureList thread = new ThreadForPictureList();


        thread.start();

        try
        {

            thread.join();
            CustomAdapterPictureOfTheDay adapter = new CustomAdapterPictureOfTheDay(PictureOfTheDay7List.this);
            list.setAdapter(adapter);

            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id)
                {

                    Intent intent = new Intent(PictureOfTheDay7List.this, PictureOfTheDayFromList.class);
                    intent.putExtra("POSITION", position);
                    startActivity(intent);


                }
            });


        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected void onPause() {
        super.onPause();



    }
}