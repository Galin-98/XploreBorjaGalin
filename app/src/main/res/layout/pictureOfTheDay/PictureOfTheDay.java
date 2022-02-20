package com.example.xplore.pictureOfTheDay;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;
import com.example.xplore.pictureOfTheDay.threads.ThreadPicture;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

public class PictureOfTheDay extends AppCompatActivity {


    public static String url  ;
    public static String titleMain;
    public static String descriptionMain;




    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod);

        //Associate views whit ID
        ImageView image = findViewById(R.id.apod);
        TextView title = findViewById(R.id.titleapod);
        TextView description  = findViewById(R.id.description);
        ImageView button = findViewById(R.id.button7days);

        //Start Thread
        ThreadPicture thread = new ThreadPicture();
        thread.start();

        try
        {
            thread.join();

            //Transformation form of picture
            Transformation trans = new RoundedTransformationBuilder()
                    .borderColor(Color.DKGRAY)
                    .borderWidthDp(1)
                    .cornerRadiusDp(10)
                    .oval(false)
                    .build();

            //Set data in to views
            Picasso.get().load(url).fit().transform(trans).into(image);
            title.setText(titleMain);
            description.setText(descriptionMain);



        } catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        //button listener to go 7 days photos list
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
               startActivity(new Intent(PictureOfTheDay.this , PictureOfTheDay7List.class));
            }
        });

    }


}