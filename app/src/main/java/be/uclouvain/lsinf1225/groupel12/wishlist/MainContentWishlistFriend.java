package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainContentWishlistFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content_wishlist_friend);
        addItemsToTab(StringMemory.getStringMemoryFriendName(), StringMemory.getStringMemory());

        bottomButton();

        setVariables();


    }
    private TextView titleWishlist;
    private TextView titleNameFriend;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String Tab[];

    private void addItemsToTab(String username, String wishlist) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String Tab[] = mySQLiteOpenHelper.getItems(username, wishlist);
        if (Tab != null) {
            for (int i = 0; i < Tab.length; i++) {
                LinearLayout tableau = (LinearLayout) findViewById(R.id.ScrollItemsTab);
                Button giftName = new Button(this);
                giftName.setText(Tab[i]);
                /*wishlistName.setOnClickListener(this);*/
                giftName.setTextSize(20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(45, 10, 30, 0);
                giftName.setLayoutParams(layoutParams);
                giftName.setTag(Tab[i]);
                giftName.setBackgroundResource(R.drawable.roundedbutton);
                tableau.addView(giftName);
            }
        }
    }

    /* Set Variables---------------------------------------------------------------- */
    private void setVariables(){
        titleNameFriend = (TextView)findViewById(R.id.textNameFriendProfil);
        titleNameFriend.setText(StringMemory.getStringMemoryFriendName());

        titleWishlist = (TextView)findViewById(R.id.NameFriendWishlist);
        titleWishlist.setText(StringMemory.getStringMemory());
    }
    /* Set Variables---------------------------------------------------------------- */

    /* *************** BUTTON *********************** */
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

    /*@Override
    public void onClick(View v) {
        String str = v.getTag().toString();
        for (int i = 0; i <Tab.length; i++){
            if (str.equals(Tab[i])){
                StringMemory.initStringMemory(str);
                Intent intent = new Intent(this, MainContentWishList.class);
                startActivity(intent);
            }
        }

    }*/
    /* Button Retour---------------------------------------------------------------- */
}
