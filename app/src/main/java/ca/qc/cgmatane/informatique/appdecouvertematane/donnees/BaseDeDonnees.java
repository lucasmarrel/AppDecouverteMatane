package ca.qc.cgmatane.informatique.appdecouvertematane.donnees;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

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

//        String TABLE_EMPLACEMENT = "create table emplacement(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, latitude REAL, longitude REAL)";
//        String TABLE_UTILISATEUR = "create table utilisateur(id INTEGER PRIMARY KEY AUTOINCREMENT, prenom TEXT, nom TEXT, nomUtilisateur TEXT, mail TEXT, motDePasse TEXT)";
//        String TABLE_PHOTO = "create table photo(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT)";
//        String TABLE_QRCODE = "create table qrCode(id TEXT PRIMARY KEY)";

//        db.execSQL(TABLE_EMPLACEMENT);
//        db.execSQL(TABLE_UTILISATEUR);
//        db.execSQL(TABLE_PHOTO);
//        db.execSQL(TABLE_QRCODE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {

//        String DETRUIRE_TABLE_EMPLACEMENT = "drop table evenement";
//        db.execSQL(DETRUIRE_TABLE_EMPLACEMENT);

//        String DETRUIRE_TABLE_UTILISATEUR = "drop table utilisateur";
//        db.execSQL(DETRUIRE_TABLE_UTILISATEUR);

//        String DETRUIRE_TABLE_PHOTO = "drop table photo";
//        db.execSQL(DETRUIRE_TABLE_PHOTO);

//        String DETRUIRE_TABLE_QRCODE = "drop table qrCode";
//        db.execSQL(DETRUIRE_TABLE_QRCODE);

//        String TABLE_EMPLACEMENT = "create table emplacement(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT, latitude REAL, longitude REAL)";
//        String TABLE_UTILISATEUR = "create table utilisateur(id INTEGER PRIMARY KEY AUTOINCREMENT, prenom TEXT, nom TEXT, nomUtilisateur TEXT, mail TEXT, motDePasse TEXT)";
//        String TABLE_PHOTO = "create table photo(id INTEGER PRIMARY KEY AUTOINCREMENT, nom TEXT)";
//        String TABLE_QrCode = "create table qrCode(id TEXT PRIMARY KEY)";

//        db.execSQL(TABLE_EMPLACEMENT);
//        db.execSQL(TABLE_UTILISATEUR);
//        db.execSQL(TABLE_PHOTO);
//        db.execSQL(TABLE_QrCode);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {

//        String DELETE = "delete from evenement where 1 = 1";
//        db.execSQL(DELETE);
//
//        String INSERT_1 = "insert into evenement(titre, lieu, date) VALUES(\"RDV Dentiste\",\"45 rue Gustave Eiffel\",\"2018-12-26 14:30:00\")";
//        String INSERT_2 = "insert into evenement(titre, lieu, date) VALUES(\"RDV Coiffeur\",\"44 avenue Lucas Marrel\",\"2016-09-07 20:45:00\")";
//        String INSERT_3 = "insert into evenement(titre, lieu, date, description) VALUES(\"Chercher enfants\",\"22 rue des Champs\",\"2013-05-05 13:58:00\",\"Penser a apporter le goûter\")";
//
//        db.execSQL(INSERT_1);
//        db.execSQL(INSERT_2);
//        db.execSQL(INSERT_3);

    }

}
