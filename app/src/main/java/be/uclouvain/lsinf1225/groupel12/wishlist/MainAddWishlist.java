package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainAddWishlist extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_wishlist);
        initAddWishlist();
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    private TextInputEditText txtList_name;
    private ToggleButton togglePrivacy;
    private String recup1, recup2, session;

    private void initAddWishlist(){
        txtList_name = (TextInputEditText) findViewById(R.id.txtList_name);
        togglePrivacy = (ToggleButton) findViewById(R.id.togglePrivacy);
        buttonSaveNewList();
    }

    private void AddWishList(){
        recup1 = txtList_name.getText().toString();
        recup2 = togglePrivacy.getText().toString();
        session = Session.getSession();

        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String alreadyExists = mySQLiteOpenHelper.insertAddWishlist(session, recup1, recup2);
        if(alreadyExists != null){
            Toast.makeText(this, alreadyExists, Toast.LENGTH_LONG).show();
        }
        else{
            mySQLiteOpenHelper.insertAddWishlist(session, recup1, recup2);
            openActivityProfil();
        }
        mySQLiteOpenHelper.close();
    }

    private void buttonSaveNewList() {
        ((Button) findViewById(R.id.buttonSaveNewList)).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                AddWishList();
            }
        });
    }

    public void openActivityProfil(){
        Intent intent = new Intent(this, MainProfil.class);
        startActivity(intent);
    }

}
