package com.example.moviesapptmdb.Util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.moviesapptmdb.MovieModelClass;
import com.example.moviesapptmdb.R;

import java.util.List;

public class Adaptery extends RecyclerView.Adapter<Adaptery.MyViewHolder> {

    private Context mContext;
    private List<MovieModelClass> mData;
    private static OnMovieListener onMovieListener;



  /*  public Adaptery(Context mContext, List<MovieModelClass> mData) {
        this.mContext = mContext;
        this.mData = mData;
        //this.onMovieListener = onMovieListener;
    }*/

    public Adaptery(Context mContext, OnMovieListener onMovieListener,List<MovieModelClass> mData) {
        this.mContext = mContext;
        this.onMovieListener = onMovieListener;
        this.mData = mData;
    }



    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        v = layoutInflater.inflate(R.layout.movie_item,parent,false);


        return new MyViewHolder(v,onMovieListener);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //here we are going to bind the data
        holder.id.setText(mData.get(position).getId());
        holder.name.setText(mData.get(position).getName());

        //getting the link for the image-> we get from json incomplete url
        //we need to build it using the general directory of images from tmdb
        //[https://image.tmdb.org/t/p/w500]

        //using Glide library to import image by url
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w500"+mData.get(position).getImage()).into(holder.image);


    }

    @Override
    public int getItemCount() {
        return mData.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView id,name;
        ImageView image;
        OnMovieListener onMovieListener;

        //Holds a card view

        public MyViewHolder(@NonNull View itemView,OnMovieListener onMovieListener) {
            super(itemView);
            this.onMovieListener = onMovieListener;
            itemView.setOnClickListener(this);
            init();
        }

        private void init() {
            id = itemView.findViewById(R.id.id_text);
            name = itemView.findViewById(R.id.name_text);
            image = itemView.findViewById(R.id.imageView);
        }


        @Override
        public void onClick(View v) {
            onMovieListener.onMovieClick(v,this.getAdapterPosition());
        }
    }

}