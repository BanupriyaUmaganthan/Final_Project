package com.example.final_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends
        RecyclerView.Adapter<RecyclerAdapter.MovieViewHolder> {



    interface ItemListener{
        void onClicked(int post);
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
     // holder.movieimage.setImageResource(Integer.parseInt(list.get(position).image));
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
      //  ImageView movieimage;
        TextView movielanguage;
        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            moviename =  itemView.findViewById(R.id.moviename);
       //   movieimage =  itemView.findViewById(R.id.movieimage);
            movielanguage = itemView.findViewById(R.id.language);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClicked( getAdapterPosition());

        }
    }


}
