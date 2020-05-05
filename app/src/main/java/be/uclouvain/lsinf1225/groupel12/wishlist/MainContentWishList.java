package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.annotation.DrawableRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainContentWishList extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_content_wishlist);
        addItemsToTab(Session.getSession(), StringMemory.getStringMemory());

        bottomButton();

        setPrivacy(Session.getSession(), StringMemory.getStringMemory());

        titleWishlist = (TextView)findViewById(R.id.textNamewishlistTitle);
        titleWishlist.setText(StringMemory.getStringMemory());
    }
    private TextView titleWishlist;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String Tab[];

    private void addItemsToTab(String username, String wishlist) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        Tab = mySQLiteOpenHelper.getItems(username, wishlist);
        if (Tab != null) {
            for (int i = 0; i < Tab.length; i++) {
                LinearLayout tableau = (LinearLayout) findViewById(R.id.ScrollItemsTab);
                Button giftName = new Button(this);
                giftName.setText(Tab[i]);
                giftName.setOnClickListener(this);
                giftName.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        String giftName = (String) v.getTag();
                        openDialog(giftName);
                        return false;
                    }
                });
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
    /* Set Privacy---------------------------------------------------------------- */
    private void setPrivacy(String username, String whishlistName){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        ImageView Privacy = findViewById(R.id.imagePrivacyWishlist);
        if(mySQLiteOpenHelper.getPrivacy(username, whishlistName)){
            Privacy.setImageResource(R.drawable.icons8_cadenas_64);
        }
        else {
            Privacy.setImageResource(R.drawable.icons8_user_group_2_96);
        }
    }
    /* Set Privacy---------------------------------------------------------------- */

    /* Delete Pop---------------------------------------------------------------- */
    @SuppressLint("ResourceAsColor")
    private void openDialog(String giftName){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("DELETE MESSAGE")
                .setMessage("Are you sur you want to delete "+ giftName +" of your gift's list ?")
                .setIcon(R.drawable.icons8_poubelle_30)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateWishlist(Session.getSession(), giftName, StringMemory.getStringMemory());
                        openActivityWishlistitems();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.red);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.red);
    }

    private void updateWishlist(String username, String giftName, String wishlistName){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        mySQLiteOpenHelper.deleteItemFromeWishlist(username, wishlistName, giftName);
    }
    /* Delete Pop---------------------------------------------------------------- */

    public void openActivityWishlistitems(){
        Intent intent = new Intent(this, MainContentWishList.class);
        startActivity(intent);
    }

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

    @Override
    public void onClick(View v) {
        String str = v.getTag().toString();
        for (int i = 0; i <Tab.length; i++){
            if (str.equals(Tab[i])){
                StringMemory.initStringMemoryGiftName(str);
                Intent intent = new Intent(this, MainItemDescription.class);
                startActivity(intent);
            }
        }

    }
}
