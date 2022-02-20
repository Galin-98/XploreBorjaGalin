package com.example.xplore.asteroids;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.xplore.R;

public class AsteroidDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asteroid_details);

        int position = getIntent().getIntExtra("position" , 0);

        TextView name = findViewById(R.id.asteroidname);
        TextView diameter = findViewById(R.id.asteroiddiameter);
        TextView hazar = findViewById(R.id.asteroidHazar);
        TextView lastdate = findViewById(R.id.lastDate);
        TextView kmh = findViewById(R.id.kmh);
        TextView distance = findViewById(R.id.distance);
        TextView futuredate = findViewById(R.id.futuredate);
        TextView kmh2 = findViewById(R.id.kmh2);
        TextView distance2 = findViewById(R.id.distance2);

        name.setText(Asteroids.asteroidsMatrix.get(position).get(0));
        diameter.setText(Asteroids.asteroidsMatrix.get(position).get(1));
        if(Asteroids.asteroidsMatrix.get(position).get(2).equals("true"))
        {
            hazar.setText("YES");
        }
        else
        {
            hazar.setText("NO");
        }
        lastdate.setText(Asteroids.asteroidsMatrix.get(position).get(3));
        kmh.setText(Asteroids.asteroidsMatrix.get(position).get(4));
        distance.setText(Asteroids.asteroidsMatrix.get(position).get(5));
        futuredate.setText(Asteroids.asteroidsMatrix.get(position).get(6));
        kmh2.setText(Asteroids.asteroidsMatrix.get(position).get(7));
        distance2.setText(Asteroids.asteroidsMatrix.get(position).get(8));

    }
}