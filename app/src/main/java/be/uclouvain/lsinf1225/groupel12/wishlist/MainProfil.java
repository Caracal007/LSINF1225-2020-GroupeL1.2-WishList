package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profil);

        TextView session = (TextView) findViewById(R.id.session);
        session.setText(Session.getSession());
        buttonModifProfil();

    }

    /* Button ModifProfil---------------------------------------------------------------- */
    private void buttonModifProfil() {
        findViewById(R.id.Modifprofil).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityModifProfil();
            }
        })

        ;}

    public void openActivityModifProfil(){
        Intent intent = new Intent(this, MainModifProfil.class);
        startActivity(intent);
    }
    /* Button ModifProfil---------------------------------------------------------------- */

    public void onBackPressed() {
        // do nothing.
    }
}
