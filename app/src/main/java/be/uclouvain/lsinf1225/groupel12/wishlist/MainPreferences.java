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
        recupPref(Session.getSession());
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    //Preferences ------------------------------------------------->>>
    private Spinner spinnerCouleur;
    private Spinner spinnerTailleVetements;
    private Spinner spinnerTailleChaussures;
    private Spinner spinnerTheme;
    private EditText textAdresse;

    private void initPreferences(){
        spinnerCouleur = findViewById(R.id.spinnerCouleur);
        spinnerTailleVetements = findViewById(R.id.spinnerTailleVetements);
        spinnerTailleChaussures = findViewById(R.id.spinnerTailleChaussures);
        spinnerTheme = findViewById(R.id.spinnerTheme);
        textAdresse = findViewById(R.id.textAdresse);
        buttonMajPreferences();
    }

    private void Preferences() {
        String recup1 = spinnerCouleur.getSelectedItem().toString();
        String recup2 = spinnerTailleVetements.getSelectedItem().toString();
        String recup3 = spinnerTailleChaussures.getSelectedItem().toString();
        String recup4 = spinnerTheme.getSelectedItem().toString();
        String recup5 = textAdresse.getText().toString();
        String session = Session.getSession();

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        mySQLiteOpenHelper.insertPreferences(session, recup1, recup2, recup3, recup4, recup5);
        mySQLiteOpenHelper.close();
    }

    private void recupPref(String username){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String[] tabPref = mySQLiteOpenHelper.recupPrefs(username);
        if(tabPref != null) {
            /*Color ----------------------------------------->*/
            switch (tabPref[0]) {
                case "Red":
                    spinnerCouleur.setSelection(1);
                    break;
                case "Blue":
                    spinnerCouleur.setSelection(2);
                    break;
                case "Yellow":
                    spinnerCouleur.setSelection(3);
                    break;
                case "Green":
                    spinnerCouleur.setSelection(4);
                    break;
                default:
                    spinnerCouleur.setSelection(0);
                    break;
            }
            /*Color -----------------------------------------<*/
            /*TailleVetement ----------------------------------------->*/
            switch (tabPref[1]) {
                case "XS":
                    spinnerTailleVetements.setSelection(1);
                    break;
                case "S":
                    spinnerTailleVetements.setSelection(2);
                    break;
                case "M":
                    spinnerTailleVetements.setSelection(3);
                    break;
                case "L":
                    spinnerTailleVetements.setSelection(4);
                    break;
                case "XL":
                    spinnerTailleVetements.setSelection(5);
                    break;
                default:
                    spinnerTailleVetements.setSelection(0);
                    break;
            }
            /*TailleVetement -----------------------------------------<*/
            /*TailleChaussures----------------------------------------->*/
            switch (tabPref[2]) {
                case "37-38":
                    spinnerTailleChaussures.setSelection(1);
                    break;
                case "38-39":
                    spinnerTailleChaussures.setSelection(2);
                    break;
                case "39-40":
                    spinnerTailleChaussures.setSelection(3);
                    break;
                case "40-41":
                    spinnerTailleChaussures.setSelection(4);
                    break;
                case "41-42":
                    spinnerTailleChaussures.setSelection(5);
                    break;
                case "42-43":
                    spinnerTailleChaussures.setSelection(6);
                    break;
                default:
                    spinnerTailleChaussures.setSelection(0);
                    break;
            }
            /*TailleChaussures----------------------------------------->*/
            /*Thème----------------------------------------->*/
            switch (tabPref[3]) {
                case "Garden":
                    spinnerTheme.setSelection(1);
                    break;
                case "Furniture":
                    spinnerTheme.setSelection(2);
                    break;
                case "Sport":
                    spinnerTheme.setSelection(3);
                    break;
                case "Media":
                    spinnerTheme.setSelection(4);
                    break;
                case "Devices":
                    spinnerTheme.setSelection(5);
                    break;
                default:
                    spinnerTheme.setSelection(0);
                    break;
            }
            /*Thème----------------------------------------->*/
            /*Adresse----------------------------------------->*/
            if (!tabPref[4].equals(" "))
                textAdresse.setText(tabPref[4]);
            /*Adresse----------------------------------------->*/
        }
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
