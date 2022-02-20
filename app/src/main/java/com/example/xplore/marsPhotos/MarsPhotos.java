package com.example.xplore.marsPhotos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;
import com.example.xplore.marsPhotos.Threads.ThreadLastCuriosityPhotos;
import com.example.xplore.marsPhotos.Threads.ThreadMarsRoverPhotos;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;

public class MarsPhotos extends AppCompatActivity {

    public static ArrayList<String> lastCuriosityImages = new ArrayList<>();
    public static ArrayList<String> spiritImages = new ArrayList<>();
    public static ArrayList<String> curiosityImages = new ArrayList<>();
    public static ArrayList<String> opportunityImages = new ArrayList<>();


    SliderView sliderView;
    public static RadioButton radioButtonSpirit;
    public static RadioButton radioButtonCuriosity;
    public static RadioButton radioButtonOpportunity;




    Button buttonSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_photos);

        sliderView  = findViewById(R.id.slider);

        radioButtonSpirit = findViewById(R.id.radioSpirit);
        radioButtonCuriosity = findViewById(R.id.radioCuriosity);
        radioButtonOpportunity = findViewById(R.id.radioOportunnity);


        buttonSearch = findViewById(R.id.buttonSearchMars);

        ThreadLastCuriosityPhotos threadLastCuriosityPhotos = new ThreadLastCuriosityPhotos();
        threadLastCuriosityPhotos.start();
        try {
            threadLastCuriosityPhotos.join();
            System.out.println("SIZE -> " + lastCuriosityImages.size());
            SliderAdapter sliderAdapter = new SliderAdapter(lastCuriosityImages);
            sliderView.setSliderAdapter(sliderAdapter);
            sliderView.setSliderTransformAnimation(SliderAnimations.DEPTHTRANSFORMATION);
            sliderView.startAutoCycle();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



        buttonSearch.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if((radioButtonOpportunity.isChecked()==false)&&(radioButtonCuriosity.isChecked()==false)&&(radioButtonSpirit.isChecked()==false))
                {
                    Toast.makeText(MarsPhotos.this, "Please, select a rover ", Toast.LENGTH_SHORT).show();
                }

                else {
                    ThreadMarsRoverPhotos thread = new ThreadMarsRoverPhotos();
                    thread.start();

                    try {
                        thread.join();

                        Log.d("TEST white_boxV value", Integer.toString(ThreadMarsRoverPhotos.white_Box_test));
                        Log.d("TEST Nº of directions", Integer.toString((spiritImages.size())));


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }


                    startActivity(new Intent(MarsPhotos.this, MarsPhotosList.class));

                }
            } //END ONCLICK
        });



    }
}