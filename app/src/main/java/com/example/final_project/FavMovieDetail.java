package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.ImageView;
import android.widget.TextView;

public class FavMovieDetail extends AppCompatActivity implements NetworkingService.NetworkingListener {
    MovieDetails movieDetails = new MovieDetails();
    TextView foverview,fwebsitelink,frelstatus,fextradetails;
    ImageView fimageposter;
    Movies movies;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_movie_detail);
        if( getIntent().hasExtra("title")){
            movies = getIntent().getParcelableExtra("title");
            this.setTitle(movies.title);
            ((MyApp) getApplication()).networkingService.listener = this;
            ((MyApp) getApplication()).networkingService.gettingDetails(movies.id);
            ((MyApp)getApplication()).networkingService.gettingPoster(movies.image);
        }
        foverview = findViewById(R.id.foverviewtext);
        fimageposter = findViewById(R.id.faimagepost);
        fwebsitelink = findViewById(R.id.fwebsitelink);
        frelstatus = findViewById(R.id.frelstatus);
        fextradetails = findViewById(R.id.faextradetails);
    }

    @Override
    public void gettingJsonIsCompleted(String json) {
        movieDetails = JsonService.details(json);
    }

    @Override
    public void gettingPosterIsCompleted(Bitmap image) {
        if(image == null){
            fimageposter.setImageBitmap(null);
        }
        else{
            fimageposter.setImageBitmap(image);
        }
        foverview.setText(movieDetails.Overview);
        foverview.setMovementMethod(new ScrollingMovementMethod());
        if(movieDetails.homepage.isEmpty()){
            fwebsitelink.setText("NO WEBSITE LINK AVAILABLE");
        }else{
            fwebsitelink.setText(movieDetails.homepage);
        }
        frelstatus.setText("Release status: "+movieDetails.Status+"\n"+"Date:"+movies.date);
        fextradetails.setText("Runtime :"+movieDetails.runtime +"mins"+"       "+"Language :"+movieDetails.language);
    }

    }
