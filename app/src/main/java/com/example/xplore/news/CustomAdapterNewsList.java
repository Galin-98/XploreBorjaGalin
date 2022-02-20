package com.example.xplore.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xplore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterNewsList extends BaseAdapter {

    Context context;
    ArrayList<NewObject> news = new ArrayList<>();

    public CustomAdapterNewsList(Context context, ArrayList<NewObject> n)
    {
        this.context = context;
        this.news = n ;
    }

    @Override
    public int getCount() {
        return news.size() ;
    }

    @Override
    public NewObject getItem(int position) {
        return news.get(position);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        convertView = LayoutInflater.from(context).inflate(R.layout.model_list_news , null);
        NewObject newAux = getItem(position);
        ImageView image = convertView.findViewById(R.id.newsImage2);
        TextView title = convertView.findViewById(R.id.newsTitle);
        TextView date = convertView.findViewById(R.id.newsDate);

        title.setText(newAux.getTitle());
        date.setText(newAux.getDate());
        System.out.println("url img = " + newAux.getImage());
        Picasso.get().load(newAux.getImage().replace("http" , "https")).into(image);

        return convertView;
    }
}
