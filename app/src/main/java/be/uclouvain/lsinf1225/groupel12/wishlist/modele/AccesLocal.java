package be.uclouvain.lsinf1225.groupel12.wishlist.modele;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import be.uclouvain.lsinf1225.groupel12.wishlist.tools.MySQLiteOpenHelper;

public class AccesLocal {

    private String nameBase = "bd.sqLite";
    private Integer versionBase = 1;
    private MySQLiteOpenHelper accesBd;
    private SQLiteDatabase bd;

    public AccesLocal(Context context){
        accesBd = new MySQLiteOpenHelper(context, nameBase, null, versionBase);
    }

    /**
     * Ajout d'une inscription dans la db
     * @param profil
     */
    public void add(Profil profil){
        bd = accesBd.getWritableDatabase();
        String req = "insert into profil (username, password, first_name, last_name, email) values ";
        req += "("+profil.getPseudo()+","+profil.getMdp()+","+profil.getPrénom()+","+profil.getNom()+","+profil.getMail()+")";
        bd.execSQL(req);
    }

    /**
     * Return le dernier profil de la base de donné (A MODIFIER)
     * @return
     */
    public Profil recupDernier() {
        bd = accesBd.getReadableDatabase();
        Profil profil = null;
        String req = "select * from profil";
        Cursor cursor = bd.rawQuery(req, null);
        cursor.moveToLast();
        if(!cursor.isAfterLast()){
            String username = cursor.getString(0);
            String password = cursor.getString(1);
            String first_name = cursor.getString(2);
            String last_name = cursor.getString(3);
            String email = cursor.getString(4);

            profil = new Profil(username, password, first_name, last_name, email);
        }
        cursor.close();
        return profil;
    }

}
