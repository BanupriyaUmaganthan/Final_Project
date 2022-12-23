package com.example.final_project;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface MovieDAO {
    @Insert
    void addFavMovie(Movies m);

    @Query("select * from Movies")
    Movies[] getAllFavMovie();

    @Delete
    void deleteAFavMovie(Movies dm);


    @Query("SELECT * FROM Movies WHERE id = :mid")
    int isDataExist(int mid);

    @Query("select * from Movies[] where language = :lan")
    Movies[] sortbyLanguage(String lan);


}
