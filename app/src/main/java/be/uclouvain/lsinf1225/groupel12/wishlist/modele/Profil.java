package be.uclouvain.lsinf1225.groupel12.wishlist.modele;

public class Profil {
    private String username;
    private String password;
    private String first_name;
    private String last_name;
    private String email;

    public Profil(String username, String password, String first_name, String last_name, String email){
        this.username = username;
        this.password = password;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public String getPseudo() {
        return username;
    }

    public String getMdp() {
        return password;
    }

    public String getPrénom() {
        return first_name;
    }

    public String getNom() {
        return last_name;
    }

    public String getMail() {
        return email;
    }
}
