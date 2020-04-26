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
                + "colAdresse text not null"
                + ")";

        db.execSQL(creationPreferences);
        Log.i("DATABASE", "onCreate invoked");

        String creationWishlist= "create table wishlists ("
                + "colUsername text not null,"
                + "colWishlistName text not null,"
                + "colPrivacy boolean not null"
                + ")";

        db.execSQL(creationWishlist);
        Log.i("DATABASE", "onCreate invoked");

        String creationItems = "create table items ("
                + "name text not null,"
                + "description text not null,"
                + "price text not null,"
                + "url text not null"
                + ")";

        db.execSQL(creationItems);
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

        String check = "select colUsername from profil where colUsername='"+username+"'";
        Cursor cursor = (this.getReadableDatabase()).rawQuery(check, null);
        int count = cursor.getCount();
        cursor.close();

        /*username = username.replace("'", "''");*/
        couleur = couleur.replace("'", "''");
        vetements = vetements.replace("'", "''");
        chaussures = chaussures.replace("'", "''");
        theme = theme.replace("'", "''");
        adresse = adresse.replace("'", "''");

        if (count != 0) {
            String delete = "delete from preferences where colUsername='"+username+"'";
            this.getWritableDatabase().execSQL(delete);
        }

        String creation = "insert into preferences (colUsername, colCouleur, colTailleVetements, colTailleChaussures, colTheme, colAdresse) values ('"
                + username + "','" + couleur + "','" + vetements + "','" + chaussures + "','" + theme + "','" + adresse + "')";
        this.getWritableDatabase().execSQL(creation);


    }
    //Preferences --------------------------2020-04-23 01:12:28.443 16472-16472/? E/upel12.wishlis: Unknown bits set in runtime_flags: 0x8000---------------------------------------->>>


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

    //Profil ------------------------------------------------------------------>>>
    public String[] getLists(String username){

        String take = "select * from wishlists where colUsername='" + username + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        int count = cursor.getCount();

        if (count == 0){
            return null;
        }
        String lists[] = new String[count];

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            for (int i = 0; i < count; i++) {
                lists[i] = cursor.getString(1);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lists;
    }
    //Profil ------------------------------------------------------------------>>>


    //Add Wishlist -------------------------------------------------------------->>>

    public String insertAddWishlist(String username, String nameList, String privacy){

        String check = "select colUsername from wishlists where colUsername='"+username+"' and colWishlistName='"+nameList+"'";
        Cursor cursor = (this.getReadableDatabase()).rawQuery(check, null);
        int count = cursor.getCount();
        cursor.close();

        if (count == 0) {
            nameList = nameList.replace("'", "''");

            String creation = "insert into wishlists (colUsername, colWishlistName, colPrivacy) values ('"
                    + username + "','" + nameList + "','" + privacy + "')";
            this.getWritableDatabase().execSQL(creation);
            Log.i("DATABASE", String.valueOf(count));

            return null;
        }
        else {
            return ("You already have a list called " + nameList);
        }
    }
    //Add Wishlist -------------------------------------------------------------->>>

    //Add Gift ------------------------------------------------------------------>>>

    public void addItem(String name, String description, String price, String url) {
        String creation = "insert into items (name, description, price, url) values ('"
                + name + "','" + description + "', '" + price + "','" + url + "')";
        this.getWritableDatabase().execSQL(creation);
    }

    //Add Gift ------------------------------------------------------------------>>>
}


