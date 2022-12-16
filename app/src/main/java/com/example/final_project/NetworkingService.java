package com.example.final_project;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;


public class NetworkingService {


    interface NetworkingListener{
        void gettingJsonIsCompleted(String json);
       // void gettingImageIsCompleted(Bitmap image);
    }
    NetworkingListener listener;
    Handler handler = new Handler(Looper.getMainLooper());

  String movieURL="https://api.themoviedb.org/3/search/movie?api_key=741b0f74b1f6d573e68ff80851725422&query=";
  String movieDetailURL="https://api.themoviedb.org/3/movie/";
  String API = "?api_key=741b0f74b1f6d573e68ff80851725422";

    public void getMovie(String query){
        String url = movieURL + query;
        connect(url);
    }
    public void gettingDetails(int id) {
        String url2 = movieDetailURL+id+API;
        connect(url2);

    }


   public void connect(String urlstring){
       MyApp.executorService.execute(new Runnable() {
           @Override
           public void run() {
               HttpURLConnection urlConnection = null;
               try {
                   int val = 0;
                   URL url = new URL(urlstring);
                   urlConnection = (HttpURLConnection) url.openConnection();
                   InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                   StringBuffer buffer = new StringBuffer();
                   while ((val = in.read()) != -1) {
                       buffer.append((char) val);
                   }


                   handler.post(new Runnable() {
                       @Override
                       public void run() {

                           listener.gettingJsonIsCompleted(buffer.toString());
                          // System.out.println(buffer.toString());

                       }
                   });
               } catch (MalformedURLException e) {
                   e.printStackTrace();
               } catch (IOException e) {
                   e.printStackTrace();
               } finally {
                   urlConnection.disconnect();
               }

           }
       });



    }
}

