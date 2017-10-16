package ca.qc.cgmatane.informatique.appdecouvertematane.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;
import ca.qc.cgmatane.informatique.appdecouvertematane.donnees.EmplacementDAO;
import ca.qc.cgmatane.informatique.appdecouvertematane.modele.Emplacement;

public class VueModifierEmplacements extends AppCompatActivity {

    protected EditText champNom;
    protected EditText champLatitude;
    protected EditText champLongitude;
    protected EditText champNomQrcode;

    protected Button boutonModifier;
    protected Button boutonAnnuler;
    protected Button boutonSupprimer;

    protected EmplacementDAO emplacementDAO;

    protected Emplacement emplacement;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_modifier_emplacements);

        try {
            emplacementDAO = EmplacementDAO.getInstance();

            Intent intent = getIntent();
            int id  = intent.getIntExtra("id",1);

            emplacement = emplacementDAO.trouverEmplacementId(id);

            champNom = findViewById(R.id.text_nom_vue_modifier_emplacement);
            champNom.setText(emplacement.getNom());
            champLatitude = findViewById(R.id.text_latitude_vue_modifier_emplacement);
            champLatitude.setText(String.valueOf(emplacement.getLatitude()));
            champLongitude = findViewById(R.id.text_longitude_vue_modifier_emplacement);
            champLongitude.setText(String.valueOf(emplacement.getLongitude()));
            champNomQrcode = findViewById(R.id.text_nom_qrcode_vue_modifier_emplacement);
            champNomQrcode.setText(emplacement.getQrCode());

            boutonModifier = findViewById(R.id.bouton_modifier_vue_modifier_emplacement);
            boutonModifier.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    modifierEmplacement();
                    retourListeEmplacements();
                }
            });

            boutonSupprimer = findViewById(R.id.bouton_supprimer_vue_modifier_emplacement);
            boutonSupprimer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    supprimerEmplacement(emplacement.getId());
                    retourListeEmplacements();
                }
            });
            boutonAnnuler = findViewById(R.id.bouton_annuler_vue_modifier_emplacement);
            boutonAnnuler.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    retourListeEmplacements();
                }
            });
        }catch (Exception ex){
            Log.d("APPERROR",ex.getMessage());
        }
    }

    protected void supprimerEmplacement(int id)
    {
        emplacementDAO.supprimerEmplacement(id);
    }

    protected void modifierEmplacement()
    {
        String nom = champNom.getText().toString();
        emplacement.setNom(nom);
        double latitude = Double.parseDouble(champLatitude.getText().toString());
        emplacement.setLatitude(latitude);
        double longitude = Double.parseDouble(champLongitude.getText().toString());
        emplacement.setLongitude(longitude);
        String nomQrcode = champNomQrcode.getText().toString();
        emplacement.setQrCode(nomQrcode);

        emplacementDAO.modifierEmplacement(emplacement);

    }

    protected void retourListeEmplacements()
    {
        this.finish();
    }
}
