package com.example.final_project;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class MainActivityAdapter extends
        RecyclerView.Adapter<MainActivityAdapter.PosterViewHolder> {



    interface ItemListener{
        void onClicked(int post);
    }

    Context context;
    ArrayList<poster> list;
   ItemListener listener;

    public MainActivityAdapter(Context context, ArrayList<poster> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public PosterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.pic_row,parent,false);
        return new MainActivityAdapter.PosterViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PosterViewHolder holder, int position) {

        Glide.with(context).load("https://image.tmdb.org/t/p/original/"+list.get(position).pics).into(holder.im);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // inner class
    public class PosterViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {

        ImageView im;

        public PosterViewHolder(@NonNull View itemView) {
            super(itemView);

            im =  itemView.findViewById(R.id.pic);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClicked( getAdapterPosition());

        }
    }
}
