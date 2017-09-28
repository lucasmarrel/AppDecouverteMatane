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

//        String DELETE = "delete from emplacement where 1 = 1";
//        db.execSQL(DELETE);
//
//        String INSERT_1 = "insert into emplacement(nom, latitude, longitude) VALUES(\"IGA\",48.852337, -67.512331)";
//        String INSERT_2 = "insert into emplacement(nom, latitude, longitude) VALUES(\"Cinema Gaiete 425\",48.845218, -67.535614)";
//        String INSERT_3 = "insert into emplacement(nom, latitude, longitude) VALUES(\"Walmart\",48.843711, -67.556267)";
//
//        db.execSQL(INSERT_1);
//        db.execSQL(INSERT_2);
//        db.execSQL(INSERT_3);

    }

}
