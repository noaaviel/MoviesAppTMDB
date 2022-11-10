package com.example.moviesapptmdb;

import com.google.gson.annotations.SerializedName;

public class PostModel {

    @SerializedName("id")
    int id; //movie id

    @SerializedName("value")
    String bodyPost; //post body contains a decimal number as review

    public PostModel(int id, String bodyPost) {
        this.id = id;
        this.bodyPost = bodyPost;
    }

    public int getId() {
        return id;
    }

    public String getBodyPost() {
        return bodyPost;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setBodyPost(String bodyPost) {
        this.bodyPost = bodyPost;
    }
}
