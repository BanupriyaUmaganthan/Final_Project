package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search For Movies....");
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


                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  Log.d("Donation app change",newText);

//                if (newText.length() >= 3){
//                    ((MyApp) getApplication()).networkingService.getAllCities(newText);
//                }
//                else {
//                    adapter.list = new ArrayList<>(0);
//                    adapter.notifyDataSetChanged();
//                }

                return false;
            }
        });


        return true;
    }
}