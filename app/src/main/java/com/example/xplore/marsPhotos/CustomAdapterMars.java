package com.example.xplore.marsPhotos;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xplore.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapterMars  extends RecyclerView.Adapter<CustomAdapterMars.MyViewHolder> implements View.OnClickListener {

    Context ctx ;
    ArrayList<String> links;
    private View.OnClickListener listener;

    public CustomAdapterMars (Context ctx , ArrayList<String> links)
    {
        this.ctx = ctx;
        this.links = links;
    }



    @Override
    public void onClick(View v)
    {
        if ((listener != null))
        {
            listener.onClick(v);
        }
    }

    public void setOnClickListener(View.OnClickListener listener){ this.listener = listener;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflateView = LayoutInflater.from(ctx).inflate(R.layout.model_list_mars , null);
        MyViewHolder holder = new MyViewHolder(inflateView);
        inflateView.setOnClickListener(this);
        return  holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        ImageView image = holder.itemView.findViewById(R.id.marsPhoto);
        Picasso.get().load(links.get(position)).into(image);

    }



    @Override
    public int getItemCount() {
        return links.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
