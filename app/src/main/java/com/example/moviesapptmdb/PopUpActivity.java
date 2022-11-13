package com.example.moviesapptmdb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class PopUpActivity extends AppCompatActivity {

    TextView id,name;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        init();
        id.setText(MainActivity.id);
        name.setText(MainActivity.name);
        Glide.with(this).load("https://image.tmdb.org/t/p/w500"+MainActivity.img).into(image);
    }

    private void init() {
        id = findViewById(R.id.id_text);
        name = findViewById(R.id.name_text);
        image =findViewById(R.id.imageView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}