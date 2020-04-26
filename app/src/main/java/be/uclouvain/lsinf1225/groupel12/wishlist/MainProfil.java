package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;

public class MainProfil extends AppCompatActivity {

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

    private void addWishlistsToTab(String username) {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String Tab[] = mySQLiteOpenHelper.getLists(username);
        if (Tab != null) {
            for (int i = 0; i < Tab.length; i++) {
                LinearLayout tableau = (LinearLayout) findViewById(R.id.ScrollWishlistsTab);
                TextView wishlistName = new TextView(this);
                wishlistName.setText(Tab[i]);
                wishlistName.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, AbsListView.LayoutParams.WRAP_CONTENT));

                tableau.addView(wishlistName);
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
