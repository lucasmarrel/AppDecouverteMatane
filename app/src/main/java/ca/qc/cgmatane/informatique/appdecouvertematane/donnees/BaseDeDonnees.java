package ca.qc.cgmatane.informatique.appdecouvertematane.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 1743002 on 2017-09-21.
 */

public class BaseDeDonnees extends SQLiteOpenHelper {

    private static BaseDeDonnees instance = null;

    public static BaseDeDonnees getInstance(Context contexte)
    {

        if(null == instance) instance = new BaseDeDonnees(contexte);
        return instance;
    }

    public static BaseDeDonnees getInstance()
    {
        return instance;
    }

    public BaseDeDonnees(Context contexte) {
        super(contexte, "appMatane", null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String TABLE_EMPLACEMENT = "create table emplacement(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, latitude REAL, longitude REAL, qrCode TEXT, valide INTEGER)";
        String TABLE_UTILISATEUR = "create table utilisateur(id INTEGER PRIMARY KEY AUTOINCREMENT, prenom TEXT, nom TEXT, nomUtilisateur TEXT, mail TEXT, motDePasse TEXT)";
        String TABLE_PHOTO = "create table photo(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT)";

        db.execSQL(TABLE_EMPLACEMENT);
        db.execSQL(TABLE_UTILISATEUR);
        db.execSQL(TABLE_PHOTO);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

        String DETRUIRE_TABLE_EMPLACEMENT = "drop table emplacement";
        db.execSQL(DETRUIRE_TABLE_EMPLACEMENT);

        String DETRUIRE_TABLE_UTILISATEUR = "drop table utilisateur";
        db.execSQL(DETRUIRE_TABLE_UTILISATEUR);

        String DETRUIRE_TABLE_PHOTO = "drop table photo";
        db.execSQL(DETRUIRE_TABLE_PHOTO);

        String TABLE_EMPLACEMENT = "create table emplacement(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, latitude REAL, longitude REAL, qrCode TEXT, valide INTEGER)";
        String TABLE_UTILISATEUR = "create table utilisateur(id INTEGER PRIMARY KEY AUTOINCREMENT, prenom TEXT, nom TEXT, nomUtilisateur TEXT, mail TEXT, motDePasse TEXT)";
        String TABLE_PHOTO = "create table photo(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT)";

        db.execSQL(TABLE_EMPLACEMENT);
        db.execSQL(TABLE_UTILISATEUR);
        db.execSQL(TABLE_PHOTO);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        try{
            String DETRUIRE_TABLE_EMPLACEMENT = "drop table emplacement";
            db.execSQL(DETRUIRE_TABLE_EMPLACEMENT);

            String TABLE_EMPLACEMENT = "create table emplacement(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, latitude REAL, longitude REAL, qrCode TEXT, valide INTEGER)";
            db.execSQL(TABLE_EMPLACEMENT);

            String DELETE = "DELETE FROM emplacement";
            String INSERT_1 = "insert into emplacement(nom, latitude, longitude, qrCode, valide) VALUES(\"IGA\", 48.852335, -67.512314, \"appmataneempiga\", 1)";
            String INSERT_2 = "insert into emplacement(nom, latitude, longitude, qrCode, valide) VALUES(\"Cinema Gaiete\", 48.845212, -67.535702, \"appmataneempcinemagaiete\", 0)";
            String INSERT_3 = "insert into emplacement(nom, latitude, longitude, qrCode, valide) VALUES(\"Walmart\", 48.843680, -67.556263, \"appmataneempwalmart\", 1)";
            String INSERT_4 = "insert into emplacement(nom, latitude, longitude, qrCode, valide) VALUES(\"Cégep De Matane\", 48.841380, -67.497777, \"appmataneempcegep\", 0)";
            String INSERT_5 = "insert into emplacement(nom, latitude, longitude, qrCode, valide) VALUES(\"Polyvalente De Matane\", 48.841243, -67.508232, \"appmataneemppolyvalente\", 0)";
            String INSERT_6 = "insert into emplacement(nom, latitude, longitude, qrCode, valide) VALUES(\"Dixie Lee Matane\", 48.843063, -67.510728, \"appmataneempdixielee\", 0)";

            db.execSQL(DELETE);
            db.execSQL(INSERT_1);
            db.execSQL(INSERT_2);
            db.execSQL(INSERT_3);
            db.execSQL(INSERT_4);
            db.execSQL(INSERT_5);
            db.execSQL(INSERT_6);
        }catch (Exception ex){
            Log.d("APPERROR",ex.getMessage());
        }

//        String DELETE = "delete from emplacement where 1 = 1";
//        db.execSQL(DELETE);



    }

}
