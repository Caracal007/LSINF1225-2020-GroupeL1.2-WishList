package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainProfil extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_profil);

        TextView session = (TextView) findViewById(R.id.session);
        session.setText(Session.getSession());
        addWishlistsToTab(Session.getSession());
        allButton();
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String Tab[];
    private void addWishlistsToTab(String username) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        Tab = mySQLiteOpenHelper.getLists(username);
        if (Tab != null) {
            for (int i = 0; i < Tab.length; i++) {
                LinearLayout tableau = (LinearLayout) findViewById(R.id.ScrollWishlistsTab);
                Button wishlistName = new Button(this);

                wishlistName.setText(Tab[i]);
                wishlistName.setTag(Tab[i]);
                wishlistName.setId(i);
                wishlistName.setOnClickListener(this);
                wishlistName.setTextSize(20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(45, 10, 30, 0);

                wishlistName.setLayoutParams(layoutParams);
                wishlistName.setTag(Tab[i]);
                wishlistName.setId(0);
                wishlistName.setBackgroundResource(R.drawable.roundedbutton);
                tableau.addView(wishlistName);
            }
        }
    }

    @Override
    public void onClick(View v) {
        String str = v.getTag().toString();
        int id = v.getId();
        for (int i = 0; i < Tab.length; i++) {
                if (str.equals(Tab[i])) {
                    if (id == 0) {
                        StringMemory.initStringMemory(str);
                        Intent intent = new Intent(this, MainContentWishList.class);
                        startActivity(intent);
                    } else {

                    }
                }
        }
    }

    /* *************** BUTTON *********************** */
    private void allButton(){
        buttonModifProfil();
        bottomButton();
        buttonAddWishlist();
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
    /* Button AddWishlist---------------------------------------------------------------- */
    private void buttonAddWishlist() {
        findViewById(R.id.buttonAddWishlist).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityAddWishlist();
            }
        })
        ;}
    public void openActivityAddWishlist(){
        Intent intent = new Intent(this, MainAddWishlist.class);
        startActivity(intent);
    }
    /* Button AddWishlist---------------------------------------------------------------- */

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
    /* *************** BUTTON *********************** */

    /* Button Retour---------------------------------------------------------------- */
    public void onBackPressed() {
        // do nothing.
    }
    /* Button Retour---------------------------------------------------------------- */

}
