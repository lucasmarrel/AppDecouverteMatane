package ca.qc.cgmatane.informatique.appdecouvertematane.vue;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import ca.qc.cgmatane.informatique.appdecouvertematane.R;
import ca.qc.cgmatane.informatique.appdecouvertematane.donnees.EmplacementDAO;
import ca.qc.cgmatane.informatique.appdecouvertematane.modele.Emplacement;

public class VueAjouterEmplacements extends AppCompatActivity {

    protected EditText champNom;
    protected EditText champLatitude;
    protected EditText champLongitude;
    protected EditText champNomQrcode;

    protected Button boutonAjouter;
    protected Button boutonAnnuler;

    protected EmplacementDAO emplacementDAO;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_ajouter_emplacements);

        champNom = findViewById(R.id.text_nom_vue_ajouter_emplacement);
        champLatitude = findViewById(R.id.text_latitude_vue_ajouter_emplacement);
        champLongitude = findViewById(R.id.text_longitude_vue_ajouter_emplacement);
        champNomQrcode = findViewById(R.id.text_nom_qrcode_vue_ajouter_emplacement);

        boutonAjouter = findViewById(R.id.bouton_ajouter_vue_ajouter_emplacement);
        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ajoutEmplacement();
                retourListeEmplacements();
            }
        });

        boutonAnnuler = findViewById(R.id.bouton_annuler_vue_ajouter_emplacement);
        boutonAnnuler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retourListeEmplacements();
            }
        });

        emplacementDAO = EmplacementDAO.getInstance();

    }

    protected void ajoutEmplacement()
    {
        String nom = champNom.getText().toString();
        double latitude = Double.parseDouble(champLatitude.getText().toString());
        double longitude = Double.parseDouble(champLongitude.getText().toString());
        String nomQrcode = champNomQrcode.getText().toString();

        Emplacement emplacement = new Emplacement(nom, latitude, longitude, nomQrcode);
        emplacementDAO.ajouterEmplacement(emplacement);
    }

    protected void retourListeEmplacements()
    {
        this.finish();
    }
}
