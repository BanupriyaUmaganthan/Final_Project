package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements MainActivityAdapter.ItemListener,NetworkingService.NetworkingListener {
    Button startbutton;
    RecyclerView piclist;
    MainActivityAdapter adapter;
    ArrayList<poster> plist = new ArrayList<>(0);
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Movie APP");
        startbutton = findViewById(R.id.startbutton);

        piclist = findViewById(R.id.pic_list);
        adapter = new MainActivityAdapter(this,plist);
        adapter.listener=this;
        ((MyApp) getApplication()).networkingService.listener = this;
        ((MyApp) getApplication()).networkingService.getpic();
        piclist.setAdapter(adapter);
        piclist.setLayoutManager(new GridLayoutManager(this,2));







        startbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SearchActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void gettingJsonIsCompleted(String json) {
    plist=JsonService.post(json);
        adapter.list = plist;
        adapter.notifyDataSetChanged();
    }

    @Override
    public void gettingPosterIsCompleted(Bitmap image) {

    }

    @Override
    public void onClicked(int post) {

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater mi = new MenuInflater(MainActivity.this);
        mi.inflate(R.menu.moviemenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.favorites:
                Intent favintent = new Intent(MainActivity.this,FavoriteMovies.class);
                startActivity(favintent);

                break;


        }

        return true;
    }

}