package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;

public class MainInscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_inscription);

        initInscription();
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;



    //Inscription ------------------------------------------------------------------>>>
    private TextInputEditText txtUsername;
    private TextInputEditText txtPassword;
    private TextInputEditText txtFirst_name;
    private TextInputEditText txtLast_name;
    private TextInputEditText txtEmail;
    private String recup1, recup2, recup3, recup4, recup5;


    private void initInscription(){
        txtUsername = (TextInputEditText) findViewById(R.id.txtUsername);
        txtPassword = (TextInputEditText) findViewById(R.id.txtPassword);
        txtFirst_name = (TextInputEditText) findViewById(R.id.txtFirst_name);
        txtLast_name = (TextInputEditText) findViewById(R.id.txtLast_name);
        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        btnInscription();
    }

    private void Inscription(){

        recup1 = txtUsername.getText().toString();
        recup2 = txtPassword.getText().toString();
        recup3 = txtFirst_name.getText().toString();
        recup4 = txtLast_name.getText().toString();
        recup5 = txtEmail.getText().toString();

        if (recup1.matches("") || recup2.matches("") || recup3.matches("") || recup4.matches("")
                || recup5.matches("")) {

            Toast.makeText(this, "Vous devez renseigner tous les champs !", Toast.LENGTH_LONG).show();
        }

        else{

            mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
            String alreadyExist = mySQLiteOpenHelper.insertInscription(recup1, recup2, recup3, recup4, recup5);
            if(alreadyExist != null){
                Toast.makeText(this, alreadyExist, Toast.LENGTH_LONG).show();
            }
            else{
                openActivity2();
            }
            mySQLiteOpenHelper.close();
        }
    }

    private void btnInscription(){
        ((Button) findViewById(R.id.btnInscription)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                Inscription();
            }
        });
    }

    public void openActivity2(){
        Intent intent = new Intent(this, MainPreferences.class);
        startActivity(intent);
    }
    //Inscription ------------------------------------------------------------------<<<
}
