package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainFriendNotification extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_friend_notification);

        bottomButton();
        addFriendsRequestToTab(Session.getSession());
    }

    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String[] Tab;
    private void addFriendsRequestToTab(String username) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        Tab = mySQLiteOpenHelper.getFriendsRequestsLists(username);
        if (Tab != null) {
            for (int i = 0; i < Tab.length; i++) {

                LinearLayout tableau = (LinearLayout) findViewById(R.id.ScrollViewFriendsRequest);
                LinearLayout group = new LinearLayout(this);

                TextView FriendsRequest = new TextView(this);
                FriendsRequest.setText(Tab[i]);
                FriendsRequest.setTextSize(30);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(45, 10, 30, 0);
                layoutParams.weight = 0.6f;
                FriendsRequest.setLayoutParams(layoutParams);

                ImageButton add = new ImageButton(this);
                add.setImageResource(R.drawable.icons8_coche_100__1_);
                add.setBackgroundResource(R.drawable.roundedbutton);
                ImageButton delete = new ImageButton(this);
                delete.setImageResource(R.drawable.icons8_effacer_100);
                delete.setBackgroundResource(R.drawable.roundedbutton);

                add.setTag(Tab[i]);
                add.setId(0);
                add.setOnClickListener(this);

                delete.setTag(Tab[i]);
                delete.setId(-1);
                delete.setOnClickListener(this);

                LinearLayout.LayoutParams layoutParams2 = new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams2.setMargins(45, 10, 10, 0);
                layoutParams2.weight = 0.2f;
                layoutParams2.gravity= Gravity.RIGHT;

                LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(
                        0, LinearLayout.LayoutParams.WRAP_CONTENT);
                layoutParams4.setMargins(10, 10, 30, 0);
                layoutParams4.weight = 0.2f;
                layoutParams4.gravity= Gravity.RIGHT;

                add.setLayoutParams(layoutParams2);
                delete.setLayoutParams(layoutParams4);

                LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                group.setWeightSum(1f);
                group.addView(FriendsRequest);
                group.addView(add);
                group.addView(delete);
                group.setLayoutParams(layoutParams3);

                tableau.addView(group);
            }
        }
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

    @Override
    public void onClick(View v) {
        String str=v.getTag().toString();
        int id=v.getId();
        for(int i = 0; i <Tab.length; i++) {
            if (str.equals(Tab[i])) {
                if (id == -1) {
                    mySQLiteOpenHelper.requestDenied(Session.getSession(), Tab[i]);
                    Toast.makeText(getApplicationContext(), "L'invitation de " +Tab[i] + " a été supprimée", Toast.LENGTH_LONG).show();
                    openActivityFriendNotification();
                }
                else {
                    mySQLiteOpenHelper.requestAccepted(Session.getSession(), Tab[i]);
                    Toast.makeText(getApplicationContext(), Tab[i] + " a été ajouté à votre liste d'amis", Toast.LENGTH_LONG).show();
                    openActivityFriendNotification();
                }
            }
        }
    }
    public void openActivityFriendNotification(){
        Intent intent = new Intent(this, MainFriendNotification.class);
        startActivity(intent);
    }
    /* Button Friend---------------------------------------------------------------- */
    /* BOTTOM BUTTON */
}
