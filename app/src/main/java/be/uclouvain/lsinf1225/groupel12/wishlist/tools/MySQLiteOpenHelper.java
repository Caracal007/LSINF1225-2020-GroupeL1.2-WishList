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
                + "colPrivacy text not null"
                + ")";

        db.execSQL(creationWishlist);
        Log.i("DATABASE", "onCreate invoked");

        String creationItems = "create table items ("
                + "name text not null,"
                + "wishlist text not null,"
                + "description text not null,"
                + "price text not null,"
                + "url text not null,"
                + "username text not null"
                + ")";

        db.execSQL(creationItems);
        Log.i("DATABASE", "onCreate invoked");

        String creationFriends = "create table friends ("
                + "colSender not null,"
                + "colReceptionist text not null,"
                + "colState text not null"
                + ")";

        db.execSQL(creationFriends);
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
        if(adresse == null){
            adresse = " ";
        }
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
    public String[] recupPrefs(String username){
        String listPref[] = new String[5];
        String take = "select * from preferences where colUsername='" + username + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            int j = 0;
            for (int i = 1; i <= 5; i++) {
                if (!cursor.isAfterLast()) {
                    listPref[j] = cursor.getString(i);
                    j++;
                } else {
                    cursor.close();
                }
            }
            return listPref;
        }
        return null;
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
            privacy = privacy.replace("'", "''");
            String creation = "insert into wishlists (colUsername, colWishlistName, colPrivacy) values ('"
                    + username + "','" + nameList + "','" + privacy + "')";
            this.getWritableDatabase().execSQL(creation);

            return null;
        }
        else {
            return ("You already have a list called " + nameList);
        }
    }
    //Add Wishlist -------------------------------------------------------------->>>

    //Add Gift ------------------------------------------------------------------>>>

    public void addItem(String name, String description, String price, String url, String wishlist, String username) {
        name = name.replace("'", "''");
        description = description.replace("'", "''");
        price = price.replace("'", "''");
        url = url.replace("'", "''");
        String creation = "insert into items (name, wishlist, description, price, url, username) values ('"
                + name + "','" + wishlist + "','" + description + "', '" + price + "','" + url + "','" + username + "')";
        this.getWritableDatabase().execSQL(creation);
    }

    //Add Gift ------------------------------------------------------------------>>>

    //Modif Profil ------------------------------------------------------------------>>>
    public String[] recupInfo (String username){
        String listinfo[] = new String[3];
        String take = "select * from profil where colUsername='" + username + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        cursor.moveToFirst();
        int j = 0;
        for(int i =2; i <= 4; i++) {
            if(!cursor.isAfterLast()) {
                listinfo[j] = cursor.getString(i);
                j++;
            }
            else{
                cursor.close();
            }
        }
        return listinfo;
    }
    public void modifInfo(String username, String first_name, String last_name, String email){

        first_name = first_name.replace("'", "''");
        last_name = last_name.replace("'", "''");
        email = email.replace("'", "''");

        String modif = "update profil set colFirst_name = '"+first_name+"' ,colLast_name = '"+last_name+"',colEmail = '"+email+"' where colUsername='"+username+"'";
        this.getWritableDatabase().execSQL(modif);

    }
    //Modif Profil ------------------------------------------------------------------>>>

    //Friend ------------------------------------------------------------------>>>
    public String[] getFriendList(String username){

        String take = "select * from friends where (colSender='" + username + "' and colState='" + "Accepted" + "') or colReceptionist='" + username + "' and colState='" + "Accepted" + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        int count = cursor.getCount();

        if (count == 0){
            return null;
        }
        String lists[] = new String[count];

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            for (int i = 0; i < count; i++) {
                if(cursor.getString(1).equals(username)) {
                    lists[i] = cursor.getString(0);
                }
                else{
                    lists[i] = cursor.getString(1);
                }
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lists;
    }

    public void deleteFriend(String username, String friendName){
        String deleteFriend = "delete from friends where (colSender='" + username + "' and colReceptionist ='" + friendName + "') or (colReceptionist='" + username + "' and colSender ='" + friendName + "')";
        this.getWritableDatabase().execSQL(deleteFriend);
    }

    //Friend ------------------------------------------------------------------>>>

    //Add Friend ------------------------------------------------------------------>>>
    public boolean checkFriendName(String friendName){
        friendName = friendName.replace("'", "''");
        String take = "select * from profil where colUsername='" + friendName + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        if(cursor.getCount() != 0){
            return true;
        }
        return false;
    }
    public boolean addFriend(String friendName){
        String take = "select * from profil where colUsername='" + friendName + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        String check = "select * from friends where (colSender='" + Session.getSession() + "' and colReceptionist='" + friendName + "') or (colSender='" + friendName + "' and colReceptionist='" + Session.getSession() + "')";
        Cursor cursorcheck = this.getReadableDatabase().rawQuery(check, null);
        if(cursorcheck.getCount() == 0 && cursor.getCount() != 0 && !friendName.equals(Session.getSession())){
            friendName = friendName.replace("'", "''");
            String creation = "insert into friends (colSender, colReceptionist, colState) values ('"
                    + Session.getSession() + "','" + friendName + "','" + "sended" + "')";
            this.getWritableDatabase().execSQL(creation);
            return true;
        }
        return false;
    }
    //Add Friend ------------------------------------------------------------------>>>

    //FriendNotification------------------------------------------------------------------>>>
    public String[] getFriendsRequestsLists(String username) {
        String state = "sended";
        String take = "select colSender from friends where colReceptionist='" + username + "'and colState='" + state + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        int count = cursor.getCount();

        if (count == 0) {
            return null;
        }
        String lists[] = new String[count];

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            for (int i = 0; i < count; i++) {
                lists[i] = cursor.getString(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lists;
    }
    public void requestAccepted(String username, String friendName){
        friendName = friendName.replace("'", "''");

        String modif = "update friends set colState = '"+"Accepted"+"'where colReceptionist='"+username+"' and colSender='"+friendName+"'";
        this.getWritableDatabase().execSQL(modif);
    }
    public void requestDenied(String username, String friendName){
        friendName = friendName.replace("'", "''");

        String modif = "update friends set colState = '"+"Denied"+"'where colReceptionist='"+username+"' and colSender='"+friendName+"'";
        this.getWritableDatabase().execSQL(modif);
    }
    //FriendNotification------------------------------------------------------------------>>>

    //ContentWishlist--------------------------------------------------------------------->>>
    public String[] getItems(String username, String namelist){

        String take = "select * from items where username='" + username + "' and wishlist ='" + namelist + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        int count = cursor.getCount();

        if (count == 0){
            return null;
        }
        String lists[] = new String[count];

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            for (int i = 0; i < count; i++) {
                lists[i] = cursor.getString(0);
                cursor.moveToNext();
            }
        }
        cursor.close();
        return lists;
    }
    //ContentWishlist--------------------------------------------------------------------->>>

    //Wishlist--------------------------------------------------------------------->>>
    public boolean getPrivacy(String username, String wishlistName){
        String take = "select * from wishlists where colUsername='" + username + "' and colWishlistName ='" + wishlistName + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        if(cursor.getCount() != 0) {
            cursor.moveToFirst();
            if (cursor.getString(2).equals("ON")) {
                return true;
            }
        }
        return false;
    }

    public void deleteWishlists(String username, String wishlistName){
        String deleteWishlist = "delete from wishlists where colUsername='" + username + "' and colWishlistName ='" + wishlistName + "'";
        this.getWritableDatabase().execSQL(deleteWishlist);
        try {
            String deleteItems = "delete from items where username='" + username + "' and wishlistName ='" + wishlistName + "'";
            this.getWritableDatabase().execSQL(deleteItems);
        }
        catch (Exception e){

        }
    }
    //Wishlist--------------------------------------------------------------------->>>

    //WishlistFriendList--------------------------------------------------------------------->>>
    public String[] getWishlistsFriend(String friendName){

        String take = "select * from wishlists where colUsername='" + friendName + "' and colPrivacy='" + "OFF" + "'" ;
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
    //WishlistFriendList--------------------------------------------------------------------->>>

    //ItemDescription--------------------------------------------------------------------->>>
    public String[] getItemDescription(String username, String nameList, String nameItem){
        String take = "select * from items where username='" + username + "' and wishlist ='" + nameList + "' and name ='" + nameItem + "'";
        Cursor cursor = this.getReadableDatabase().rawQuery(take, null);
        int count = cursor.getCount();

        if (count == 0){
            return null;
        }
        String lists[] = new String[3];

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            lists[0] = cursor.getString(4);
            lists[1] = cursor.getString(3);
            lists[2] = cursor.getString(2);
            cursor.moveToNext();
        }
        cursor.close();
        return lists;
    }
    //ItemDescription--------------------------------------------------------------------->>>
    //DeleteItem--------------------------------------------------------------------->>>
    public void deleteItemFromeWishlist(String username, String wishlistName, String itemName){
        String deleteItem = "delete from items where username='" + username + "' and wishlist ='" + wishlistName + "' and name = '" + itemName + "'";
        this.getWritableDatabase().execSQL(deleteItem);
    }
    //DeleteItem--------------------------------------------------------------------->>>

    //EditItemDescription--------------------------------------------------------------------->>>
    public void updateItemDescription(String nameItemEdited, String urlItem, String priceItem, String descriptionItem, String username, String nameList, String nameItem){
        nameItemEdited = nameItemEdited.replace("'", "''");
        urlItem = urlItem.replace("'", "''");
        priceItem = priceItem.replace("'", "''");
        descriptionItem = descriptionItem.replace("'", "''");

        String modif = "update items set name = '"+nameItemEdited+"',  url = '"+urlItem+"', price = '"+priceItem+"', description = '"+descriptionItem+"' where username='" + username + "' and wishlist ='" + nameList + "' and name ='" + nameItem + "'";
        this.getWritableDatabase().execSQL(modif);
    }
    //EditItemDescription--------------------------------------------------------------------->>>
}


