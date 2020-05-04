package be.uclouvain.lsinf1225.groupel12.wishlist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.Session;
import be.uclouvain.lsinf1225.groupel12.wishlist.tools.StringMemory;

public class MainFriendPreferences extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_friend_preferences);
        recupPref(StringMemory.getStringMemory());
    }
    private MySQLiteOpenHelper mySQLiteOpenHelper;


    private TextView friendColour;
    private TextView friendVetements;
    private TextView friendChaussures;
    private TextView friendTheme;
    private TextView friendAdresse;


    private void friendPreferences(){
        friendColour = (TextView) findViewById(R.id.friendColour);
        friendVetements = (TextView) findViewById(R.id.friendVetements);
        friendChaussures = (TextView) findViewById(R.id.friendChaussures);
        friendTheme = (TextView) findViewById(R.id.friendTheme);
        friendAdresse = (TextView) findViewById(R.id.friendAdresse);
    }


    private void recupPref(String username){
        mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        String tabPref[] = mySQLiteOpenHelper.recupPrefs(username);
        friendColour.setText(tabPref[0]);
        friendVetements.setText(tabPref[1]);
        friendChaussures.setText(tabPref[2]);
        friendTheme.setText(tabPref[3]);
        friendAdresse.setText(tabPref[4]);
    }
}
