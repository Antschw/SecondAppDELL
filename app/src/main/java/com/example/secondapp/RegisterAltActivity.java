package com.example.secondapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.List;

public class RegisterAltActivity extends AppCompatActivity {

    Button  rgtrBtn;
    TextInputEditText editTextJoueur1,editTextJoueur2,editTextJoueur3,editTextJoueur4,editTextJoueur5,editTextJoueur6;
    FloatingActionButton addButton, rmvButton;
    TextView errTxt,textvv,textvvv;
    List<String> saveNames;
    Dialog mError;
    TextInputLayout editTextJoueurLayout1,editTextJoueurLayout2,editTextJoueurLayout3,editTextJoueurLayout4,editTextJoueurLayout5,editTextJoueurLayout6;

    int nbJoueur;
    boolean err;

    private ConstraintLayout rmvConstraintLayout;
    private ConstraintLayout rgtrConstraintLayout;
    private ConstraintSet rmvConstraintSet = new ConstraintSet();
    private ConstraintSet rgtrConstraintSet = new ConstraintSet();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_alt);

        rmvConstraintLayout = findViewById(R.id.constraint_layout);
        rgtrConstraintLayout = findViewById(R.id.constraint_layout);

        rgtrBtn = findViewById(R.id.registerButton);
        addButton = findViewById(R.id.addButton);
        rmvButton = findViewById(R.id.rmvButton);

        errTxt = findViewById(R.id.textError);
        textvv = findViewById(R.id.textView24);
        textvvv = findViewById(R.id.textvvv);

        mError = new Dialog(this);
        mError.setContentView(R.layout.popup_register_activity);

        editTextJoueurLayout1 = findViewById(R.id.textInputLayout1);
        editTextJoueurLayout2 = findViewById(R.id.textInputLayout2);
        editTextJoueurLayout3 = findViewById(R.id.textInputLayout3);
        editTextJoueurLayout4 = findViewById(R.id.textInputLayout4);
        editTextJoueurLayout5 = findViewById(R.id.textInputLayout5);
        editTextJoueurLayout6 = findViewById(R.id.textInputLayout6);

        editTextJoueur1 = findViewById(R.id.textInputEditJoueur1);
        editTextJoueur2 = findViewById(R.id.textInputEditJoueur2);
        editTextJoueur3 = findViewById(R.id.textInputEditJoueur3);
        editTextJoueur4 = findViewById(R.id.textInputEditJoueur4);
        editTextJoueur5 = findViewById(R.id.textInputEditJoueur5);
        editTextJoueur6 = findViewById(R.id.textInputEditJoueur6);

        saveNames = new ArrayList<>();

        nbJoueur = 2;

        addButton.setOnClickListener(v -> {
            nbJoueur += 1;

            if (nbJoueur==3) {
                rmvButton.setVisibility(View.VISIBLE);
                editTextJoueurLayout3.setVisibility(View.VISIBLE);

                rgtrConstraintSet.clone(rgtrConstraintLayout);
                rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                        R.id.textInputLayout3, ConstraintSet.BOTTOM);
                rgtrConstraintSet.applyTo(rgtrConstraintLayout);

            } else if (nbJoueur==4) {
                editTextJoueurLayout4.setVisibility(View.VISIBLE);

                rmvConstraintSet.clone(rmvConstraintLayout);
                rmvConstraintSet.connect(R.id.rmvButton, ConstraintSet.TOP,
                        R.id.textInputLayout3, ConstraintSet.BOTTOM);
                rmvConstraintSet.applyTo(rmvConstraintLayout);

                rgtrConstraintSet.clone(rgtrConstraintLayout);
                rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                        R.id.textInputLayout4, ConstraintSet.BOTTOM);
                rgtrConstraintSet.applyTo(rgtrConstraintLayout);

            } else if (nbJoueur==5) {
                editTextJoueurLayout5.setVisibility(View.VISIBLE);

                rmvConstraintSet.clone(rmvConstraintLayout);
                rmvConstraintSet.connect(R.id.rmvButton, ConstraintSet.TOP,
                        R.id.textInputLayout4, ConstraintSet.BOTTOM);
                rmvConstraintSet.applyTo(rmvConstraintLayout);

                rgtrConstraintSet.clone(rgtrConstraintLayout);
                rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                        R.id.textInputLayout5, ConstraintSet.BOTTOM);
                rgtrConstraintSet.applyTo(rgtrConstraintLayout);

            } else {
                editTextJoueurLayout6.setVisibility(View.VISIBLE);

                rmvConstraintSet.clone(rmvConstraintLayout);
                rmvConstraintSet.connect(R.id.rmvButton, ConstraintSet.TOP,
                        R.id.textInputLayout5, ConstraintSet.BOTTOM);
                rmvConstraintSet.applyTo(rmvConstraintLayout);

                rgtrConstraintSet.clone(rgtrConstraintLayout);
                rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                        R.id.textInputLayout6, ConstraintSet.BOTTOM);
                rgtrConstraintSet.applyTo(rgtrConstraintLayout);
            }
        });

        rgtrBtn.setOnClickListener(v -> {

            editTextJoueur1.setTextColor(Color.rgb(0,0,0));
            editTextJoueur2.setTextColor(Color.rgb(0,0,0));
            editTextJoueur3.setTextColor(Color.rgb(0,0,0));
            editTextJoueur4.setTextColor(Color.rgb(0,0,0));
            editTextJoueur5.setTextColor(Color.rgb(0,0,0));
            editTextJoueur6.setTextColor(Color.rgb(0,0,0));

            hideKeyboard(RegisterAltActivity.this);

            int i = 1;
            saveNames.clear();
            //errTxt.setVisibility(View.INVISIBLE);
            err = false;

            while (i <= nbJoueur) {
                String name = "textInputEditJoueur"+i;
                int id = getResources().getIdentifier(name, "id", getPackageName());
                if (id != 0) {
                    TextInputEditText editText = findViewById(id);
                    if (editText.getText().toString().equals("")) {
                        mError.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                        mError.show();
                    } else {
                        for (int j = 0; j < saveNames.size(); j++) {
                            if (editText.getText().toString().equals(saveNames.get(j))){
                                //errTxt.setVisibility(View.VISIBLE);
                                mError.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                                mError.show();
                                editText.setTextColor(Color.RED);
                                //TODO : remplacer le setTextColor par un setBackgroundTint
                                err = true;
                                break;
                            }
                        }
                        if (!err){
                            saveNames.add(editText.getText().toString());
                        } else {
                            break;
                        }
                    }
                }
                i += 1;
            }

            textvvv.setText(Integer.toString(nbJoueur));
            textvv.setText(Integer.toString(saveNames.size()));

            if (saveNames.size()==nbJoueur) {

                Intent intent = new Intent(RegisterAltActivity.this, PlayActivity.class);

                intent.putStringArrayListExtra("liste_noms_joueurs", (ArrayList<String>) saveNames);
                intent.putExtra("nombre_joueur", nbJoueur);

                startActivity(intent);
            }
        });
    }

    public void onRmvBtnClick (View view) {
        if (nbJoueur==3) {
            editTextJoueurLayout3.setVisibility(View.INVISIBLE);
            rmvButton.setVisibility(View.INVISIBLE);

            rgtrConstraintSet.clone(rgtrConstraintLayout);
            rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                    R.id.textInputLayout2, ConstraintSet.BOTTOM);
            rgtrConstraintSet.applyTo(rgtrConstraintLayout);

        } else if (nbJoueur==4) {
            editTextJoueurLayout4.setVisibility(View.INVISIBLE);

            rmvConstraintSet.clone(rmvConstraintLayout);
            rmvConstraintSet.connect(R.id.rmvButton, ConstraintSet.TOP,
                    R.id.textInputLayout2, ConstraintSet.BOTTOM);
            rmvConstraintSet.applyTo(rmvConstraintLayout);

            rgtrConstraintSet.clone(rgtrConstraintLayout);
            rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                    R.id.textInputLayout3, ConstraintSet.BOTTOM);
            rgtrConstraintSet.applyTo(rgtrConstraintLayout);

        } else if (nbJoueur==5) {
            editTextJoueurLayout5.setVisibility(View.INVISIBLE);

            rmvConstraintSet.clone(rmvConstraintLayout);
            rmvConstraintSet.connect(R.id.rmvButton, ConstraintSet.TOP,
                    R.id.textInputLayout3, ConstraintSet.BOTTOM);
            rmvConstraintSet.applyTo(rmvConstraintLayout);

            rgtrConstraintSet.clone(rgtrConstraintLayout);
            rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                    R.id.textInputLayout4, ConstraintSet.BOTTOM);
            rgtrConstraintSet.applyTo(rgtrConstraintLayout);

        } else {
            editTextJoueurLayout6.setVisibility(View.INVISIBLE);

            rmvConstraintSet.clone(rmvConstraintLayout);
            rmvConstraintSet.connect(R.id.rmvButton, ConstraintSet.TOP,
                    R.id.textInputLayout4, ConstraintSet.BOTTOM);
            rmvConstraintSet.applyTo(rmvConstraintLayout);

            rgtrConstraintSet.clone(rgtrConstraintLayout);
            rgtrConstraintSet.connect(R.id.registerButton, ConstraintSet.TOP,
                    R.id.textInputLayout5, ConstraintSet.BOTTOM);
            rgtrConstraintSet.applyTo(rgtrConstraintLayout);
        }
        nbJoueur -= 1;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}