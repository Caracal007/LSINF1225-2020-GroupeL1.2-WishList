package be.uclouvain.lsinf1225.groupel12.wishlist.tools;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;


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
        Log.i("DATABASE", "oncreate invoked");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String creation ="drop table profil";
        db.execSQL(creation);
        this.onCreate(db);
        Log.i( "DATABASE", "onUpgrade invoked");
    }



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
}
