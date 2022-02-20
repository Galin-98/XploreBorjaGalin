package com.example.xplore.pictureOfTheDay;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xplore.R;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;


public class CustomAdapterPictureOfTheDay extends BaseAdapter {


    Context context;

    public CustomAdapterPictureOfTheDay(Context context)
    {
        this.context = context;
    }

    @Override
    public int getCount() {
       return PictureOfTheDayFromList.names.size();
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

        View inflateView = LayoutInflater.from(context).inflate(R.layout.model_list_apod , null);
        TextView name = inflateView.findViewById(R.id.nameApod);
        TextView date = inflateView.findViewById(R.id.dateApod);
        ImageView img = inflateView.findViewById(R.id.imageApod);


       name.setText(PictureOfTheDayFromList.names.get(position));
       date.setText(PictureOfTheDayFromList.dates.get(position));

        Transformation trans = new RoundedTransformationBuilder()
                .borderColor(Color.DKGRAY)
                .borderWidthDp(1)
                .cornerRadiusDp(25)
                .oval(false)
                .build();

        Picasso.get().load(PictureOfTheDayFromList.photosLink.get(position)).transform(trans).into(img);

        return inflateView;

    }
}
