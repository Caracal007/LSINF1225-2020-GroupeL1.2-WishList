package be.uclouvain.lsinf1225.groupel12.wishlist.tools;

public class StringMemory {
    static public String stringmemory;

    static public void initStringMemory(String username){
        stringmemory = username;
    }
    static public String getStringmemory(){
        return stringmemory;
    }
}
