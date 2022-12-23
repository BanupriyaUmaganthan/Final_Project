package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class RecyclerAdapter extends
        RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder> {



    interface ItemListener{
        void onClicked(int post);
       // void butClicked(int post);
    }

    Context context;
    ArrayList<Movies> list;
    ItemListener listener;

    public RecyclerAdapter(Context context, ArrayList<Movies> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.movie_row,parent,false);
        return new MovieViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {

        holder.moviename.setText(list.get(position).title);
        holder.movielanguage.setText(list.get(position).language);
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/" + list.get(position).image).into(holder.movieimage);

//        if (list.get(position).favb) {
//            holder.favbut.setBackground(  context.getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24,null));
//        } else{
//            holder.favbut.setBackground( context.getResources().getDrawable(R.drawable.ic_baseline_favorite_24,null));
//
//    }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // inner class
    public class MovieViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        TextView moviename;
       ImageView movieimage;
        TextView movielanguage;
       // Button favbut;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviename =  itemView.findViewById(R.id.moviename);
        movieimage =  itemView.findViewById(R.id.movieimage);
            movielanguage = itemView.findViewById(R.id.language);
            //favbut = itemView.findViewById(R.id.favbut);
            itemView.setOnClickListener(this);
//            favbut.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    listener.butClicked(getAdapterPosition());
//                    if (list.get(getAdapterPosition()).favb == true) {
//
//                        favbut.setBackground( context.getResources().getDrawable(R.drawable.ic_baseline_favorite_24,null));
//                    }
//                    else{
//                        favbut.setBackground(  context.getResources().getDrawable(R.drawable.ic_baseline_favorite_border_24,null));
//
//                    }
//
//
//                }
//            });
        }

        @Override
        public void onClick(View view) {
            listener.onClicked( getAdapterPosition());


        }
    }


}
