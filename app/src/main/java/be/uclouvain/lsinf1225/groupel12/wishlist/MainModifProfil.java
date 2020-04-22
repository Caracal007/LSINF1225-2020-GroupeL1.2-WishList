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
}


