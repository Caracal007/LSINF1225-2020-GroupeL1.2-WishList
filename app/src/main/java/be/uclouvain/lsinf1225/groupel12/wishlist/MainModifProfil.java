package be.uclouvain.lsinf1225.groupel12.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainModifProfil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modif_profil);
        buttonAll();
        Infos(Session.getSession());
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private TextInputEditText txtFirst_name;
    private TextInputEditText txtLast_name;
    private TextInputEditText txtEmail;
    private String recup1, recup2, recup3;

    private void Infos(String username){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String tab[] = mySQLiteOpenHelper.recupInfo(username);
        TextInputEditText firstnameinfo = (TextInputEditText) findViewById(R.id.Fisrtname);
        firstnameinfo.setText(tab[0]);
        TextInputEditText lastnameinfo = (TextInputEditText) findViewById(R.id.lastName);
        lastnameinfo.setText(tab[1]);
        TextInputEditText emailinfo = (TextInputEditText) findViewById(R.id.mail);
        emailinfo.setText(tab[2]);
    }

    private void modifInfos(String username){
        txtFirst_name = (TextInputEditText) findViewById(R.id.Fisrtname);
        txtLast_name = (TextInputEditText) findViewById(R.id.lastName);
        txtEmail = (TextInputEditText) findViewById(R.id.mail);
        recup1 = txtFirst_name.getText().toString();
        recup2 = txtLast_name.getText().toString();
        recup3 = txtEmail.getText().toString();
        mySQLiteOpenHelper.modifInfo(username, recup1, recup2, recup3);
    }


    /* Button All ---------------------------------------------------------------- */
    private void buttonAll(){
        logout();
        down();
        modif();
        bottomButton();
    }
    /* Button All ---------------------------------------------------------------- */
    /* Button logout ---------------------------------------------------------------- */
    private void logout() {
        findViewById(R.id.buttonLogout).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityLogin();
            }
        })
        ;}
    public void openActivityLogin(){
        Intent intent = new Intent(this, MainLogin.class);
        startActivity(intent);
    }
    /* Button logout---------------------------------------------------------------- */

    /* Button terminer ---------------------------------------------------------------- */
    private void down() {
        findViewById(R.id.ButtonTerminerModif).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityProfil();
                modifInfos(Session.getSession());
            }
        })
        ;}
    /* Button terminer---------------------------------------------------------------- */

    /* Button modif ---------------------------------------------------------------- */
    private void modif() {
        findViewById(R.id.modifPref).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityPreferences();
            }
        })
        ;}
    public void openActivityPreferences(){
        Intent intent = new Intent(this, MainPreferences.class);
        startActivity(intent);
    }
    /* Button modif ---------------------------------------------------------------- */
    /* BOTTOM BUTTON */
    private void bottomButton(){
        buttonAddGift();
        buttonProfil();
        buttonFriend();
    }
    /* Button AddGift---------------------------------------------------------------- */
    private void buttonAddGift() {
        findViewById(R.id.buttonAddGift).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityAddGift();
            }
        })
        ;}
    public void openActivityAddGift(){
        Intent intent = new Intent(this, MainAddGift.class);
        startActivity(intent);
    }
    /* Button AddGift---------------------------------------------------------------- */
    /* Button Profil---------------------------------------------------------------- */
    private void buttonProfil() {
        findViewById(R.id.buttonProfil).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityProfil();
            }
        })
        ;}
    public void openActivityProfil(){
        Intent intent = new Intent(this, MainProfil.class);
        startActivity(intent);
    }
    /* Button Profil---------------------------------------------------------------- */
    /* Button Friend---------------------------------------------------------------- */
    private void buttonFriend() {
        findViewById(R.id.buttonFriends).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityFriend();
            }
        })

        ;}
    public void openActivityFriend(){
        Intent intent = new Intent(this, MainFriend.class);
        startActivity(intent);
    }

    /* Button Friend---------------------------------------------------------------- */
    /* BOTTOM BUTTON */
}


