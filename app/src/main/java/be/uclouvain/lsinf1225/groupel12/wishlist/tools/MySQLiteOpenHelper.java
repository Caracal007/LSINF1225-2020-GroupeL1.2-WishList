package be.uclouvain.lsinf1225.groupel12.wishlist.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Wish.Inscription";
    private static final int DATABASE_VERSION = 1;

    public MySQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String creation= "create table profil ("
                + "colUsername text not null,"
                + "colPassword text not null,"
                + "colFirst_name text not null,"
                + "colLast_name text not null, "
                + "colEmail text not null"
                + ")";

        db.execSQL(creation);
        Log.i("DATABASE", "onCreate invoked");

        String creationPreferences= "create table preferences ("
                + "colUsername text not null,"
                + "colCouleur text not null,"
                + "colTailleVetements text not null,"
                + "colTailleChaussures text not null,"
                + "colTheme text not null,"
                + "colAdresse text not null,"
                + ")";

        db.execSQL(creationPreferences);
        Log.i("DATABASE", "onCreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i( "DATABASE", "onUpgrade invoked");
    }


    //Inscription ------------------------------------------------------------------>>>
    public String insertInscription(String username, String password, String first_name, String last_name, String email){

            String check = "select colUsername from profil where colUsername='"+username+"' or colEmail='"+email+"'";
            Cursor cursor = (this.getReadableDatabase()).rawQuery(check, null);
            int count = cursor.getCount();
            cursor.close();

            if (count == 0) {
                username = username.replace("'", "''");
                password = password.replace("'", "''");
                first_name = first_name.replace("'", "''");
                last_name = last_name.replace("'", "''");
                email = email.replace("'", "''");

                String creation = "insert into profil (colUsername, colPassword, colFirst_name, colLast_name, colEmail) values ('"
                        + username + "','" + password + "', '" + first_name + "','" + last_name + "','" + email + "')";
                this.getWritableDatabase().execSQL(creation);
                Log.i("DATABASE", String.valueOf(count));

                return null;
            }
            else{
                return "Username or Email Already exist";
            }
    }
    //Inscription ------------------------------------------------------------------>>>




    //Preferences ------------------------------------------------------------------>>>
    public void insertPreferences(String username, String couleur, String vetements, String chaussures, String theme, String adresse){

        String creation = "insert into preferences (colUsername, colCouleur, colTailleVetements, colTailleChaussures, colTheme, colAdresse) values ('"
                + username + "','" + couleur + "','" + vetements + "','" + chaussures + "','" + theme + "','" + adresse + "')";
        this.getWritableDatabase().execSQL(creation);

    }
    //Preferences ------------------------------------------------------------------>>>


    //Connection ------------------------------------------------------------------>>>

    public Boolean checkConnection(String username, String password) {
        String pass;

        String get_password = "select colPassword from profil where colUsername='"+username+"'";

        Cursor cursor = (this.getReadableDatabase()).rawQuery(get_password, null);


        int count = cursor.getCount();
        if (count == 0) {
            cursor.close();
            return false;
        }
        cursor.moveToFirst();
        pass = cursor.getString(0);
        cursor.close();
        if (pass.equals(password)) {
            return true;
        }
        else {return false;}

    }
    //Connection ------------------------------------------------------------------>>>

    //Inscription  ------------------------------------------------------------------>>>

    //Inscription  ------------------------------------------------------------------>>>

    //Profil ------------------------------------------------------------------>>>
    public String[] getLists(){
        String lists[] = new String[10];
        lists[0] = "null";
        return lists;
    }
    //Profil ------------------------------------------------------------------>>>
}


