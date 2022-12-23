package com.example.final_project;

import android.widget.Toast;

import androidx.lifecycle.viewmodel.CreationExtras;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class JsonService {
   // static ArrayList<Movies> list;
   static ArrayList<Movies> fromJsonToActivity(String jsonString){
        ArrayList<Movies> list = new ArrayList<>(0);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray resultArray = jsonObject.getJSONArray("results");
            int page = jsonObject.getInt("page");
            int total_page = jsonObject.getInt("total_pages");
          // if(page <= total_page) {

                for (int i = 0; i < resultArray.length(); i++) {

                    resultArray.getString(i);
                    int jsonid = resultArray.getJSONObject(i).getInt("id");
                    String jsontitle = resultArray.getJSONObject(i).getString("title");
                    String jsonPoster = resultArray.getJSONObject(i).getString("poster_path");
                    String jsonDate = resultArray.getJSONObject(i).getString("release_date");
                    String jsonLanguage = resultArray.getJSONObject(i).getString("original_language");
                    Movies movies = new Movies(jsonid, jsontitle, jsonPoster, jsonDate,jsonLanguage,false);
                    list.add(movies);


            }
               //}else
           //{

           //}
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return list;

    }



    static MovieDetails details(String jsonString){
        MovieDetails movieDetails = new MovieDetails();

        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            movieDetails.Overview =  jsonObject.getString("overview");
            movieDetails.Status=jsonObject.getString("status");
            movieDetails.language=jsonObject.getString("original_language");
            movieDetails.runtime=jsonObject.getInt("runtime");
            movieDetails.homepage=jsonObject.getString("homepage");


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieDetails;

    }


    static ArrayList<poster> post(String jsonString){
        ArrayList<poster> plist = new ArrayList<>(0);
        try {
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray resultArray = jsonObject.getJSONArray("results");

            for (int i = 0; i < resultArray.length(); i++) {

                resultArray.getString(i);

                String Poster = resultArray.getJSONObject(i).getString("poster_path");

                poster p = new poster(Poster);
                        plist.add(p);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return plist;

    }


    }


