package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class MainLogin extends AppCompatActivity {

    EditText username;
    EditText password;
    boolean isValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_login);

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Button inscription = findViewById(R.id.inscription_btn);
        Button connexion = findViewById(R.id.connexion_btn);

        connexion.setOnClickListener(v -> {
            checkDataEntered();
            if(isValid){
                Toast.makeText(this, "Connexion en cours !", Toast.LENGTH_LONG).show();
            }
        });


        inscription.setOnClickListener(v -> {

            Intent intent = new Intent(this, MainInscription.class);

            startActivity(intent);

        });

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered(){
        isValid = true;
        if (isEmpty(username)) {
            username.setError("username is required");
            isValid = false;
        }

        if(isEmpty(password)){
            password.setError("Password is required");
            isValid = false;
        }

    }

}
