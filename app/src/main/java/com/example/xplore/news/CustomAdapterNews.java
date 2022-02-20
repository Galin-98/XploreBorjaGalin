package com.example.xplore.news;

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

import java.util.List;

public class CustomAdapterNews extends RecyclerView.Adapter<CustomAdapterNews.MyViewHolder> implements View.OnClickListener {

    Context ctx;
   List<NewObject> news;
    private View.OnClickListener listener;

    public CustomAdapterNews(Context ctx , List<NewObject> news )
    {
        this.ctx = ctx;
        this.news = news;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View inflateView = LayoutInflater.from(ctx).inflate(R.layout.model_list_news, null);
        MyViewHolder holder = new MyViewHolder(inflateView);
        inflateView.setOnClickListener(this);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        ImageView image = holder.itemView.findViewById(R.id.newsImage2);
        TextView title = holder.itemView.findViewById(R.id.newsTitle);
        TextView date = holder.itemView.findViewById(R.id.newsDate);

        Picasso.get().load(news.get(position).getImage().replace("http" , "https")).into(image);
        title.setText(news.get(position).getTitle());
        date.setText(news.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public void setOnClickListener(View.OnClickListener listener)
    {
        this.listener = listener;
    }
    @Override
    public void onClick(View view)
    {
        if ((listener!= null))
        {
            listener.onClick(view);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
        }
    }

}
