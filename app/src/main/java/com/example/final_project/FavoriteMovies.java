package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class FavoriteMovies extends AppCompatActivity implements MoviesDBManager.DataBaseListener, RecyclerAdapter.ItemListener{
    RecyclerView favmoviesList;
    RecyclerAdapter adapter;
    ArrayList<Movies> flist = new ArrayList<>(0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        setTitle("Favorite Movie list");
        favmoviesList = findViewById(R.id.favMovielist);
        adapter = new RecyclerAdapter(this, flist);
        adapter.listener = this;
        favmoviesList.setAdapter(adapter);
        favmoviesList.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                Toast.makeText(FavoriteMovies.this, " Deleted ", Toast.LENGTH_SHORT).show();
                //Remove swiped item from list and notify the RecyclerView
                int position = viewHolder.getAdapterPosition();
                ((MyApp)getApplication()).moviesDBManager.delAMovie(flist.remove(position));
                adapter.notifyDataSetChanged();

            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(favmoviesList);




    }

    @Override
    protected void onResume() {
        super.onResume();
        ((MyApp)getApplication()).moviesDBManager.getDB(this);
        ((MyApp)getApplication()).moviesDBManager.getAllMovies();
        ((MyApp)getApplication()).moviesDBManager.listener = this;
    }

    @Override
    public void insertingMovieCompleted() {

    }

    @Override
    public void gettingMovieCompleted(Movies[] list) {
        flist = new ArrayList( Arrays.asList(list));
        adapter.list = flist;
        adapter.notifyDataSetChanged();

    }








    @Override
    public void onClicked(int post) {
        Intent i = new Intent(this,DetailMoviePage.class);
        i.putExtra("title", (Parcelable) flist.get(post));
        startActivity(i);
    }

    @Override
    public void butClicked(int post) {

    }
}