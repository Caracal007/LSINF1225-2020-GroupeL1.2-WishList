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

    private void recupPref(String username){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String tabPref[] = mySQLiteOpenHelper.recupPrefs(username);
        if(tabPref != null) {
            /*Color ----------------------------------------->*/
            if (tabPref[0].equals("Rouge"))
                spinnerCouleur.setSelection(1);
            else if (tabPref[0].equals("Bleu"))
                spinnerCouleur.setSelection(2);
            else if (tabPref[0].equals("Jaune"))
                spinnerCouleur.setSelection(3);
            else if (tabPref[0].equals("Vert"))
                spinnerCouleur.setSelection(4);
            else
                spinnerCouleur.setSelection(0);
            /*Color -----------------------------------------<*/
            /*TailleVetement ----------------------------------------->*/
            if (tabPref[1].equals("XS"))
                spinnerTailleVetements.setSelection(1);
            else if (tabPref[1].equals("S"))
                spinnerTailleVetements.setSelection(2);
            else if (tabPref[1].equals("M"))
                spinnerTailleVetements.setSelection(3);
            else if (tabPref[1].equals("L"))
                spinnerTailleVetements.setSelection(4);
            else if (tabPref[1].equals("XL"))
                spinnerTailleVetements.setSelection(5);
            else
                spinnerTailleVetements.setSelection(0);
            /*TailleVetement -----------------------------------------<*/
            /*TailleChaussures----------------------------------------->*/
            if (tabPref[2].equals("37-38"))
                spinnerTailleChaussures.setSelection(1);
            else if (tabPref[2].equals("38-39"))
                spinnerTailleChaussures.setSelection(2);
            else if (tabPref[2].equals("39-40"))
                spinnerTailleChaussures.setSelection(3);
            else if (tabPref[2].equals("40-41"))
                spinnerTailleChaussures.setSelection(4);
            else if (tabPref[2].equals("41-42"))
                spinnerTailleChaussures.setSelection(5);
            else if (tabPref[2].equals("42-43"))
                spinnerTailleChaussures.setSelection(6);
            else
                spinnerTailleChaussures.setSelection(0);
            /*TailleChaussures----------------------------------------->*/
            /*Thème----------------------------------------->*/
            if (tabPref[3].equals("Jardin"))
                spinnerTheme.setSelection(1);
            else if (tabPref[3].equals("Mobilier"))
                spinnerTheme.setSelection(2);
            else if (tabPref[3].equals("Sport"))
                spinnerTheme.setSelection(3);
            else if (tabPref[3].equals("Média"))
                spinnerTheme.setSelection(4);
            else if (tabPref[3].equals("Electronique"))
                spinnerTheme.setSelection(5);
            else
                spinnerTheme.setSelection(0);
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
