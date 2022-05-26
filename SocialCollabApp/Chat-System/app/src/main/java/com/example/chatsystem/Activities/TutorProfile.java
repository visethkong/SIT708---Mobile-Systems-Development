package com.example.chatsystem.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Pair;

import com.example.chatsystem.R;
import com.example.chatsystem.databinding.ActivityTutorProfileBinding;
import com.taufiqrahman.reviewratings.BarLabels;
import com.taufiqrahman.reviewratings.RatingReviews;

import java.util.Random;

public class TutorProfile extends AppCompatActivity {

    private ActivityTutorProfileBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTutorProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        setListeners();

        RatingReviews ratingReviews = findViewById(R.id.rating_reviews);

        Pair colors[] = new Pair[]{
                new Pair<>(Color.parseColor("#6fb35e"),Color.parseColor("#6fb35e")),
                new Pair<>(Color.parseColor("#6fb35e"),Color.parseColor("#6fb35e")),
                new Pair<>(Color.parseColor("#6fb35e"),Color.parseColor("#6fb35e")),
                new Pair<>(Color.parseColor("#6fb35e"),Color.parseColor("#6fb35e")),
                new Pair<>(Color.parseColor("#6fb35e"), Color.parseColor("#6fb35e"))};

        int minValue = 30;

        int raters[] = new int[]{
                minValue + new Random().nextInt(100 - minValue + 1),
                minValue + new Random().nextInt(100 - minValue + 1),
                minValue + new Random().nextInt(100 - minValue + 1),
                minValue + new Random().nextInt(100 - minValue + 1),
                minValue + new Random().nextInt(100 - minValue + 1 )
        };

        ratingReviews.createRatingBars(100, BarLabels.STYPE1, colors, raters);
    }

    private void setListeners() {
        binding.backButton.setOnClickListener(v ->
                startActivity(new Intent(getApplicationContext(), MainActivity.class))
        );
    }
}