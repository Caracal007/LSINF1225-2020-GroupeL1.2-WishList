package be.uclouvain.lsinf1225.groupel12.wishlist;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainModifProfil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modif_profil);
        buttonAll();
        infos(Session.getSession());
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    private void infos(String username){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String[] tab = mySQLiteOpenHelper.recupInfo(username);
        TextInputEditText firstnameinfo = findViewById(R.id.Fisrtname);
        firstnameinfo.setText(tab[0]);
        TextInputEditText lastnameinfo = findViewById(R.id.lastName);
        lastnameinfo.setText(tab[1]);
        TextInputEditText emailinfo = findViewById(R.id.mail);
        emailinfo.setText(tab[2]);
    }

    private void modifInfos(String username){
        TextInputEditText txtFirst_name = findViewById(R.id.Fisrtname);
        TextInputEditText txtLast_name = findViewById(R.id.lastName);
        TextInputEditText txtEmail = findViewById(R.id.mail);
        String recup1 = Objects.requireNonNull(txtFirst_name.getText()).toString();
        String recup2 = Objects.requireNonNull(txtLast_name.getText()).toString();
        String recup3 = Objects.requireNonNull(txtEmail.getText()).toString();
        mySQLiteOpenHelper.modifInfo(username, recup1, recup2, recup3);
    }


    @SuppressLint("ResourceAsColor")
    private void openDialog(){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("LOGOUT MESSAGE")
                .setMessage("Are you sure to want to logout ?")
                .setIcon(R.drawable.power_)
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        openActivityLogin();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.red);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.red);
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
                openDialog();
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


