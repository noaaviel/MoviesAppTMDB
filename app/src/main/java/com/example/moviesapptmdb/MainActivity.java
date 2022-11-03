package com.example.moviesapptmdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static String JSON_URL= "https://api.themoviedb.org/3/movie/popular?api_key=3930eda0423c873bc5ce559094909f9d";

    //json: https://run.mocky.io/v3/669bfc8d-6f62-4206-b7d1-ab3f5e6d4167
    //json popular movies: https://api.themoviedb.org/3/movie/popular?api_key=3930eda0423c873bc5ce559094909f9d
    List<MovieModelClass> movieList;
    RecyclerView recyclerView;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerView);
        imageView = findViewById(R.id.mainImg);
        Glide.with(this).load(R.drawable.thegodfather).into(imageView);

        //base url: https://api.themoviedb.org/

        //execute:
        Call<MovieList> call = AppManager.jsonPopMovies.getPopularMovies();
        call.enqueue(new Callback<MovieList>() {
            @Override
            public void onResponse(Call<MovieList> call, Response<MovieList> response) {
                //successful
                if(!response.isSuccessful()){ //200->300 good otherwise bad
                    //set text to response.code -> http code
                    Log.d("noa","" + response.code());
                    return;
                }
                List<MovieModelClass> popMovies = response.body().movies;
             //   for(MovieModelClass model: popMovies){
                for(int i=0;i<popMovies.size();i++){
                   /* String id = "";
                    String name = "";
                    String image = "";
                    id+= model.getId();
                    name+= model.getName();
                    image+= model.getImage();*/

                    //אין לזה הגיוןןןןןן בכלללללללללללללל
                    MovieModelClass model = new MovieModelClass();
                    model.setId(popMovies.get(i).getId());
                    model.setName(popMovies.get(i).getName());
                    model.setImage(popMovies.get(i).getImage());

                    //no reason for this part either why cant
                    movieList.add(model);

                }
                PutDataIntoRecyclerView(movieList);

            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //something bad happened t.getMessage() will indicate what
                Log.d("noa",t.getMessage());

            }
        });

        //GetData getData = new GetData();
        //getData.execute();
    }



    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
        Adaptery adaptery = new Adaptery(this,movieList);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        recyclerView.setAdapter(adaptery);
    }

    //getting data through the task -> change to retrofit web services REST API
  /*  public class GetData extends AsyncTask<String,String,String> {

        @Override
        protected String doInBackground(String... strings) {
            //getting strings from json
            String current = " ";
            try {
                URL url;
                HttpURLConnection httpURLConnection = null;

                try {
                    url = new URL(JSON_URL);
                    httpURLConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = httpURLConnection.getInputStream();
                    InputStreamReader reader = new InputStreamReader(is);

                    int data = reader.read();
                    while (data != -1) {
                        current += (char) data;
                        data = reader.read();
                    }

                    return current;


                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    if (httpURLConnection != null) {
                        httpURLConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }


            return current;
        }

        //here string s will be current that is returned from func above
        @Override
        protected void onPostExecute(String s) {
            //super.onPostExecute(s);
            //we have gotten the string and we will pass it to the post execute which will
            //analyze it and will retrieve it from the json and [ass it to model->adapter->view

            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONArray jsonArray = jsonObject.getJSONArray("results");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                    MovieModelClass model = new MovieModelClass();
                    //FROM MODEL TO LIST
                    model.setId(jsonObject1.getString("vote_average"));
                    model.setName(jsonObject1.getString("title"));
                    model.setImage(jsonObject1.getString("poster_path"));

                    movieList.add(model);
                }

            } catch (JSONException e) {
                e.printStackTrace();
                Log.d("noa","failed json object in catch");
            }

            PutDataIntoRecyclerView(movieList);


        }
    }

    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
        Adaptery adaptery = new Adaptery(this,movieList);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        recyclerView.setAdapter(adaptery);
    }*/

}