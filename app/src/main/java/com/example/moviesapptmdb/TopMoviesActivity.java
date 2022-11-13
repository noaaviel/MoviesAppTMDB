package com.example.moviesapptmdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.moviesapptmdb.Util.Adaptery;
import com.example.moviesapptmdb.Util.OnMovieListener;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TopMoviesActivity extends AppCompatActivity implements OnMovieListener {
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
        MainActivity.adaptery = new Adaptery(this,this::onMovieClick,movieList);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        topRecycler.setLayoutManager(new GridLayoutManager(this,3));

        topRecycler.setAdapter(MainActivity.adaptery);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }


    @Override
    public void onMovieClick(View v, int position) {
        Toast.makeText(TopMoviesActivity.this,topMovieList.get(position).getName(), Toast.LENGTH_SHORT).show();
        MainActivity.id= topMovieList.get(position).getId();
        MainActivity.name = topMovieList.get(position).getName();
        MainActivity.img = topMovieList.get(position).getImage();
        startActivity(new Intent(TopMoviesActivity.this, PopUpActivity.class));
    }
}