package ca.qc.cgmatane.informatique.appdecouvertematane.modele;

import android.location.Location;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by 1732986 on 2017-09-21.
 */

public class Emplacement
{
    private int id;
    private String nom;
    private double latitude;
    private double longitude;
    private String qrCode;

    public Emplacement(int id, String nom, double latitude, double longitude, String qrCode)
    {
        this.id = id;
        this.nom = nom;
        this.latitude = latitude;
        this.longitude = longitude;
        this.qrCode = qrCode;
    }

    public Emplacement()
    {
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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public LatLng getLocation(){
        LatLng coordonnees = new LatLng(latitude,longitude);
        return  coordonnees;
    }

    @Override
    public String toString() {
        return "Emplacement{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", qrCode='" + qrCode + '\'' +
                '}';
    }
}

