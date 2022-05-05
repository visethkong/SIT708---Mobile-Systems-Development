package com.example.chatsystem.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Base64;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.chatsystem.Adapters.CoursesAdapter;
import com.example.chatsystem.Adapters.RecentConversationsAdapter;
import com.example.chatsystem.Models.ChatMessage;
import com.example.chatsystem.Models.Course;
import com.example.chatsystem.R;
import com.example.chatsystem.Utilities.Constants;
import com.example.chatsystem.Utilities.PreferenceManager;
import com.example.chatsystem.databinding.ActivityChatBinding;
import com.example.chatsystem.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private PreferenceManager preferenceManager;
    private List<ChatMessage> conversations;
    private RecentConversationsAdapter conversationsAdapter;
    private FirebaseFirestore database;

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        preferenceManager = new PreferenceManager(getApplicationContext());

        loadUserDetails();
        bottomNavMenu();
        setListeners();
        setupCoursesViewPager();

    }

    private void loadUserDetails() {
        binding.textName.setText(preferenceManager.getString(Constants.KEY_NAME));
        byte[] bytes = Base64.decode(preferenceManager.getString(Constants.KEY_IMAGE), Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        binding.imageProfile.setImageBitmap(bitmap);
    }

    private void setListeners() {
        binding.imageSignOut.setOnClickListener(v -> signOut());
    }

    private void bottomNavMenu() {
        bottomNavigationView = findViewById(R.id.bottom_navigator);
        bottomNavigationView.setSelectedItemId(R.id.navigation_course);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.navigation_course:
                        return true;
                    case R.id.navigation_chat:
                        startActivity(new Intent(getApplicationContext(), ChatActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_activity:
                        startActivity(new Intent(getApplicationContext(), ActivityActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.navigation_account:
                        startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });

    }

    private void showToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    private void signOut() {
        showToast("Signing Out...");
        FirebaseFirestore database = FirebaseFirestore.getInstance();
        DocumentReference documentReference =
                database.collection(Constants.KEY_COLLECTION_USERS).document(
                        preferenceManager.getString(Constants.KEY_USER_ID)
                );
        HashMap<String, Object> updates = new HashMap<>();
        updates.put(Constants.KEY_FCM_TOKEN, FieldValue.delete());
        documentReference.update(updates)
                .addOnSuccessListener(unused -> {
                    preferenceManager.clear();
                    startActivity(new Intent(getApplicationContext(), SignInActivity.class));
                    finish();
                })
                .addOnFailureListener(e -> showToast("Unable to sign out"));
    }

    private void setupCoursesViewPager() {
        ViewPager2 coursesViewPager = findViewById(R.id.courseViewPager);
        coursesViewPager.setClipToPadding(false);
        coursesViewPager.setClipChildren(false);
        coursesViewPager.setOffscreenPageLimit(3);
        coursesViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(10));
        compositePageTransformer.addTransformer((page, position) -> {
            float r = 1 - Math.abs(position);
            page.setScaleY(0.85f + r * 0.15f);
        });
        coursesViewPager.setPageTransformer(compositePageTransformer);
        coursesViewPager.setAdapter(new CoursesAdapter(getCourses()));
    }



    private List<Course> getCourses() {
        List<Course> courses = new ArrayList<>();

        Course digitalPainting = new Course();
        digitalPainting.poster = R.drawable.avatar2;
        digitalPainting.name = "Jaysen Batchelor";
        digitalPainting.category = "Digital Painting";
        digitalPainting.rating = 4.6f;
        courses.add(digitalPainting);

        Course adobePhotoshop = new Course();
        adobePhotoshop.poster = R.drawable.avatar3;
        adobePhotoshop.name = "Mr Bojangle";
        adobePhotoshop.category = "Adobe Photoshop";
        adobePhotoshop.rating = 4.8f;
        courses.add(adobePhotoshop);

        Course webDesign = new Course();
        webDesign.poster = R.drawable.avatar4;
        webDesign.name = "Daniel Craig";
        webDesign.category = "Web Design";
        webDesign.rating = 3.5f;
        courses.add(webDesign);

        Course userInterface = new Course();
        userInterface.poster = R.drawable.avatar6;
        userInterface.name = "John Doe";
        userInterface.category = "UI Design";
        userInterface.rating = 5f;
        courses.add(userInterface);

        return courses;
    }
}