package com.example.xplore.satelliteImages;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.xplore.R;
import com.squareup.picasso.Picasso;

public class CustomAdapterSatellite extends RecyclerView.Adapter<CustomAdapterSatellite.MyViewHolder> implements View.OnClickListener {

    Context ctx ;

    private View.OnClickListener listener;

    public CustomAdapterSatellite (Context ctx )
    {
        this.ctx = ctx;

    }


    @Override
    public void onClick(View v) {

        if ((listener != null))
        {
            listener.onClick(v);
        }


    }

    public void setOnClickListener(View.OnClickListener listener){ this.listener = listener;}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflateView = LayoutInflater.from(ctx).inflate(R.layout.model_list_satellite , null);
        MyViewHolder holder = new MyViewHolder(inflateView);
        inflateView.setOnClickListener(this);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {

        ImageView image = holder.itemView.findViewById(R.id.ImageRecyclerSatellite);
        TextView text = holder.itemView.findViewById(R.id.TextRecyclerSatellite);

        if (!SatelliteImageResult.matrix.get(position).get(0).isEmpty())
        {
            text.setText(SatelliteImageResult.matrix.get(position).get(0));
            Picasso.get().load(SatelliteImageResult.matrix.get(position).get(1)).into(image);
        }


    }

    @Override
    public int getItemCount() {
        return SatelliteImageResult.matrix.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }
}
