package com.example.moviesapptmdb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnMovieListener{

    private static String JSON_URL= "https://api.themoviedb.org/3/movie/popular?api_key=3930eda0423c873bc5ce559094909f9d";

    //json: https://run.mocky.io/v3/669bfc8d-6f62-4206-b7d1-ab3f5e6d4167
    //json popular movies: https://api.themoviedb.org/3/movie/popular?api_key=3930eda0423c873bc5ce559094909f9d
    List<MovieModelClass> popMovieList;

    RecyclerView recyclerView;
    ImageView imageView;
    TextView topMoviesText;
    Button topMovies;
    SessionKey sessionKey;
    static Adaptery adaptery;

    public List<MovieModelClass> getPopMovieList() {
        return popMovieList;
    }

    public void setPopMovieList(List<MovieModelClass> popMovieList) {
        this.popMovieList = popMovieList;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        popMovieList = new ArrayList<>();

        topMovies = findViewById(R.id.BTN_top_movies);
        topMoviesText= findViewById(R.id.TXT_top_movies);
        recyclerView = findViewById(R.id.recyclerView);



       // imageView = findViewById(R.id.mainImg);
       // Glide.with(this).load(R.drawable.thegodfather).into(imageView);

        //base url: https://api.themoviedb.org/

        //execute:
        Call<MovieList> callPopMovies = AppManager.jsonPopMovies.getPopularMovies();
        callPopMovies.enqueue(new Callback<MovieList>() {
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

                    MovieModelClass model = new MovieModelClass();
                    model.setId(popMovies.get(i).getId());
                    model.setName(popMovies.get(i).getName());
                    model.setImage(popMovies.get(i).getImage());

                    //no reason for this part either why cant
                    popMovieList.add(model);

                }
                PutDataIntoRecyclerView(popMovieList);

            }

            @Override
            public void onFailure(Call<MovieList> call, Throwable t) {
                //something bad happened t.getMessage() will indicate what
                Log.d("noa",t.getMessage());

            }
        });

        topMovies.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, TopMoviesActivity.class));
            }
        });

     //   sessionKey=new SessionKey(false,"a","a");

  //      Call<SessionKey> sessionKeyCall = AppManager.jsonPopMovies.getSessionKey();
    //    sessionKeyCall.enqueue(new Callback<SessionKey>() {
      //      @Override
        //    public void onResponse(Call<SessionKey> call, Response<SessionKey> response) {
                //successful
          //      if(!response.isSuccessful()){ //200->300 good otherwise bad
                    //set text to response.code -> http code
            //        Log.d("noa","" + response.code());
              //      return;
                //}
                //sessionKey =new SessionKey(response.body().success,response.body().request_token,response.body().expires_at);
                //String guest_session_id = response.body().guest_session_id;
                //sessionKey.setExpires_at(response.body().expires_at); // this is ok
                //sessionKey.setRequest_token(response.body().request_token); //this comes null in response body
                //sessionKey.setSuccess(response.body().success);//this shows success
                //Log.d("noa",response.body().request_token);

                //need to go here and authenticate token
                // https://www.themoviedb.org/authenticate/{REQUEST_TOKEN}

                //create session id - post method

                //Call<RateMovieResponse> rateMovieResponseCall = AppManager.jsonPopMovies.RateMovie(663712,
                  //      "3930eda0423c873bc5ce559094909f9d", sessionKey.getRequest_token(), 8.5);
                //rateMovieResponseCall.enqueue(new Callback<RateMovieResponse>() {
                  //  @Override
                    //public void onResponse(Call<RateMovieResponse> call, Response<RateMovieResponse> response) {
                      //  if(!response.isSuccessful()){ //200->300 good otherwise bad
                            //set text to response.code -> http code
                        //    Log.d("noa","rate response " + response.code());//rate response401
                          //  return;
                        //}

                    //}

                    //@Override
                    //public void onFailure(Call<RateMovieResponse> call, Throwable t) {
                      //  Log.d("noa",t.getMessage());
                    //}
                //});



            //}

            //@Override
            //public void onFailure(Call<SessionKey> call, Throwable t) {
              //  Log.d("noa",t.getMessage());
            //}
       // });






        //663712 is Terrifier 2
    /*    AppManager.jsonPopMovies.RateMovie(663712, "3930eda0423c873bc5ce559094909f9d", sessionKey.getRequest_token(), 8.5, new Callback<RateMovieResponse>() {
            @Override
            public void onResponse(Call<RateMovieResponse> call, Response<RateMovieResponse> response) {
                Toast.makeText(MainActivity.this, response.body().getStatus_message(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<RateMovieResponse> call, Throwable t) {
                Log.d("noa",t.getMessage());
            }
        });*/


        //GetData getData = new GetData();
        //getData.execute();
    }

    private void PutDataIntoRecyclerView(List<MovieModelClass> movieList){
         adaptery = new Adaptery(this,this::onMovieClick,movieList);
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setLayoutManager(new GridLayoutManager(this,3));

        recyclerView.setAdapter(adaptery);
    }

    @Override
    public void onMovieClick(View v, int position) {
        Toast.makeText(MainActivity.this,popMovieList.get(position).getName(), Toast.LENGTH_SHORT).show();
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