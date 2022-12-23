package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class DetailMoviePage extends AppCompatActivity implements NetworkingService.NetworkingListener ,
MoviesDBManager.DataBaseListener{
            MovieDetails movieDetails = new MovieDetails();
            TextView overview,websitelink,relstatus,extradetails;
            ImageView imageposter;
            Button addbutton;

    Movies movies;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie_page);
        ((MyApp)getApplication()).moviesDBManager.getDB(this);
        ((MyApp)getApplication()).moviesDBManager.listener = this;
        if( getIntent().hasExtra("title")){
            movies = getIntent().getParcelableExtra("title");
            this.setTitle(movies.title);
         ((MyApp) getApplication()).networkingService.listener = this;
          ((MyApp) getApplication()).networkingService.gettingDetails(movies.id);
          ((MyApp)getApplication()).networkingService.gettingPoster(movies.image);
        }
        overview = findViewById(R.id.overviewtext);
        imageposter = findViewById(R.id.imagepost);
        websitelink = findViewById(R.id.websitelink);
        relstatus = findViewById(R.id.relstatus);
       extradetails = findViewById(R.id.extradetails);
       addbutton=findViewById(R.id.addtofav);
       addbutton.setOnClickListener(new View.OnClickListener() {
           @Override
                                        public void onClick(View view) {
               ((MyApp)getApplication()).moviesDBManager.movieExistCheck(movies.id);

                                        }

          });
       }
void showAlert(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
              builder.setMessage("Are you sure you want to save "+ movies.title +" to your favourites");
              builder.setNegativeButton("No",null);
               builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                     ((MyApp)getApplication()).moviesDBManager.insertNewMovieAsync(movies);
                       Intent in = new Intent(DetailMoviePage.this,SearchActivity.class);
                       startActivity(in);
                   }
               });
              builder.create().show();


}

    @Override
    public void gettingJsonIsCompleted(String json) {
     movieDetails = JsonService.details(json);

    }


    @Override
    public void gettingPosterIsCompleted(Bitmap image) {
        if(image == null){
            imageposter.setImageBitmap(null);
        }
        else{
    imageposter.setImageBitmap(image);
        }
    overview.setText(movieDetails.Overview);
    overview.setMovementMethod(new ScrollingMovementMethod());
    if(movieDetails.homepage.isEmpty()){
        websitelink.setText("NO WEBSITE LINK AVAILABLE");
    }else{
    websitelink.setText(movieDetails.homepage);
    }
    relstatus.setText("Release status: "+movieDetails.Status+"\n"+"Date:"+movies.date);
    extradetails.setText("Runtime :"+movieDetails.runtime +"mins"+"       "+"Language :"+movieDetails.language);
    }


    @Override
    public void insertingMovieCompleted() {
        finish();
    }

    @Override
    public void gettingMovieCompleted(Movies[] list) {

    }

    @Override
    public void movieChecked(int ans) {
        int ex = ans;
        if(ex == 0){
            showAlert();
        }else {
            addbutton.setText("ALREADY ADDED");
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage( movies.title + " Already Exists In Your List");
            builder.setNegativeButton("Ok",null);
            builder.create().show();
        }
    }

    @Override
    public void sorted(Movies[] lis) {

    }

}