package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_preferences);

        Button inscription = findViewById(R.id.buttonMajPreferences);
        inscription.setOnClickListener(v -> {

            Intent intent = new Intent(this, MainProfil.class);

            startActivity(intent);
        });

    }
    @Override
    public void onBackPressed() {
        // do nothing.
    }

}
