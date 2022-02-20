package com.example.xplore.pictureOfTheDay;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xplore.R;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class PictureOfTheDayFromList extends AppCompatActivity {


    public static ArrayList<String> descriptions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apod7days_enter);
        int position = getIntent().getIntExtra("POSITION" , 0);
        ImageView image = findViewById(R.id.image7D);
        TextView title = findViewById(R.id.title7D);
        TextView description = findViewById(R.id.description7D);

        title.setText(PictureOfTheDay7List.names.get((position)));
        description.setText(descriptions.get(position));

        Transformation trans = new RoundedTransformationBuilder()
                .borderColor(Color.DKGRAY)
                .borderWidthDp(1)
                .cornerRadiusDp(10)
                .oval(false)
                .build();

        Picasso.get().load(PictureOfTheDay7List.photosLink.get((position))).fit().transform(trans).into(image);


    }
}