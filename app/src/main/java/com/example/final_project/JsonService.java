package com.example.final_project;

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
            System.out.println(page);
            int total_page = jsonObject.getInt("total_pages");
            System.out.println(total_page);
          // if(page <= total_page) {

               for (int i = 0; i < resultArray.length(); i++) {

                   resultArray.getString(i);
                   int jsonid = resultArray.getJSONObject(i).getInt("id");
                   // System.out.println(movies.id);
                   String jsontitle = resultArray.getJSONObject(i).getString("title");
                   //System.out.println(movies.title);
                  String jsonPoster = resultArray.getJSONObject(i).getString("poster_path");
                   //System.out.println(movies.image);
                   String jsonDate = resultArray.getJSONObject(i).getString("release_date");
                   //  System.out.println(movies.date);
                  // list.add(jsonid,jsontitle,jsonPoster,jsonDate);
                   Movies movies = new Movies(jsonid, jsontitle,jsonPoster, jsonDate);
                   list.add(movies);


               }
               System.out.println(list);

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
            System.out.println(movieDetails);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return movieDetails;

    }



}
