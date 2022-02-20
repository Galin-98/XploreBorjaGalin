package com.example.xplore.asteroids;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.xplore.R;

public class CustomAdapterAsteroids extends BaseAdapter
{
    Context context;

    public CustomAdapterAsteroids(Context context) {
        this.context = context;
    }

    @Override
    public int getCount()
    {
        return Asteroids.asteroidsMatrix.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View inflateView = LayoutInflater.from(context).inflate(R.layout.model_list_asteroids , null);

        TextView name = inflateView.findViewById(R.id.asteroids_name);

        name.setText(Asteroids.asteroidsMatrix.get(position).get(0));

        return inflateView;
    }
}
