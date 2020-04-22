package be.uclouvain.lsinf1225.groupel12.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainModifProfil extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_modif_profil);
        logout();
        down();
        modif();

    }
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
            }
        })
        ;}
    public void openActivityProfil(){
        Intent intent = new Intent(this, MainProfil.class);
        startActivity(intent);
    }
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
}


