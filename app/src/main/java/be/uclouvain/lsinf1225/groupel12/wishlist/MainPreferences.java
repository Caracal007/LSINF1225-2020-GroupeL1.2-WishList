package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_preferences);
        initPreferences();
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    //Preferences ------------------------------------------------->>>
    private Spinner spinnerCouleur;
    private Spinner spinnerTailleVetements;
    private Spinner spinnerTailleChaussures;
    private Spinner spinnerTheme;
    private EditText textAdresse;
    private String recup1, recup2, recup3, recup4, recup5, session;

    private void initPreferences(){
        spinnerCouleur = (Spinner) findViewById(R.id.spinnerCouleur);
        spinnerTailleVetements = (Spinner) findViewById(R.id.spinnerTailleVetements);
        spinnerTailleChaussures = (Spinner) findViewById(R.id.spinnerTailleChaussures);
        spinnerTheme = (Spinner) findViewById(R.id.spinnerTheme);
        textAdresse = (EditText) findViewById(R.id.textAdresse);
        buttonMajPreferences();
    }

    private void Preferences() {
        recup1 = spinnerCouleur.getSelectedItem().toString();
        recup2 = spinnerTailleVetements.getSelectedItem().toString();
        recup3 = spinnerTailleChaussures.getSelectedItem().toString();
        recup4 = spinnerTheme.getSelectedItem().toString();
        recup5 = textAdresse.getText().toString();
        session = Session.getSession();

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        mySQLiteOpenHelper.insertPreferences(session, recup1, recup2, recup3, recup4, recup5);
        mySQLiteOpenHelper.close();
    }


    private void buttonMajPreferences() {
        findViewById(R.id.buttonMajPreferences).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Preferences();
                openActivityProfil();
            }
        })

    ;}

    public void openActivityProfil(){
        Intent intent = new Intent(this, MainProfil.class);
        startActivity(intent);
    }

    public void onBackPressed() {
        // do nothing.
    }

}
