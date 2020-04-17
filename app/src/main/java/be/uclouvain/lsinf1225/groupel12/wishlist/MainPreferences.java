package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;

public class MainPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_preferences);

        Button inscription = findViewById(R.id.buttonMajPreferences);
        inscription.setOnClickListener(v -> {

            Intent intent = new Intent(this, MainProfil.class);

            startActivity(intent);
        });

    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    //Preferences ------------------------------------------------->>>
    private Spinner spinnerCouleur;
    private Spinner spinnerTailleVetements;
    private Spinner spinnerTailleChaussures;
    private Spinner spinnerTheme;
    private TextInputEditText textAdresse;

    private void initPreferences(){
        spinnerCouleur = (Spinner) findViewById(R.id.spinnerCouleur);
        spinnerTailleVetements = (Spinner) findViewById(R.id.spinnerTailleVetements);
        spinnerTailleChaussures = (Spinner) findViewById(R.id.spinnerTailleChaussures);
        spinnerTheme = (Spinner) findViewById(R.id.spinnerTheme);
        textAdresse = (TextInputEditText) findViewById(R.id.textAdresse);
        buttonMajPreferences();
    }

    private void Preferences() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        //à compléter
        mySQLiteOpenHelper.close();
    }


    private void buttonMajPreferences() {
        findViewById(R.id.buttonMajPreferences).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Preferences();
            }
        })

    ;}

    public void onBackPressed() {
        // do nothing.
    }

}
