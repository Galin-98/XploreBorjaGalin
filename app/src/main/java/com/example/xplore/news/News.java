package com.example.xplore.news;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xplore.R;
import com.example.xplore.news.Threads.ThreadNews;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class News extends AppCompatActivity {

    public static List<NewObject> news = Collections.synchronizedList(new ArrayList<NewObject>());

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        RecyclerView list = findViewById(R.id.newsList);
        RadioGroup group = findViewById(R.id.filterby);
        RadioButton spaceBut = findViewById(R.id.space_com);
        RadioButton nasaBut = findViewById(R.id.nasa_gov);
        RadioButton astronomyBut = findViewById(R.id.astronomy_com);
        RadioButton all = findViewById(R.id.all);



        try {
            URL nasaLink = new URL("https://www.nasa.gov/rss/dyn/breaking_news.rss");
            URL spaceLink = new URL("https://www.space.com/feeds/all");
            URL astroLink = new URL ("https://astronomy.com/rss/news");

            ThreadNews space = new ThreadNews(spaceLink);
            ThreadNews astro = new ThreadNews(astroLink);
            ThreadNews nasa = new ThreadNews(nasaLink);


            if (news.size() == 0)
            {
                space.start();
                astro.start();
                nasa.start();

                space.join();
                astro.join();
                nasa.join();
            }

            list.setLayoutManager(new LinearLayoutManager(this));
            CustomAdapterNews adapter = new CustomAdapterNews(this, news);

            adapter.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {

                    openWebURL(news.get(list.getChildAdapterPosition(view)).getLink());
                }
            });

            list.setAdapter(adapter);
            DividerItemDecoration divider = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
            divider.setDrawable(getDrawable(R.drawable.divider));
            list.addItemDecoration(divider);


            group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup radioGroup, int i) {

                        if (spaceBut.isChecked())
                        {
                            news.clear();
                            ThreadNews space2 = new ThreadNews(spaceLink);

                            try {
                                space2.start();
                                space2.join();
                                adapter.notifyDataSetChanged();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }

                        else if (nasaBut.isChecked())
                        {
                            news.clear();
                            ThreadNews nasa2 = new ThreadNews(nasaLink);

                            try {
                                nasa2.start();
                                nasa2.join();

                                adapter.notifyDataSetChanged();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                        else if (astronomyBut.isChecked())
                        {
                            news.clear();
                            ThreadNews astro2 = new ThreadNews(astroLink);

                            try {
                                astro2.start();
                                astro2.join();
                                adapter.notifyDataSetChanged();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            System.out.println("hola mundo");
                        }

                        else if (all.isChecked())
                        {
                            news.clear();
                            ThreadNews space2 = new ThreadNews(spaceLink);
                            ThreadNews nasa2 = new ThreadNews(nasaLink);
                            ThreadNews astro2 = new ThreadNews(astroLink);

                            space2.start();
                            nasa2.start();
                            astro2.start();

                            try {
                                space2.join();
                                nasa2.join();
                                astro2.join();

                                adapter.notifyDataSetChanged();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }


                }
            });


        } catch (MalformedURLException | InterruptedException e) {
            e.printStackTrace();
        }



    }

    public void openWebURL( String inURL )
    {
        Intent browse = new Intent( Intent.ACTION_VIEW , Uri.parse( inURL ) );

        startActivity( browse );
    }
}