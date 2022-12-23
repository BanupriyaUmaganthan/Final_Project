package com.example.final_project;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
       void gettingPosterIsCompleted(Bitmap image);
    }

    NetworkingListener listener;
    Handler handler = new Handler(Looper.getMainLooper());

  String movieURL="https://api.themoviedb.org/3/search/movie?api_key=741b0f74b1f6d573e68ff80851725422&query=";
  String movieDetailURL="https://api.themoviedb.org/3/movie/";
  String API = "?api_key=741b0f74b1f6d573e68ff80851725422";
    String posterURL ="https://image.tmdb.org/t/p/original/";


    public void getMovie(String query){
        String url = movieURL + query;
        connect(url);
    }
    public void gettingDetails(int id) {
        String url2 = movieDetailURL+id+API;
        connect(url2);

    }
    public void getpic(){
        String posterUrl="https://api.themoviedb.org/3/movie/upcoming?api_key=741b0f74b1f6d573e68ff80851725422&language=en-US&page=1";
        connect(posterUrl);
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


        void gettingPoster(String posterpath) {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL url = new URL(posterURL + posterpath);
                    InputStream in = url.openStream();
                    Bitmap imageData = BitmapFactory.decodeStream(in);

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.gettingPosterIsCompleted(imageData);
                            System.out.println(imageData);
                        }
                    });
                } catch (MalformedURLException e) {
                    e.printStackTrace();

                } catch (IOException e) {
                    e.printStackTrace();
                }


            }
        });
    }
}

