package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class RegisterActivity extends AppCompatActivity {

    Button rgtrButton, goButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        TextView txtJoueur1 = findViewById(R.id.textPlayer1);
        TextView txtJoueur2 = findViewById(R.id.textPlayer2);
        TextView txtJoueur3 = findViewById(R.id.textPlayer3);

        EditText edtTxtJoueur1 = findViewById(R.id.editTextTextPersonName1);
        EditText edtTxtJoueur2 = findViewById(R.id.editTextTextPersonName2);
        EditText edtTxtJoueur3 = findViewById(R.id.editTextTextPersonName3);

        ProgressBar showPogress1 = findViewById(R.id.progressBar1);
        ProgressBar showPogress2 = findViewById(R.id.progressBar2);
        ProgressBar showPogress3 = findViewById(R.id.progressBar3);

        ImageView showError1 = findViewById(R.id.Incorrect1);
        ImageView showError2 = findViewById(R.id.Incorrect2);
        ImageView showError3 = findViewById(R.id.Incorrect3);

        ImageView showCorrect1 = findViewById(R.id.Correct1);
        ImageView showCorrect2 = findViewById(R.id.Correct2);
        ImageView showCorrect3 = findViewById(R.id.Correct3);

        TextView showTextError = findViewById((R.id.textError));

        rgtrButton = findViewById(R.id.registerButton);

        rgtrButton.setOnClickListener(v -> {

            txtJoueur1.setText("Joueur 1 : " + edtTxtJoueur1.getText().toString());
            txtJoueur2.setText("Joueur 2 : " + edtTxtJoueur2.getText().toString());
            txtJoueur3.setText("Joueur 3 : " + edtTxtJoueur3.getText().toString());

            showPogress1.setVisibility(View.VISIBLE);
            showPogress2.setVisibility(View.VISIBLE);
            showPogress3.setVisibility(View.VISIBLE);

            showError1.setVisibility(View.INVISIBLE);
            showError2.setVisibility(View.INVISIBLE);
            showError3.setVisibility(View.INVISIBLE);

            showCorrect1.setVisibility(View.INVISIBLE);
            showCorrect2.setVisibility(View.INVISIBLE);
            showCorrect3.setVisibility(View.INVISIBLE);


            if (txtJoueur1.getText().equals("Joueur 1 : ")) {
                showPogress1.setVisibility(View.INVISIBLE);
                showError1.setVisibility(View.VISIBLE);
                showTextError.setVisibility(View.VISIBLE);
            } else {
                showPogress1.setVisibility(View.INVISIBLE);
                showCorrect1.setVisibility(View.VISIBLE);
                if (edtTxtJoueur1.getText().toString().equals(edtTxtJoueur2.getText().toString())) {
                    showPogress2.setVisibility(View.INVISIBLE);
                    showError2.setVisibility(View.VISIBLE);
                    showTextError.setVisibility(View.VISIBLE);
                } else {
                    showPogress2.setVisibility(View.INVISIBLE);
                    showCorrect2.setVisibility(View.VISIBLE);
                    if (edtTxtJoueur1.getText().toString().equals(edtTxtJoueur3.getText().toString()) | edtTxtJoueur2.getText().toString().equals(edtTxtJoueur3.getText().toString())) {
                        showPogress3.setVisibility(View.INVISIBLE);
                        showError3.setVisibility(View.VISIBLE);
                        showTextError.setVisibility(View.VISIBLE);
                    } else {
                        showPogress3.setVisibility(View.INVISIBLE);
                        showCorrect3.setVisibility(View.VISIBLE);
                        showTextError.setVisibility(View.INVISIBLE);

                        Button showGo = findViewById(R.id.buttonGo);
                        showGo.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        goButton = findViewById(R.id.buttonGo);

        goButton.setOnClickListener(v -> {

            String saveName1 = edtTxtJoueur1.getText().toString();
            String saveName2 = edtTxtJoueur2.getText().toString();
            String saveName3 = edtTxtJoueur3.getText().toString();

            Intent intent = new Intent(RegisterActivity.this, PlayActivity.class);

            intent.putExtra("EXTRA_TEXT1", saveName1);
            intent.putExtra("EXTRA_TEXT2", saveName2);
            intent.putExtra("EXTRA_TEXT3", saveName3);

            startActivity(intent);
        });
    }
}