package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import android.os.CountDownTimer;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.floor;

public class PlayActivity extends AppCompatActivity {


    //Variables utilisees
    TextView textId,textTimer,textQuestion,textReponse1,textReponse2,textReponse3,textReponse4,textCorrect,textCurrentPlayer,textRepJoueur;
    DataBaseHelper db;
    Button valider;
    ImageView imageQuestion;
    CheckBox isMedia;
    Dialog mGood,mBad;
    ProgressBar progressBarTimer;
    CountDownTimer myCount;

    int reponseJoueur, lastDbId, nbJoueur, joueurActuel, counter;
    List<String> saveNames;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        //Création des fenêtres pop up
        mGood = new Dialog(this);
        mGood.setContentView(R.layout.popup_play_good_activity);
        mBad = new Dialog(this);
        mBad.setContentView(R.layout.popup_play_bad_activity);

        counter = 15;

        progressBarTimer = findViewById(R.id.progressBarTimer);

        //Chargement des données des autres activités
        Intent intent = getIntent();
        nbJoueur = intent.getIntExtra("nombre_joueur",0);
        saveNames = intent.getStringArrayListExtra("liste_noms_joueurs");

        reponseJoueur = 0;

        joueurActuel = 1;

        List<Integer> dejaTire = new ArrayList<>();  //Initialisation de la liste des elements tires

        //Affectation de tous les elements visuels ainsi que de la base de donnees
        textCurrentPlayer = findViewById(R.id.textCurrentPlayer);
        textId = findViewById(R.id.textId);
        textQuestion = findViewById(R.id.textQuestion);
        textReponse1 = findViewById(R.id.textReponse1);
        textReponse2 = findViewById(R.id.textReponse2);
        textReponse3 = findViewById(R.id.textReponse3);
        textReponse4 = findViewById(R.id.textReponse4);
        textCorrect = findViewById(R.id.textCorrect);
        textRepJoueur = findViewById(R.id.textRepJoueur);
        imageQuestion = findViewById(R.id.imageQuestion);
        isMedia = findViewById(R.id.checkBox);
        valider = findViewById(R.id.viewBtn);
        textTimer = findViewById(R.id.timer);

        counter = 15;
        progressBarTimer.setMax(15);
        progressBarTimer.setProgress(counter);

        myCount = new CountDownTimer(15000,1000){
            public void onTick (long millisUntilFinished){
                textTimer.setText(String.valueOf(counter));
                counter--;
                progressBarTimer.setProgress(15-counter,true);
            }

            @Override
            public void onFinish() {
                valider.callOnClick();
                progressBarTimer.setProgress(15);
            }
        }.start();


        db = new DataBaseHelper(this);

        //Initialisation du Curseur
        Cursor cursor = db.getdata();

        //Enregistrement de la taille de la base de donnees
        cursor.moveToLast();

        //Enregistrement de la taille de la db
        lastDbId = cursor.getPosition();


        //Verification que la base de donnee n'est pas vide
        if (cursor.getCount() == 0) {
            Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();

        } else {

            //Tirage d'un premier random
            cursor.moveToPosition(GenRandom(0, lastDbId));
            //Verification que tous les elements de la base de donnee n'ont pas deja ete tire
            if (dejaTire.size() == lastDbId) {
                Toast.makeText(PlayActivity.this, "Base de donnée finie. Réinitialisation ", Toast.LENGTH_SHORT).show();
                //(new Handler()).postDelayed(this::switchActivities, 2000);  //mise en pause de 2s avant retour main activity
                dejaTire.clear();
            } else {
                while (dejaTire.contains(cursor.getPosition())) {   //Retirage en boucle si element tire lors du premier rand deja tire jusqu'a element jamais tire
                    cursor.moveToPosition(GenRandom(0, lastDbId));
                }
            }

            dejaTire.add(cursor.getPosition()); //Ajout de l'element tire a la liste des elements deja tire

            if (cursor.getString(7).equals("1")) {  //Verification de si la question contient un media
                String imageUrl = cursor.getString(8);
                Picasso.get().load(imageUrl).into(imageQuestion);   //Chargement du media a partir du module picasso via url dans base de donnees
                isMedia.setChecked(true);
            } else {
                isMedia.setChecked(false);
                imageQuestion.setImageResource(R.drawable.questionmarkk);
            }

            //Affichage des question en reponses possible de l'id tire depuis la base de donnees
            textId.setText("Id : " + cursor.getString(0));
            textQuestion.setText("Question : " + cursor.getString(1));
            textReponse1.setText("Reponse 1 : " + cursor.getString(2));
            textReponse2.setText("Reponse 2 : " + cursor.getString(3));
            textReponse3.setText("Reponse 3 : " + cursor.getString(4));
            textReponse4.setText("Reponse 4 : " + cursor.getString(5));
            textCorrect.setText("Correct : " + cursor.getString(6));

        }

        //Mise en surbrillance de la réponse du joueur
        highlightSelectedAnswer(textReponse1, 1);
        highlightSelectedAnswer(textReponse2, 2);
        highlightSelectedAnswer(textReponse3, 3);
        highlightSelectedAnswer(textReponse4, 4);

        //Actualisation du nom du joueur actuel
        textCurrentPlayer.setText(saveNames.get(joueurActuel-1));
        //joueurActuel = joueurActuel == nbJoueur ? 1 : joueurActuel++;
        if (joueurActuel==nbJoueur) {
            joueurActuel = 1;
        } else {
            joueurActuel += 1;
        }

        valider.setOnClickListener(v -> {   //Deroulement des actions suite au click sur bouton valider


            myCount.cancel();

            if (reponseJoueur!=0){
                if (cursor.getInt(6)==reponseJoueur){
                    Toast.makeText(PlayActivity.this, " Bonne Réponse", Toast.LENGTH_SHORT).show();
                    mGood.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mGood.show();
                } else {
                    Toast.makeText(PlayActivity.this, " Mauvaise Réponse", Toast.LENGTH_SHORT).show();

                    mBad.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                    mBad.show();
                }
            } else {
                Toast.makeText(PlayActivity.this, "Vous n'avez pas répondu", Toast.LENGTH_SHORT).show();
            }

            //Verification que la base de donnee n'est pas vide
            if (cursor.getCount() == 0) {
                Toast.makeText(getApplicationContext(), "NO DATA", Toast.LENGTH_SHORT).show();

            } else {

                //Tirage d'un premier random
                cursor.moveToPosition(GenRandom(0, lastDbId));
                //Verification que tous les elements de la base de donnee n'ont pas deja ete tire
                if (dejaTire.size() == lastDbId) {
                    Toast.makeText(PlayActivity.this, "Base de donnée finie. Réinitialisation ", Toast.LENGTH_SHORT).show();
                    //(new Handler()).postDelayed(this::switchActivities, 2000);  //mise en pause de 2s avant retour main activity
                    dejaTire.clear();
                } else {
                    while (dejaTire.contains(cursor.getPosition())) {   //Retirage en boucle si element tire lors du premier rand deja tire jusqu'a element jamais tire
                        cursor.moveToPosition(GenRandom(0, lastDbId));
                    }
                }

                dejaTire.add(cursor.getPosition()); //Ajout de l'element tire a la liste des elements deja tire

                if (cursor.getString(7).equals("1")) {  //Verification de si la question contient un media
                    String imageUrl = cursor.getString(8);
                    Picasso.get().load(imageUrl).into(imageQuestion);   //Chargement du media a partir du module picasso via url dans base de donnees
                    isMedia.setChecked(true);
                } else {
                    isMedia.setChecked(false);
                    imageQuestion.setImageResource(R.drawable.questionmarkk);
                }

                //Affichage des question en reponses possible de l'id tire depuis la base de donnees
                textId.setText("Id : " + cursor.getString(0));
                textQuestion.setText("Question : " + cursor.getString(1));
                textReponse1.setText("Reponse 1 : " + cursor.getString(2));
                textReponse2.setText("Reponse 2 : " + cursor.getString(3));
                textReponse3.setText("Reponse 3 : " + cursor.getString(4));
                textReponse4.setText("Reponse 4 : " + cursor.getString(5));
                textCorrect.setText("Correct : " + cursor.getString(6));

            }

            //Remise à la couleur par defaut des couleurs des réponses
            resetColorAnswer();

            //Actualisation du nom du joueur actuel

            textCurrentPlayer.setText(saveNames.get(joueurActuel-1));

            //Actualisation du numero du joueur en jeu
            //joueurActuel = joueurActuel == nbJoueur ? 1 : joueurActuel++;
            if (joueurActuel==nbJoueur) {
                joueurActuel = 1;
            } else {
                joueurActuel += 1;
            }

            //Reset réponse joueur
            reponseJoueur = 0;
            textRepJoueur.setText("Votre réponse : ");

            counter = 15;
            progressBarTimer.setMax(15);
            progressBarTimer.setProgress(counter);


            myCount.start();


        });
    }

    public static int GenRandom(Integer min, Integer max) { //Tirage aleatoire entre la valeur min et la valeur max en argument de la methode
        return (int) floor(Math.random()*(max-min+1)+min);
    }

    //Méthode de surbrillance des réponses du joueur
    private void highlightSelectedAnswer(TextView textView, int id) {
        textView.setOnClickListener(v -> {
            reponseJoueur = id;
            textReponse1.setTextColor(ContextCompat.getColor(PlayActivity.this, id == 1 ? R.color.purple_500 : R.color.black));
            textReponse2.setTextColor(ContextCompat.getColor(PlayActivity.this, id == 2 ? R.color.purple_500 : R.color.black));
            textReponse3.setTextColor(ContextCompat.getColor(PlayActivity.this, id == 3 ? R.color.purple_500 : R.color.black));
            textReponse4.setTextColor(ContextCompat.getColor(PlayActivity.this, id == 4 ? R.color.purple_500 : R.color.black));
            textRepJoueur.setText("Votre réponse : " + id);
        });
    }

    //Méthode de remise à zero de la couleur des réponses
    private void resetColorAnswer () {
        textReponse1.setTextColor(ContextCompat.getColor(PlayActivity.this, R.color.black));
        textReponse2.setTextColor(ContextCompat.getColor(PlayActivity.this, R.color.black));
        textReponse3.setTextColor(ContextCompat.getColor(PlayActivity.this, R.color.black));
        textReponse4.setTextColor(ContextCompat.getColor(PlayActivity.this, R.color.black));
    }

}