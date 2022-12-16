package com.example.final_project;

public class MovieDetails {
    String Overview;
    String Status;
    int runtime;
    String language;

    public MovieDetails(){

    }

    public MovieDetails(String overview, String status, int runtime, String language) {
        Overview = overview;
        Status = status;
        this.runtime = runtime;
        this.language = language;
    }
}
