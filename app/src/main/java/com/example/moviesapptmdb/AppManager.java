package com.example.moviesapptmdb;

import android.app.Application;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AppManager extends Application {

    private static Retrofit retrofit= new Retrofit.Builder().baseUrl("https://api.themoviedb.org/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(new OkHttpClient().newBuilder().addInterceptor(newInterceptor()).build())
            .build();

    //retrofit =
       // Log.d("noa","created retrofit");

    // now with retrofit we can create jsonplaceholder
    static tmdbAPI jsonPopMovies = retrofit.create(tmdbAPI.class);

    private static Interceptor newInterceptor() {

        return new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original =chain.request();
                HttpUrl httpUrl = original.url().newBuilder().addQueryParameter("api_key","3930eda0423c873bc5ce559094909f9d").build();
                original = original.newBuilder().url(httpUrl).build();
                return chain.proceed(original);
            }
        };

    }

}
