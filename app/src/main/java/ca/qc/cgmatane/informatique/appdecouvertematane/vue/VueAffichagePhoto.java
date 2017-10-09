package ca.qc.cgmatane.informatique.appdecouvertematane.vue;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.File;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;

public class VueAffichagePhoto extends AppCompatActivity {

    protected TextView titrePhoto;
    protected ImageView photo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_affichage_photo);

        titrePhoto = findViewById(R.id.titre_image_vue_affichage_photo);
        photo = findViewById(R.id.image_vue_affichage_photo);

        Intent intent = getIntent();
        String pathPhoto = intent.getStringExtra("pathPhoto");

        File image = new File(pathPhoto);

        Picasso.with(getApplicationContext()).load(image).resize(1100,1100).centerInside().into(photo);

        titrePhoto.setText(getName(image.getName()));

    }

    protected String getName(String titre){
        String nom = new String("");
        try {
            String[] separation = titre.split("\\.");

            nom = separation[0];

        }catch (Exception ex){
            Log.d("APPERROR", ex.getMessage());
        }
        return nom;
    }

}
