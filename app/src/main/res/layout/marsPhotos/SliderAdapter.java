package com.example.xplore.marsPhotos;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.xplore.R;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.ArrayList;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.Holder>
{

    ArrayList<String> list = new ArrayList<>();

public SliderAdapter(ArrayList<String> list)
{
    for (int i = 0; i < list.size(); i++) {
        this.list.add(list.get(i));

    }
}


    @Override
    public Holder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.slider_item, parent ,false);

        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder viewHolder, int position)
    {
        Transformation trans = new RoundedTransformationBuilder()
                .borderColor(Color.DKGRAY)
                .borderWidthDp(1)
                .cornerRadiusDp(10)
                .oval(false)
                .build();

        Picasso.get().load(list.get(position)).fit().transform(trans).into(viewHolder.imageView);

    }


    @Override
    public int getCount() {
        return 25;
    }

    public class Holder extends ViewHolder
    {

        ImageView imageView;

        public Holder(View itemView)
        {
            super(itemView);
            imageView = itemView.findViewById(R.id.slider_ImgV);
        }
    }

}
