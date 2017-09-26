package ca.qc.cgmatane.informatique.appdecouvertematane.modele;

/**
 * Created by 1732986 on 2017-09-26.
 */

public class Photo {
    private int id;
    private String nom;

    public Photo() {
    }

    public Photo(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                '}';
    }
}
