package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
        moviesList.setLayoutManager(new LinearLayoutManager(this));
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
                //  Log.d("Donation app submit",query);
//
//                if (!query.isEmpty() ) {
//                    ((MyApp) getApplication()).networkingService.getMovie(query);
//                }
//
//                else {
//                    adapter.list = new ArrayList<>(0);
//                    adapter.notifyDataSetChanged();
//                }
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
      //  ((MyApp) getApplication()).networkingService.gettingDetails(list.get(post).id);
    }

    @Override
    public void gettingJsonIsCompleted(String json) {
      list = JsonService.fromJsonToActivity(json);
        adapter.list = list;
        adapter.notifyDataSetChanged();
    }



}