package ca.qc.cgmatane.informatique.appdecouvertematane.modele;

/**
 * Created by 1732986 on 2017-09-26.
 */

public class Utilisateur {

    private int id;
    private String prenom;
    private String nom;
    private String nomUtilisateur;
    private String mail;
    private String motDePasse;

    public Utilisateur() {
    }

    public Utilisateur(int id, String prenom, String nom, String nomUtilisateur, String mail, String motDePasse) {
        this.id = id;
        this.prenom = prenom;
        this.nom = nom;
        this.nomUtilisateur = nomUtilisateur;
        this.mail = mail;
        this.motDePasse = motDePasse;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getNomUtilisateur() {
        return nomUtilisateur;
    }

    public void setNomUtilisateur(String nomUtilisateur) {
        this.nomUtilisateur = nomUtilisateur;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMotDePasse() {
        return motDePasse;
    }

    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "id=" + id +
                ", prenom='" + prenom + '\'' +
                ", nom='" + nom + '\'' +
                ", nomUtilisateur='" + nomUtilisateur + '\'' +
                ", mail='" + mail + '\'' +
                ", motDePasse='" + motDePasse + '\'' +
                '}';
    }
}
