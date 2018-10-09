package com.example.guillermogs2.userprofile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class UserProfileActivity extends AppCompatActivity {

    private Profile profile;
    private TextView txt_name_surname;
    private TextView txt_username;
    private TextView txt_followers_value;
    private TextView txt_following_value;
    private TextView txt_about_me_value;
    private ImageView img_profile;
    private ImageView img_background;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        txt_name_surname = findViewById(R.id.txt_name_surname);
        txt_username = findViewById(R.id.txt_username);
        txt_followers_value = findViewById(R.id.txt_followers_value);
        txt_following_value = findViewById(R.id.txt_following_value);
        txt_about_me_value = findViewById(R.id.txt_about_me_value);
        img_profile = findViewById(R.id.img_profile);
        img_background = findViewById(R.id.img_background);

        gson = new Gson();

        try{
            InputStream stream = getAssets().open("GuillermoGarcia.json");
            InputStreamReader reader = new InputStreamReader(stream);
            profile = gson.fromJson(reader,Profile.class);
            updateInfo();
        }catch (IOException e){
            Toast.makeText(UserProfileActivity.this, "Unknow error ...", Toast.LENGTH_SHORT).show();
        }
        //updateMovie();

        Glide.with(this).load("file:///android_asset/my_profile.jpg").apply(RequestOptions.circleCropTransform().circleCrop()).into(img_profile);
        Glide.with(this).load("file:///android_asset/UserProfile-background.jpg").into(img_background);

    }

    private void updateInfo() {
        //txt_name_surname.setText(profile.getName());
        txt_name_surname.setText(profile.getName() + " " + profile.getLastname());
        txt_username.setText(profile.getHandle());
        txt_following_value.setText(profile.getFollowing());
        txt_followers_value.setText(profile.getFollowers());
        txt_about_me_value.setText(profile.getAbout());
    }
}
