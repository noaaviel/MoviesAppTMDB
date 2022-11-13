package com.example.moviesapptmdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopMoviesActivity extends AppCompatActivity {
    List<MovieModelClass> topMovieList;
    RecyclerView topRecycler;
    ImageView tmdbLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_movies);
        topMovieList = new ArrayList<>();
        topRecycler = findViewById(R.id.topRecyclerView);
        tmdbLogo = findViewById(R.id.tmdbIMG);

        Glide.with(this).load(R.drawable.thegodfather).into(tmdbLogo);


        Call<MovieList> callTopMovies = AppManager.jsonPopMovies.getTopMovies();
        callTopMovies.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                //successful
                if(!response.isSuccessful()){ //200->300 good otherwise bad
                    //set text to response.code -> http code
                    Log.d("noa","" + response.code());
                    return;
                }
                List<MovieModelClass> topMovies = response.body().movies;
                //   for(MovieModelClass model: popMovies){
                for(int i=0;i<topMovies.size();i++){

                    MovieModelClass model = new MovieModelClass();
                    model.setId(topMovies.get(i).getId());
                    model.setName(topMovies.get(i).getName());
                    model.setImage(topMovies.get(i).getImage());

                    //no reason for this part either why cant
                    topMovieList.add(model);

                }
                PutDataIntoRecyclerView(topMovies);

            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                Log.d("noa",t.getMessage());
            }
        });

    }

    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
        MainActivity.adaptery = new Adaptery(this,movieList);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topRecycler.setLayoutManager(new GridLayoutManager(this,3));

        topRecycler.setAdapter(MainActivity.adaptery);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }



}