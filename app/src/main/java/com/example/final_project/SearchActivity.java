package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;
import android.os.PersistableBundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;


public class SearchActivity extends AppCompatActivity implements RecyclerAdapter.ItemListener,NetworkingService.NetworkingListener {
    RecyclerView moviesList;
    RecyclerAdapter adapter;
    ArrayList<Movies> list = new ArrayList<>(0);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        ((MyApp) getApplication()).networkingService.listener = this;
        moviesList = findViewById(R.id.movie_list);
        adapter = new RecyclerAdapter(this,list);
        setTitle("Search For Movies....");
        adapter.listener = this;
      //  ((MyApp)getApplication()).dbManager.listener = this;
      //  ((MyApp)getApplication()).dbManager.getDB(this);
        moviesList.setAdapter(adapter);
       moviesList.setLayoutManager(new GridLayoutManager(this,3));


    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.movie_search_menu, menu);
        MenuItem searchViewMenu = menu.findItem(R.id.movie_searchview);

        SearchView searchView = (SearchView) searchViewMenu.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                if (newText.length() >= 2){
                    ((MyApp) getApplication()).networkingService.getMovie(newText);
                }
               else {

                    adapter.list = new ArrayList<>(0);
                    adapter.notifyDataSetChanged();
                }

               return false;
            }
        });


        return true;
    }

    @Override
    public void onClicked(int post) {

        Intent i = new Intent(this,DetailMoviePage.class);
        i.putExtra("title", (Parcelable) list.get(post));
        startActivity(i);

    }

    @Override
    public void butClicked(int post) {
        if (!list.get(post).favb) {
            list.get(post).favb = true;
            //((MyApp)getApplication()).dbManager.insertNewCityAsync(city);

            Toast.makeText(this, "Added", Toast.LENGTH_SHORT).show();

//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Are you sure you want to save " + list.get(post).title + " to your favourites");
//            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialogInterface, int i) {
//                    adapter.list = list;
//                    adapter.notifyDataSetChanged();
//
//                }
//            });
//            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//
//                    //((MyApp)getApplication()).dbManager.insertNewCityAsync(city);
//
//                }
//            });
//            builder.create().show();


        }else{
            list.get(post).favb = false;
            //((MyApp)getApplication()).dbManager.insertNewCityAsync(city);

            Toast.makeText(this, "Removed", Toast.LENGTH_SHORT).show();


//            AlertDialog.Builder builder = new AlertDialog.Builder(this);
//            builder.setMessage("Are you sure you want to remove " + list.get(post).title + " from your favourites");
//            builder.setNegativeButton("No", null);
//            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                public void onClick(DialogInterface dialog, int id) {
//
//                    adapter.list = new ArrayList<>(0);
//                    adapter.notifyDataSetChanged();
//                    //((MyApp)getApplication()).dbManager.insertNewCityAsync(city);
//
//                }
//            });
//            builder.create().show();
//
//               }
        }}

    @Override
    public void gettingJsonIsCompleted(String json) {
        list = JsonService.fromJsonToActivity(json);
        if(list.isEmpty()){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("The Movie You Looking For Is Not Available");
            builder.setNegativeButton("Ok",null);
            builder.create().show();
           // Toast.makeText(this, "Sorry The Movie You Looking For Is Not Available", Toast.LENGTH_SHORT).show();
        }
            adapter.list = list;
            adapter.notifyDataSetChanged();

    }




    @Override
    public void gettingPosterIsCompleted(Bitmap image) {


    }


}