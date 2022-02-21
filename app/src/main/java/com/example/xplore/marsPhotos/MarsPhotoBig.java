package com.example.xplore.marsPhotos;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;
import com.squareup.picasso.Picasso;

public class MarsPhotoBig extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_photo_big);
        String rover = getIntent().getStringExtra("rover");
        int position = getIntent().getIntExtra("position" , 0);


        ImageView image = findViewById(R.id.marsBigPhoto);

        switch (rover)
        {
            case "spirit":
                Picasso.get().load(MarsPhotos.spiritImages.get((position))).into(image);
                break;

            case "opportunity":
                Picasso.get().load(MarsPhotos.opportunityImages.get((position))).into(image);
                break;

            case "curiosity":
                Picasso.get().load(MarsPhotos.curiosityImages.get((position))).into(image);
                break;
        }
    }
}