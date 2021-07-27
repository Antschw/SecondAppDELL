package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DevActivity extends AppCompatActivity {

    // References

    EditText question, answer1, answer2, answer3, answer4, correctAnswer, mediaName;
    Switch isMedia, isMusic;
    Button btn_send, btn_Main;
    TextView warning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev);

        question = findViewById(R.id.editTextQuestion);
        answer1 = findViewById(R.id.editTextAnswer1);
        answer2 = findViewById(R.id.editTextAnswer2);
        answer3 = findViewById(R.id.editTextAnswer3);
        answer4 = findViewById(R.id.editTextAnswer4);
        correctAnswer = findViewById(R.id.editTextNumberAnswer);
        mediaName = findViewById(R.id.editTextMedia);

        isMedia = findViewById(R.id.switchMedia);
        isMusic = findViewById(R.id.switchTypeMedia);

        btn_send = findViewById(R.id.sendQuestionBtn);
        btn_Main = findViewById(R.id.toMainBtn);

        warning = findViewById(R.id.textView5);

        // Button listeners for the add and view all buttons
        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                QuestionModel questionModel;
                if (Integer.parseInt(correctAnswer.getText().toString())>4 | Integer.parseInt(correctAnswer.getText().toString())==0) {
                    Toast.makeText(DevActivity.this, "La Bonne réponse ne peut être comprise qu'entre 1 et 4", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        questionModel = new QuestionModel(-1, question.getText().toString(), answer1.getText().toString(), answer2.getText().toString(), answer3.getText().toString(), answer4.getText().toString(), Integer.parseInt(correctAnswer.getText().toString()), isMedia.isChecked(), mediaName.getText().toString());
                        Toast.makeText(DevActivity.this, questionModel.toString(), Toast.LENGTH_SHORT).show();
                    } catch (Exception e) {
                        Toast.makeText(DevActivity.this, "Erreur lors de la creation de la question", Toast.LENGTH_SHORT).show();
                        questionModel = new QuestionModel(-1, "error", "error", "error", "error", "error", 0, false, "error");
                    }
                    DataBaseHelper dataBaseHelper = new DataBaseHelper(DevActivity.this);

                    boolean success = dataBaseHelper.addOne(questionModel);

                    Toast.makeText(DevActivity.this, "Success= " + success, Toast.LENGTH_SHORT).show();

                    question.setText("");answer1.setText("");answer2.setText("");answer3.setText("");answer4.setText("");
                    correctAnswer.setText("");mediaName.setText("");
                }
            }
        });

        btn_Main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DevActivity.this, "Return Button", Toast.LENGTH_SHORT).show();
                switchActivities();
            }
        });
    }


    private void switchActivities() {
        Intent switchActivityIntent = new Intent(this, MainActivity.class);
        startActivity(switchActivityIntent);
    }
    public void onClickMedia (View view) {
        if (isMedia.isChecked()) {
            mediaName.setVisibility(View.VISIBLE);
            warning.setVisibility(View.VISIBLE);
            isMusic.setVisibility(View.VISIBLE);
        } else {
            mediaName.setVisibility(View.INVISIBLE);
            warning.setVisibility(View.INVISIBLE);
            isMusic.setVisibility(View.INVISIBLE);
        }
    }

}