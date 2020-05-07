package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainWishlistFriend extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_wishlist_friend);

        bottomButton();
        buttonFriendPreferences();

        setTitle(StringMemory.getStringMemoryFriendName());

        addWishlistsToTab(StringMemory.getStringMemoryFriendName());
    }

    private String[] Tab;
    private void addWishlistsToTab(String username) {
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        Tab = mySQLiteOpenHelper.getWishlistsFriend(username);
        if (Tab != null) {
            for (int i = 0; i < Tab.length; i++) {
                LinearLayout tableau = (LinearLayout) findViewById(R.id.ScrollWishlistsTab);
                Button wishlistNameFriend = new Button(this);
                wishlistNameFriend.setText(Tab[i]);
                wishlistNameFriend.setTag(Tab[i]);
                wishlistNameFriend.setId(i);
                wishlistNameFriend.setOnClickListener(this);
                wishlistNameFriend.setTextSize(20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(45, 10, 30, 0);
                wishlistNameFriend.setLayoutParams(layoutParams);
                wishlistNameFriend.setTag(Tab[i]);
                wishlistNameFriend.setBackgroundResource(R.drawable.roundedbutton);
                tableau.addView(wishlistNameFriend);
            }
        }
    }

    @Override
    public void onClick(View v) {
        String str=v.getTag().toString();
        for (String s : Tab) {
            if (str.equals(s)) {
                StringMemory.initStringMemory(str);
                Intent intent = new Intent(this, MainContentWishlistFriend.class);
                startActivity(intent);
            }
        }
    }


    private void setTitle(String friendName){
        TextView friendNameTitle = (TextView) findViewById(R.id.profilFriendName);
        friendNameTitle.setText(friendName);
    }


    /* *************** BUTTON *********************** */
    /* Button friendPref---------------------------------------------------------------- */
    private void buttonFriendPreferences() {
        findViewById(R.id.friendPreferences).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityFriendPreferences();
            }
        })
        ;}
    public void openActivityFriendPreferences(){
        Intent intent = new Intent(this, MainFriendPreferences.class);
        startActivity(intent);
    }
    /* Button friendPref---------------------------------------------------------------- */
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
    /* *************** BUTTON *********************** */
}
