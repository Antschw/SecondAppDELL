package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    MediaPlayer introSong;
    EditText editPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        introSong = MediaPlayer.create(MainActivity.this,R.raw.sosouistiti);
        editPassword = findViewById(R.id.editPassword);
        introSong.start();
    }

    public void onBtnClick (View view) {
        if (editPassword.getText().toString().equals("pasdev")) {
            editPassword.setText("");
            switchActivitiesDev();
        } else {
            introSong.release();
            switchActivities();
        }
    }

    public void onDevBtnClick (View view) {
        if (editPassword.getVisibility()==(View.VISIBLE)) {
            editPassword.setVisibility(View.INVISIBLE);
        } else {
            introSong.release();
            editPassword.setVisibility(View.VISIBLE);
        }
    }

    public void onMajBtnClick (View view) {
        Intent switchActivityIntent = new Intent(this, RegisterActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchActivities(){
        Intent switchActivityIntent = new Intent(this, RegisterAltActivity.class);
        startActivity(switchActivityIntent);
    }

    private void switchActivitiesDev(){
        Intent switchActivityDevIntent = new Intent(this, DevActivity.class);
        startActivity(switchActivityDevIntent);
    }

}