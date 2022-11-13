package com.example.moviesapptmdb.Util;

import com.example.moviesapptmdb.MovieList;

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

    //https://api.themoviedb.org/3/authentication/token/new?api_key=3930eda0423c873bc5ce559094909f9d



    /*@Path – variable substitution for the API endpoint. For example movie id will be swapped for{id} in the URL endpoint.

@Query – specifies the query key name with the value of the annotated parameter.

@Body – payload for the POST call
https://guides.codepath.com/android/consuming-apis-with-retrofit

@Header – specifies the header with the value of the annotated


@Body - Sends Java objects as request body.
@Url - use dynamic URLs.
@Query - We can simply add a method parameter with @Query() and a query parameter name, describing the type. To URL encode a query use the form: @Query(value = "auth_token",encoded = true) String auth_token
@Field - send data as form-urlencoded. This requires a @FormUrlEncoded annotation attached with the method. The @Field parameter works only with a POST
Note: @Field requires a mandatory parameter. In cases when @Field is optional, we can use @Query instead and pass a null value.
https://www.digitalocean.com/community/tutorials/retrofit-android-example-tutorial




*/
    

}
