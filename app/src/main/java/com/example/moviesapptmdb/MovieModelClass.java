package com.example.moviesapptmdb;

import com.google.gson.annotations.SerializedName;

//uses for POST method-> getting the movie by JSON structure in JSON array
//if needed -> the name of the variable differs from the JSON key we can use annotation
// @serializedName("JSON KEY")
public class MovieModelClass {

    @SerializedName("vote_average")
    String id;
    @SerializedName("title")
    String name;
    @SerializedName("poster_path")
    String image;

    public MovieModelClass(String id, String name, String image) {

        this.id = id;
        this.name = name;
        this.image = image;
    }

    public MovieModelClass() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
