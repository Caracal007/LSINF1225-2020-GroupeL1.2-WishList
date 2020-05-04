package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainAddFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_friend);

        bottomButton();
        buttonSearch();
        buttonAddFriend();

    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    private void checkFriendName() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        TextView friendToAdd = (TextView) findViewById(R.id.friendToAdd);
        String friendName = ((TextInputEditText) findViewById(R.id.searchView)).getText().toString();
        boolean check = mySQLiteOpenHelper.checkFriendName(friendName);
        if (friendName.length() != 0) {
            if (check) {
                if (friendName.equals(Session.getSession())) {
                    friendToAdd.setText("You can't add yourself");
                    friendToAdd.setTypeface(null, Typeface.ITALIC);
                }
                else {
                    friendToAdd.setText(friendName);
                }
            }
            else {
                friendToAdd.setText("No person found ...");
                friendToAdd.setTypeface(null, Typeface.ITALIC);
            }
        }
    }

    private void addFriend(String friendName){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        boolean ok = mySQLiteOpenHelper.addFriend(friendName);
        if(ok && !friendName.equals(Session.getSession())){
            Toast.makeText(this, "Une invitation a été envoyée", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this, "Ami incorrect !", Toast.LENGTH_LONG).show();
        }
    }
    private void buttonAddFriend(){
        findViewById(R.id.addFriendButton).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                TextView friendToAdd = (TextView) findViewById(R.id.friendToAdd);
                String friendName = ((TextInputEditText) findViewById(R.id.searchView)).getText().toString();
                addFriend(friendName);
            }
        })
        ;}

    private void buttonSearch() {
        findViewById(R.id.searchButton).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                checkFriendName();
            }
        })
        ;}

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
