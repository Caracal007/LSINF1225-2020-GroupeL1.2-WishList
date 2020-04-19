package be.uclouvain.lsinf1225.groupel12.wishlist.tools;

public class Session {
    static public String session;

    static public void initSession(String username){
        session = username;
    }
    static public String getSession(){
        return session;
    }
}
