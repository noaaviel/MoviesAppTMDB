package com.example.moviesapptmdb;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieList {
    @SerializedName("results")
    List<MovieModelClass> movies;
}
