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

public class MainDescriptionItemFriend extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_description_item_friend);
        bottomButton();
        setTextItemName();
        setTextItemDescription();
    }

    private TextView titleItemName, urlItem, priceItem, descriptionItem;
    private MySQLiteOpenHelper mySQLiteOpenHelper;

    public void setTextItemName() {
        titleItemName = (TextView) findViewById(R.id.itemName);
        titleItemName.setText(StringMemory.getStringMemoryGiftName());
    }

    public void setTextItemDescription() {
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String Tab[] = mySQLiteOpenHelper.getItemDescription(StringMemory.getStringMemoryFriendName(), StringMemory.getStringMemory(), StringMemory.getStringMemoryGiftName());
        Log.i("TAG", StringMemory.getStringMemoryFriendName() + " + " + StringMemory.getStringMemory() + " + " + StringMemory.getStringMemoryGiftName());
        urlItem = (TextView) findViewById(R.id.urlItem);
        priceItem = (TextView) findViewById(R.id.priceItem);
        descriptionItem = (TextView) findViewById(R.id.descriptionItem);
        urlItem.setText(Tab[0]);
        priceItem.setText(Tab[1]);
        descriptionItem.setText(Tab[2]);
    }

    public void openActivityEdit() {
        Intent intent = new Intent(this, MainEditGiftDescription.class);
        startActivity(intent);
    }


    /* BOTTOM BUTTON */
    private void bottomButton() {
        buttonAddGift();
        buttonProfil();
        buttonFriend();
    }

    /* Button AddGift---------------------------------------------------------------- */
    private void buttonAddGift() {
        findViewById(R.id.buttonAddGift).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                openActivityAddGift();
            }
        })
        ;
    }

    public void openActivityAddGift() {
        Intent intent = new Intent(this, MainAddGift.class);
        startActivity(intent);
    }

    /* Button AddGift---------------------------------------------------------------- */
    /* Button Profil---------------------------------------------------------------- */
    private void buttonProfil() {
        findViewById(R.id.buttonProfil).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                openActivityProfil();
            }
        })
        ;
    }

    public void openActivityProfil() {
        Intent intent = new Intent(this, MainProfil.class);
        startActivity(intent);
    }

    /* Button Profil---------------------------------------------------------------- */
    /* Button Friend---------------------------------------------------------------- */
    private void buttonFriend() {
        findViewById(R.id.buttonFriends).setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                openActivityFriend();
            }
        })

        ;
    }

    public void openActivityFriend() {
        Intent intent = new Intent(this, MainFriend.class);
        startActivity(intent);
    }

    /* Button Friend---------------------------------------------------------------- */
    /* BOTTOM BUTTON */
}