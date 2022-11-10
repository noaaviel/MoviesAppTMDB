package com.example.moviesapptmdb;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface tmdbAPI {
    //annotate for GET popular movies

    // popular is the relative url:3/movie/popular/?api_key=3930eda0423c873bc5ce559094909f9d
    @GET("3/movie/popular")
    Call<MovieList> getPopularMovies(); //will return an object of type MovieModelClass
    //encapsulates a request and response for 1 get
    @GET("3/movie/top_rated")
    Call<MovieList> getTopMovies();

    //https://api.themoviedb.org/3/movie/{movie_id}/rating?api_key=3930eda0423c873bc5ce559094909f9d

    //https://api.themoviedb.org/3/authentication/token/new?api_key=<<api_key>>
    @GET("3/authentication/token/new")
    Call<SessionKey> getSessionKey();

    @FormUrlEncoded // annotation used in POST type requests
    @Headers("Content-Type: application/json")
    @POST("3/movie/{movie_id}/rating")
    public void RateMovie(
            @Path("movie_id") int id,
            @Query("api_key") String api_key,
            @Query("guest_session_id") String guest_session_id,
            @Field("value") double value,
            Callback<RateMovieResponse> callback //callback is used to get the response from api
            //https://abhiandroid.com/programming/retrofit
            );
    

}
