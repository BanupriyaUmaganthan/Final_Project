package com.example.final_project;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp extends Application {
    static ExecutorService executorService = Executors.newFixedThreadPool(4);
    NetworkingService networkingService = new NetworkingService();

    MoviesDBManager moviesDBManager = new MoviesDBManager();
}
