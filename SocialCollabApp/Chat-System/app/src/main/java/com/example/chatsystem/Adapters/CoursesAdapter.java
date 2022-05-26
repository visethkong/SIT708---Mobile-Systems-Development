package com.example.chatsystem.Adapters;

import android.content.Intent;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatsystem.Activities.SignUpActivity;
import com.example.chatsystem.Models.Course;
import com.example.chatsystem.R;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CoursesAdapter extends RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>{

    private final List<Course> courses;
    private RecyclerViewClickListener listener;

    public CoursesAdapter(List<Course> courses, RecyclerViewClickListener listener) {
        this.courses = courses;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CourseViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_course,
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull CourseViewHolder holder, int position) {
        holder.setCourse(courses.get(position));

    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public interface RecyclerViewClickListener{
        void onClick(View v, int position);
    }


    public class CourseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private final RoundedImageView imagePoster;
        private final TextView textName, textCategory;
        private final RatingBar ratingBar;

        public CourseViewHolder(View view) {
            super(view);
            imagePoster = view.findViewById(R.id.imagePoster);
            textName = view.findViewById(R.id.textName);
            textCategory = view.findViewById(R.id.textCategory);
            ratingBar = view.findViewById(R.id.ratingBar);
            view.setOnClickListener(this);
        }

        void setCourse(Course course) {
            imagePoster.setImageResource(course.poster);
            textName.setText(course.name);
            textCategory.setText(course.category);
            ratingBar.setRating(course.rating);
        }

        @Override
        public void onClick(View view) {
            listener.onClick(view, getAdapterPosition());

        }
    }
}
