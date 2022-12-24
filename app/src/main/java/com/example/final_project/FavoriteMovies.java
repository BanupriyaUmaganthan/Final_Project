package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
        favmoviesList.setLayoutManager(new GridLayoutManager(this,2));

        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
                int position  = viewHolder.getAdapterPosition();
                AlertDialog.Builder builder = new AlertDialog.Builder(FavoriteMovies.this);
                builder.setMessage("Are you sure you want to remove " + flist.get(position).title +" from favourites");
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        adapter.notifyDataSetChanged();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Remove swiped item from list and notify the RecyclerView
                        ((MyApp)getApplication()).moviesDBManager.delAMovie(flist.remove(position));
                        adapter.notifyDataSetChanged();

                    }
                });
                builder.create().show();

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
    public void movieChecked(int ans) {

    }

    @Override
    public void sorted(Movies[] lis) {
        flist = new ArrayList( Arrays.asList(lis));
        adapter.list = flist;
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onClicked(int post) {
        Intent i = new Intent(this,FavMovieDetail.class);
        i.putExtra("title", (Parcelable) flist.get(post));
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = new MenuInflater(FavoriteMovies.this);
        mi.inflate(R.menu.languagemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.en:
                String lang = "en";
                ((MyApp)getApplication()).moviesDBManager.sortlanguage(lang);

                break;
            case R.id.fr:
                String l1 = "fr";
                ((MyApp)getApplication()).moviesDBManager.sortlanguage(l1);
                break;
            case R.id.ta:
                String l2 = "ta";
                ((MyApp)getApplication()).moviesDBManager.sortlanguage(l2);
                break;
            case R.id.ml:
                String l3 = "ml";
                ((MyApp)getApplication()).moviesDBManager.sortlanguage(l3);
                break;
            case R.id.te:
                String l4 = "te";
                ((MyApp)getApplication()).moviesDBManager.sortlanguage(l4);
                break;
            case R.id.hi:
                String l5 = "hi";
                ((MyApp)getApplication()).moviesDBManager.sortlanguage(l5);
                break;
            case R.id.Ja:
                String l6 = "ja";
                ((MyApp)getApplication()).moviesDBManager.sortlanguage(l6);
                break;
            case R.id.all:
                ((MyApp)getApplication()).moviesDBManager.getAllMovies();
                break;

        }

        return true;
    }
}