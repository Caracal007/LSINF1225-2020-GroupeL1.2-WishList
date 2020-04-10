package be.uclouvain.lsinf1225.groupel12.wishlist.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

    private String creation="create table profil ("
            + "username TEXT NOT NULL,"
            + "password TEXT NOT NULL,"
            + "first_name TEXT NOT NULL,"
            + "last_name TEXT NOT NULL, "
            + "email TEXT NOT NULL);";







    public MySQLiteOpenHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(creation);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
