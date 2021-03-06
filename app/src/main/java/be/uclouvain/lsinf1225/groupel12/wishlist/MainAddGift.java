package be.uclouvain.lsinf1225.groupel12.wishlist;


import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainAddGift extends AppCompatActivity {

    EditText name;
    EditText description;
    EditText price;
    EditText url;
    private Spinner spinnerChooseWishlist;
    boolean isValid;
    private MySQLiteOpenHelper mySQLiteOpenHelper;
    private String[] WishTab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_add_gift);

        name = findViewById(R.id.name);
        description = findViewById(R.id.description);
        price = findViewById(R.id.price);
        url = findViewById(R.id.url);
        spinnerChooseWishlist = findViewById(R.id.spinnerChooseWishlist);
        Button add = findViewById(R.id.add);

        bottomButton();
        setWishTab(Session.getSession());

        setScroll();

        add.setOnClickListener(v -> {
            checkDataEntered();
            if (isValid) {
                if(WishTab != null) {
                    mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
                    String name_txt = name.getText().toString();
                    String description_txt = description.getText().toString();
                    String price_txt = price.getText().toString();
                    String url_txt = url.getText().toString();
                    String wishlist_txt = spinnerChooseWishlist.getSelectedItem().toString();
                    mySQLiteOpenHelper.addItem(name_txt, description_txt, price_txt, url_txt, wishlist_txt, Session.getSession());
                    mySQLiteOpenHelper.close();
                    Toast.makeText(this, "Gift added", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(this, MainContentWishList.class);

                    startActivity(intent);
                }
                else{
                    Toast.makeText(this, "You need to create a wishlist first to add a gift !", Toast.LENGTH_LONG).show();
                    openActivityAddWishlist();
                }
            }
        });
    }
    public void setScroll(){
        if (WishTab != null) {
            for (int i = 0; i < WishTab.length; i++) {
                if (WishTab[i].equals(StringMemory.getStringMemory())) {
                    spinnerChooseWishlist.setSelection(i);
                }
            }
        }
    }
    public void setWishTab(String username){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        WishTab = mySQLiteOpenHelper.getLists(username);
        ArrayList<String> wishList = new ArrayList<>();
        if (WishTab != null) {
            Collections.addAll(wishList, WishTab);
            Spinner spinner = (Spinner) findViewById(R.id.spinnerChooseWishlist);
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, wishList);
            spinner.setAdapter(adapter);
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
    /* Button Friend---------------------------------------------------------------- */
    /* BOTTOM BUTTON */
    public void openActivityAddWishlist(){
        Intent intent = new Intent(this, MainAddWishlist.class);
        startActivity(intent);
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        isValid = true;
        if (isEmpty(name)) {
            name.setError("Name is required");
            isValid = false;
        }
    }
}
