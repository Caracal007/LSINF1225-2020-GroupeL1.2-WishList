package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainFriend extends AppCompatActivity implements  View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_friend);
        allButton();
        addFriendlistsToTab(Session.getSession());

    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String[] Tab;

    private void addFriendlistsToTab(String username) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        Tab = mySQLiteOpenHelper.getFriendList(username);
        if (Tab != null) {
            for (int i = 0; i < Tab.length; i++) {
                LinearLayout tableau = (LinearLayout) findViewById(R.id.ScrollFriendListTab);
                Button friendName = new Button(this);
                friendName.setText(Tab[i]);
                friendName.setTag(Tab[i]);
                friendName.setId(i);
                friendName.setOnClickListener(this);
                friendName.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        String friendName = (String) v.getTag();
                        openDialog(friendName);
                        return false;
                    }
                });
                friendName.setTextSize(20);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(45, 10, 30, 0);
                friendName.setLayoutParams(layoutParams);
                friendName.setTag(Tab[i]);
                friendName.setBackgroundResource(R.drawable.roundedbutton);
                tableau.addView(friendName);
            }
        }
    }

    @Override
    public void onClick(View v) {
        String str=v.getTag().toString();
        for (String s : Tab) {
            if (str.equals(s)) {
                StringMemory.initStringMemoryFriendName(str);
                Intent intent = new Intent(this, MainWishlistFriend.class);
                startActivity(intent);
            }
        }

    }

    /* Delete Pop---------------------------------------------------------------- */
    @SuppressLint("ResourceAsColor")
    private void openDialog(String friendName ){
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("DELETE MESSAGE")
                .setMessage("Are you sur you want to delete "+ friendName + " of your friend's list ?" )
                .setIcon(R.drawable.icons8_poubelle_30)
                .setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        updateFriends(Session.getSession(), friendName );
                        openActivityFriend();
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(R.color.red);
        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(R.color.red);
    }

    private void updateFriends(String username, String friendName){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        mySQLiteOpenHelper.deleteFriend(username, friendName );
    }
    /* Delete Pop---------------------------------------------------------------- */

    private void allButton(){
        bottomButton();
        buttonAddFriend();
        buttonFriendNotification();
    }
    /* Button FriendRequest---------------------------------------------------------------- */
    private void buttonFriendNotification() {
        findViewById(R.id.buttonFriendRequest).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityFriendNotification();
            }
        })
        ;}
    public void openActivityFriendNotification(){
        Intent intent = new Intent(this, MainFriendNotification.class);
        startActivity(intent);
    }
    /* Button AddFriend---------------------------------------------------------------- */
    /* Button AddFriend---------------------------------------------------------------- */
    private void buttonAddFriend() {
        findViewById(R.id.buttonAddFriend).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                openActivityAddFriend();
            }
        })
        ;}
    public void openActivityAddFriend(){
        Intent intent = new Intent(this, MainAddFriend.class);
        startActivity(intent);
    }
    /* Button AddFriend---------------------------------------------------------------- */
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
