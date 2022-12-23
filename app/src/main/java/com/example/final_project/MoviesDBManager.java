package com.example.final_project;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import androidx.room.Room;

public class MoviesDBManager {

    interface DataBaseListener{
        void insertingMovieCompleted();
        void gettingMovieCompleted(Movies[] list);
    }

    DataBaseListener listener;

    MovieDatabase movieDatabase;
    Handler dbHandler = new Handler(Looper.getMainLooper());

    MovieDatabase getDB(Context context){
        if (movieDatabase == null)
            movieDatabase = Room.databaseBuilder(context,MovieDatabase.class,"movies_db").build();

        return movieDatabase;
    }

    void insertNewMovieAsync(Movies m){

        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDatabase.getDao().addFavMovie(m);
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.insertingMovieCompleted();
                    }
                });
            }
        });
    }

    void getAllMovies(){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                Movies[] list = movieDatabase.getDao().getAllFavMovie();
                dbHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        // notify main thread
                        listener.gettingMovieCompleted(list);
                    }
                });
            }
        });
    }

    void delAMovie(Movies mo){
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDatabase.getDao().deleteAFavMovie(mo);
            }
        });

    }



}
