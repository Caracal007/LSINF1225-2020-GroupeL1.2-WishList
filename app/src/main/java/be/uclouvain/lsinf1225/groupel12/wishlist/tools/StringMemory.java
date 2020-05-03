package be.uclouvain.lsinf1225.groupel12.wishlist.tools;

public class StringMemory {
    static public String stringmemory;
    static public String stringmemoryFriendName;

    static public void initStringMemory(String username){
        stringmemory = username;
    }
    static public void initStringMemoryFriendName(String username){
        stringmemoryFriendName = username;
    }
    static public String getStringMemory(){
        return stringmemory;
    }
    static public String getStringMemoryFriendName(){
        return stringmemoryFriendName;
    }
}
