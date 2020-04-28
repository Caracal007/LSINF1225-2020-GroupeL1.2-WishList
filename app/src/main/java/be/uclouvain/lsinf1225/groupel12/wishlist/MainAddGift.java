package be.uclouvain.lsinf1225.groupel12.wishlist;


import androidx.appcompat.app.AppCompatActivity;

import android.text.TextUtils;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;

public class MainAddGift extends AppCompatActivity {

    EditText name;
    EditText description;
    EditText price;
    EditText url;
    private Spinner spinnerChooseWishlist;
    boolean isValid;
    private MySQLiteOpenHelper mySQLiteOpenHelper;


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

        add.setOnClickListener(v -> {
            checkDataEntered();
            if (isValid) {
                mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
                String name_txt = name.getText().toString();
                String description_txt = description.getText().toString();
                String price_txt = price.getText().toString();
                String url_txt = url.getText().toString();
                String wishlist_txt = spinnerChooseWishlist.getSelectedItem().toString();
                mySQLiteOpenHelper.addItem(name_txt, wishlist_txt, description_txt, price_txt, url_txt);
                mySQLiteOpenHelper.close();
                Toast.makeText(this, "Gift added", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(this, MainContentWishlist.class);

                startActivity(intent);
            }
        });
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

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        isValid = true;
        if (isEmpty(name)) {
            name.setError("name is required");
            isValid = false;
        }
    }
}
