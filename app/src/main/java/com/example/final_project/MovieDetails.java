package com.example.final_project;

public class MovieDetails {
    String Overview;
    String Status;
    int runtime;
    String language;
    String homepage;

    public MovieDetails(){

    }

    public MovieDetails(String overview, String status, int runtime, String language,String homepage) {
        Overview = overview;
        Status = status;
        this.runtime = runtime;
        this.language = language;
        this.homepage = homepage;
    }
}
