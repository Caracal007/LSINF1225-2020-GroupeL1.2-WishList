package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.android.material.textfield.TextInputEditText;

import java.util.Objects;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainAddWishlist extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_wishlist);
        initAddWishlist();
    }

    private TextInputEditText txtList_name;
    private CheckBox switchPrivacy;

    private void initAddWishlist(){
        txtList_name = findViewById(R.id.txtList_name);
        buttonSaveNewList();
        bottomButton();
    }

    private void AddWishList(String privacy){
        String recup1 = Objects.requireNonNull(txtList_name.getText()).toString();
        String session = Session.getSession();

        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String alreadyExists = mySQLiteOpenHelper.checkWishlistAlreadyExist(session, recup1, privacy);
        if(alreadyExists != null){
            Toast.makeText(this, alreadyExists, Toast.LENGTH_LONG).show();
        }
        else if(recup1.matches("")){
            Toast.makeText(this, "Poor wishlist, it deserves a name !", Toast.LENGTH_LONG).show();
        }
        else{
            mySQLiteOpenHelper.insertAddWishlist(session, recup1, privacy);
            openActivityProfil();
        }
        mySQLiteOpenHelper.close();
    }



    private void buttonSaveNewList() {
        switchPrivacy = (CheckBox) findViewById(R.id.switchPrivacy);
        ((Button) findViewById(R.id.buttonSaveNewList)).setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (switchPrivacy.isChecked()) {
                        AddWishList("ON");
                    } else {
                        AddWishList("OFF");
                    }
                }
            }
        );
    }


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
