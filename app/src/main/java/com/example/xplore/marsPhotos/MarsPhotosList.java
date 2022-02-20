package com.example.xplore.marsPhotos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xplore.R;

public class MarsPhotosList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mars_photos_list);

        RecyclerView recyclerView = findViewById(R.id.marsList);

        Intent i = new Intent(MarsPhotosList.this, MarsPhotoBig.class);
        CustomAdapterMars customAdapterMars = null;
        
            if(MarsPhotos.radioButtonSpirit.isChecked()) 
            {
                customAdapterMars = new CustomAdapterMars(this, MarsPhotos.spiritImages);
                i.putExtra("rover" , "spirit");
            }

            else if (MarsPhotos.radioButtonCuriosity.isChecked())
            {
                customAdapterMars = new CustomAdapterMars(this, MarsPhotos.curiosityImages);
                i.putExtra("rover" , "curiosity");
            }

            else if (MarsPhotos.radioButtonOpportunity.isChecked()) 
            {
                customAdapterMars = new CustomAdapterMars(this, MarsPhotos.opportunityImages);
                i.putExtra("rover" , "opportunity");
            }

        customAdapterMars.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                i.putExtra("position" ,recyclerView.getChildAdapterPosition(v) );
                startActivity(i);
            }
        });

        recyclerView.setAdapter(customAdapterMars);
        recyclerView.setLayoutManager(new GridLayoutManager(this , 3));

        DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        DividerItemDecoration divider2 = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
        divider.setDrawable(getDrawable(R.drawable.divider));
        divider2.setDrawable(getDrawable(R.drawable.divider));

        recyclerView.addItemDecoration(divider);
        recyclerView.addItemDecoration(divider2);
    }
}