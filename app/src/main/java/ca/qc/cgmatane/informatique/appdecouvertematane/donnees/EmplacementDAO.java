package ca.qc.cgmatane.informatique.appdecouvertematane.donnees;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.sql.PreparedStatement;
import java.util.ArrayList;
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

    public List<Emplacement> listerEmplacements(){

        try {
            String LISTER_EMPLACEMENTS = "SELECT * FROM emplacement";
            Cursor curseur = baseDeDonnees.getReadableDatabase().rawQuery(LISTER_EMPLACEMENTS,null);

            listeEmplacements.clear();
            int index_id = curseur.getColumnIndex("id");
            int index_nom = curseur.getColumnIndex("nom");
            int index_latitude = curseur.getColumnIndex("latitude");
            int index_longitude = curseur.getColumnIndex("longitude");
            int index_qrCode = curseur.getColumnIndex("qrCode");

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {

                int id = curseur.getInt(index_id);
                String nom = curseur.getString(index_nom);
                double latitude = curseur.getDouble(index_latitude);
                double longitude = curseur.getDouble(index_longitude);
                String qrCode = curseur.getString(index_qrCode);

                emplacement = new Emplacement(id, nom, latitude, longitude, qrCode);
                listeEmplacements.add(emplacement);

            }
        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return listeEmplacements;

    }

    public Emplacement trouverEmplacements(String qrCodeTrouve){

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

            for (curseur.moveToFirst(); !curseur.isAfterLast();curseur.moveToNext()) {

                int id = curseur.getInt(index_id);
                String nom = curseur.getString(index_nom);
                double latitude = curseur.getDouble(index_latitude);
                double longitude = curseur.getDouble(index_longitude);
                String qrCode = curseur.getString(index_qrCode);

                emplacement = new Emplacement(id, nom, latitude, longitude, qrCode);
                listeEmplacements.add(emplacement);

            }
        }

        catch (Exception ex) {
            Log.d("APPERROR", ex.getMessage());
        }

        return emplacement;

    }

    public void ajouterEmplacement(Emplacement emplacement){

        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("nom", emplacement.getNom());
            contentValues.put("latitude", emplacement.getLatitude());
            contentValues.put("longitude", emplacement.getLongitude());
            contentValues.put("qrCode", emplacement.getQrCode());
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

            String MODIFIER_EMPLACEMENT = "UPDATE emplacement SET nom =\""+ nom +"\", latitude ="+ latitude+", longitude="+longitude+", qrCode=\""+ qrCode +"\" WHERE id ="+id;
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
