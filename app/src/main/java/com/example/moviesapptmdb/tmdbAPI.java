package com.example.moviesapptmdb;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface tmdbAPI {
    //annotate for GET popular movies

    // popular is the relative url:3/movie/popular/?api_key=3930eda0423c873bc5ce559094909f9d
    @GET("3/movie/popular")
    Call<MovieList> getPopularMovies(); //will return an object of type MovieModelClass
    //encapsulates a request and response for 1 get
}
