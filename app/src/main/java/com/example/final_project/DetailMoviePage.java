package com.example.final_project;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailMoviePage extends AppCompatActivity implements NetworkingService.NetworkingListener {
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
                                            showAlert();
                                        }

          });
       }
void showAlert(){
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
              builder.setMessage("Are you sure you want to save "+ movies.title +" to your favourites");
              builder.setNegativeButton("No",null);
               builder.setPositiveButton("Yes",null);
                    //   new DialogInterface.OnClickListener() {
//                   public void onClick(DialogInterface dialog, int id) {
//                       ((MyApp)getApplication()).dbManager.insertNewCityAsync(city);
//                   }
//               });
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


}