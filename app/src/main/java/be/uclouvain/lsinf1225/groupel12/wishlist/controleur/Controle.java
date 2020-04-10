package be.uclouvain.lsinf1225.groupel12.wishlist.controleur;

import be.uclouvain.lsinf1225.groupel12.wishlist.modele.Profil;


public final class Controle {
    private static Controle instance = null;
    private Profil profil;

    private Controle(){
        super();
    }

    /**
     *
     * @return
     */
    public static final  Controle getInstance(){
        if(Controle.instance == null){
            Controle.instance = new Controle();
        }
        return Controle.instance;
    }

    /**
     * Création profil
     * @param username
     * @param password
     * @param first_name
     * @param last_name
     * @param email
     */
    public void createProfil(String username, String password, String first_name, String last_name, String email){
        profil = new Profil(username, password, first_name, last_name, email);
    }

}
