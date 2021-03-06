package ca.qc.cgmatane.informatique.appdecouvertematane.donnees;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.appdecouvertematane.modele.Emplacement;

/**
 * Created by 1732986 on 2017-09-28.
 */

public class EmplacementDAO {
    private BaseDeDonnees baseDeDonnees = BaseDeDonnees.getInstance();
    private static EmplacementDAO instance = null;
    private List<Emplacement> listeEmplacements;
    private Emplacement emplacement;


    public static EmplacementDAO getInstance(){
        if (instance == null){
            instance= new EmplacementDAO();
        }
        return instance;
    }

    public EmplacementDAO(){
        super();

        listeEmplacements = new ArrayList<>();

    }

    public List<Emplacement> listerEmplacementsNonValide(){

        try {
            String LISTER_EMPLACEMENTS = "SELECT * FROM emplacement WHERE valide = 0";
            Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_EMPLACEMENTS,null);

            listeEmplacements.clear();
            int index_id = curseur.getColumnIndex("id");
            int index_nom = curseur.getColumnIndex("nom");
            int index_latitude = curseur.getColumnIndex("latitude");
            int index_longitude = curseur.getColumnIndex("longitude");
            int index_qrCode = curseur.getColumnIndex("qrCode");
            int index_valide = curseur.getColumnIndex("valide");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {
                emplacement = null;
                int id = curseur.getInt(index_id);
                String nom = curseur.getString(index_nom);
                double latitude = curseur.getDouble(index_latitude);
                double longitude = curseur.getDouble(index_longitude);
                String qrCode = curseur.getString(index_qrCode);
                int valide = curseur.getInt(index_valide);

                emplacement = new Emplacement(id, nom, latitude, longitude, qrCode, valide);

                listeEmplacements.add(emplacement);

            }
        }



        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return listeEmplacements;

    }

    public List<Emplacement> listerTousLesEmplacements(){

        try {
            String LISTER_EMPLACEMENTS = "SELECT * FROM emplacement ";
            Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_EMPLACEMENTS,null);

            listeEmplacements.clear();
            int index_id = curseur.getColumnIndex("id");
            int index_nom = curseur.getColumnIndex("nom");
            int index_latitude = curseur.getColumnIndex("latitude");
            int index_longitude = curseur.getColumnIndex("longitude");
            int index_qrCode = curseur.getColumnIndex("qrCode");
            int index_valide = curseur.getColumnIndex("valide");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {
                emplacement = null;
                int id = curseur.getInt(index_id);
                String nom = curseur.getString(index_nom);
                double latitude = curseur.getDouble(index_latitude);
                double longitude = curseur.getDouble(index_longitude);
                String qrCode = curseur.getString(index_qrCode);
                int valide = curseur.getInt(index_valide);

                emplacement = new Emplacement(id, nom, latitude, longitude, qrCode, valide);

                listeEmplacements.add(emplacement);

            }
        }



        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return listeEmplacements;

    }

    public List<Emplacement> listerEmplacementsValide(){

        try {
            String LISTER_EMPLACEMENTS = "SELECT * FROM emplacement WHERE valide = 1";
            Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_EMPLACEMENTS,null);

            listeEmplacements.clear();
            int index_id = curseur.getColumnIndex("id");
            int index_nom = curseur.getColumnIndex("nom");
            int index_latitude = curseur.getColumnIndex("latitude");
            int index_longitude = curseur.getColumnIndex("longitude");
            int index_qrCode = curseur.getColumnIndex("qrCode");
            int index_valide = curseur.getColumnIndex("valide");

            for (curseur.moveToFirst(); !curseur.isAfterLast(); curseur.moveToNext()) {

                emplacement = null;
                int id = curseur.getInt(index_id);
                String nom = curseur.getString(index_nom);
                double latitude = curseur.getDouble(index_latitude);
                double longitude = curseur.getDouble(index_longitude);
                String qrCode = curseur.getString(index_qrCode);
                int valide = curseur.getInt(index_valide);

                emplacement = new Emplacement(id, nom, latitude, longitude, qrCode, valide);
                listeEmplacements.add(emplacement);
            }
        }



        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return listeEmplacements;

    }

    public List<HashMap<String, String>> listerTousLesEmplacementsEnHashMap()
    {
        List<Emplacement> listeEmplacements = listerTousLesEmplacements();
        List<HashMap<String, String>> listeEmplacementsEnHashMap = new ArrayList<>();

        for (Emplacement emplacement: listeEmplacements)
        {
            listeEmplacementsEnHashMap.add(emplacement.exporterEnHashMap());
        }

        return listeEmplacementsEnHashMap;
    }



    public Emplacement trouverEmplacementQrCode(String qrCodeTrouve){

        try {
            emplacement=null;

            String LISTER_EMPLACEMENTS = "SELECT * FROM emplacement WHERE qrCode =\""+qrCodeTrouve+"\"";
            Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_EMPLACEMENTS,null);

            listeEmplacements.clear();
            int index_id = curseur.getColumnIndex("id");
            int index_nom = curseur.getColumnIndex("nom");
            int index_latitude = curseur.getColumnIndex("latitude");
            int index_longitude = curseur.getColumnIndex("longitude");
            int index_qrCode = curseur.getColumnIndex("qrCode");
            int index_valide = curseur.getColumnIndex("valide");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {

                int id = curseur.getInt(index_id);
                String nom = curseur.getString(index_nom);
                double latitude = curseur.getDouble(index_latitude);
                double longitude = curseur.getDouble(index_longitude);
                String qrCode = curseur.getString(index_qrCode);
                int valide = curseur.getInt(index_valide);

                emplacement = new Emplacement(id, nom, latitude, longitude, qrCode, valide);
                listeEmplacements.add(emplacement);

            }
        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return emplacement;

    }

    public Emplacement trouverEmplacementId(int idEmplacement){

        try {
            emplacement=null;

            String LISTER_EMPLACEMENTS = "SELECT * FROM emplacement WHERE id ="+idEmplacement;
            Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_EMPLACEMENTS,null);

            listeEmplacements.clear();
            int index_id = curseur.getColumnIndex("id");
            int index_nom = curseur.getColumnIndex("nom");
            int index_latitude = curseur.getColumnIndex("latitude");
            int index_longitude = curseur.getColumnIndex("longitude");
            int index_qrCode = curseur.getColumnIndex("qrCode");
            int index_valide = curseur.getColumnIndex("valide");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {

                int id = curseur.getInt(index_id);
                String nom = curseur.getString(index_nom);
                double latitude = curseur.getDouble(index_latitude);
                double longitude = curseur.getDouble(index_longitude);
                String qrCode = curseur.getString(index_qrCode);
                int valide = curseur.getInt(index_valide);

                emplacement = new Emplacement(id, nom, latitude, longitude, qrCode, valide);
                listeEmplacements.add(emplacement);

            }
        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return emplacement;

    }

    public boolean emplacementValide(int id){

        int valide =0;

        try {
            String EMPLACEMENT_VALIDE = "SELECT valide FROM emplacement WHERE id ="+id;
            Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(EMPLACEMENT_VALIDE,null);

            int index_valide = curseur.getColumnIndex("valide");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {

                valide = curseur.getInt(index_valide);

            }
        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return valide == 1;

    }

    public void ajouterEmplacement(Emplacement emplacement){

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nom", emplacement.getNom());
            contentValues.put("latitude", emplacement.getLatitude());
            contentValues.put("longitude", emplacement.getLongitude());
            contentValues.put("qrCode", emplacement.getQrCode());
            contentValues.put("valide", 0);
            baseDeDonnees.getWritableDatabase().insertOrThrow("emplacement","", contentValues);

        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

    }

    public void modifierEmplacement(Emplacement emplacement){

        try {
            int id = emplacement.getId();
            String nom = emplacement.getNom();
            double latitude = emplacement.getLatitude();
            double longitude = emplacement.getLongitude();
            String qrCode = emplacement.getQrCode();

            String MODIFIER_EMPLACEMENT = "UPDATE emplacement SET nom =\""+ nom +"\", latitude ="+ latitude+", longitude="+longitude+", qrCode=\""+ qrCode +"\", valide="+emplacement.getValide()+" WHERE id ="+id;
            baseDeDonnees.getWritableDatabase().execSQL(MODIFIER_EMPLACEMENT);

        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

    }

    public void validationEmplacement(int id){

        try {

            String MODIFIER_EMPLACEMENT = "UPDATE emplacement SET valide = 1 WHERE id ="+id;
            baseDeDonnees.getWritableDatabase().execSQL(MODIFIER_EMPLACEMENT);

        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

    }

    public void supprimerEmplacement(int id){

        try {
            baseDeDonnees.getWritableDatabase().delete("emplacement","id=" + id,null);
        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

    }
}
