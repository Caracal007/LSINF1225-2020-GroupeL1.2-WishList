package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lol();

    }
    private void lol(){
        ((Button) findViewById(R.id.btnInscription)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "mdr", Toast.LENGTH_LONG).show();
            }
        });
    }
}
