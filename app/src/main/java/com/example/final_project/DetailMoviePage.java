package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class DetailMoviePage extends AppCompatActivity implements NetworkingService.NetworkingListener {
            MovieDetails movieDetails = new MovieDetails();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_page);
        if( getIntent().hasExtra("title")){
            Movies movies = getIntent().getParcelableExtra("title");
            this.setTitle(movies.title);
         ((MyApp) getApplication()).networkingService.listener = this;
          ((MyApp) getApplication()).networkingService.gettingDetails(movies.id);

        }
    }
    @Override
    public void gettingJsonIsCompleted(String json) {
     movieDetails    = JsonService.details(json);
    //    ((MyApp) getApplication()).networkingService.gettingImage(wo.icon);

    }
}