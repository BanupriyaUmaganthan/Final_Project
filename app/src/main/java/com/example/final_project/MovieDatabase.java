package com.example.final_project;

import androidx.room.Database;
import androidx.room.RoomDatabase;
@Database(entities = {Movies.class},version = 1)
public abstract class MovieDatabase extends RoomDatabase {
    public abstract MovieDAO getDao();
}
