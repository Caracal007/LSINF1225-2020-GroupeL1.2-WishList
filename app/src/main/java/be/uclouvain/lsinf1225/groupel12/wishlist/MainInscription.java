package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

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
    private boolean isValid;


    private void initInscription(){
        txtUsername = (TextInputEditText) findViewById(R.id.username);
        txtPassword = (TextInputEditText) findViewById(R.id.password);
        txtFirst_name = (TextInputEditText) findViewById(R.id.txtFirst_name);
        txtLast_name = (TextInputEditText) findViewById(R.id.txtLast_name);
        txtEmail = (TextInputEditText) findViewById(R.id.txtEmail);
        btnInscription();
    }

    boolean isEmailValid(CharSequence email) {
        return !(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    private void Inscription(){

        recup1 = txtUsername.getText().toString();
        recup2 = txtPassword.getText().toString();
        recup3 = txtFirst_name.getText().toString();
        recup4 = txtLast_name.getText().toString();
        recup5 = txtEmail.getText().toString();

        
        checkDataEntered();
        if(isEmailValid(recup5)){
            Toast.makeText(this, "Email is invalid.", Toast.LENGTH_LONG).show();
            isValid = false;
        }
        if(isValid){

            mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
            String alreadyExist = mySQLiteOpenHelper.insertInscription(recup1, recup2, recup3, recup4, recup5);
            if(alreadyExist != null){
                Toast.makeText(this, alreadyExist, Toast.LENGTH_LONG).show();
            }
            else{
                Session.initSession(recup1);
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

    private void checkDataEntered() {
        isValid = true;
        if (isEmpty(txtFirst_name)) {
            txtFirst_name.setError("First name is required");
            isValid = false;
        }

        if (isEmpty(txtLast_name)) {
            txtLast_name.setError("Last name is required");
            isValid = false;
        }

        if (isEmpty(txtEmail)) {
            txtEmail.setError("Email is required");
            isValid = false;
        }

        if (isEmpty(txtUsername)) {
            txtUsername.setError("Username is required");
            isValid = false;
        }
        if (isEmpty(txtPassword)) {
            txtPassword.setError("Password is required");
            isValid = false;
        }
    }

    private boolean isEmpty(TextInputEditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    public void openActivity2(){
        Intent intent = new Intent(this, MainPreferences.class);
        startActivity(intent);
    }
    //Inscription ------------------------------------------------------------------<<<
}
