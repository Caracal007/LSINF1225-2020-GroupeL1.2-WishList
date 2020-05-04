package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainEditGiftDescription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_edit_gift_description);

        setTextItemName();
        setTextItemDescription();

        bottomButton();

        buttonSave();
    }

    private TextView titleItemName, urlItem, priceItem, descriptionItem;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    public void setTextItemName(){
        titleItemName = (TextView)findViewById(R.id.itemName);
        titleItemName.setText(StringMemory.getStringMemoryGiftName());
    }

    public void setTextItemDescription(){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String Tab[] = mySQLiteOpenHelper.getItemDescription(Session.getSession(), StringMemory.getStringMemory(), StringMemory.getStringMemoryGiftName());
        urlItem = (TextView)findViewById(R.id.urlItem);
        priceItem = (TextView)findViewById(R.id.priceItem);
        descriptionItem = (TextView)findViewById(R.id.descriptionItem);

        urlItem.setText(Tab[0]);
        priceItem.setText(Tab[1]);
        descriptionItem.setText(Tab[2]);
    }

    public void updateDescription(){
        urlItem = (TextView)findViewById(R.id.urlItem);
        priceItem = (TextView)findViewById(R.id.priceItem);
        descriptionItem = (TextView)findViewById(R.id.descriptionItem);
        titleItemName = (TextView)findViewById(R.id.itemName);

        String name = titleItemName.getText().toString();
        String url = urlItem.getText().toString();
        String price = priceItem.getText().toString();
        String description = descriptionItem.getText().toString();

        mySQLiteOpenHelper.updateItemDescription(name, url, price, description, Session.getSession(), StringMemory.getStringMemory(),StringMemory.getStringMemoryGiftName());;
    }

    /* Button AddGift---------------------------------------------------------------- */
    private void buttonSave() {
        findViewById(R.id.saveButton).setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v) {
                Log.i("lol", "lol");
                updateDescription();
                openActivityitemDescription();
            }
        })
        ;}
    public void openActivityitemDescription(){
        Intent intent = new Intent(this,MainProfil.class);
        startActivity(intent);
    }
    /* Button AddGift---------------------------------------------------------------- */

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
