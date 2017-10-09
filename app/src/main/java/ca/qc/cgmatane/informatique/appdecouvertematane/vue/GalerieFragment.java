package ca.qc.cgmatane.informatique.appdecouvertematane.vue;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import java.io.File;
import java.util.ArrayList;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;
import ca.qc.cgmatane.informatique.appdecouvertematane.modele.ImageAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalerieFragment extends Fragment {

    protected ArrayList<File> listeImage;
    protected GridView gridView;
    protected ImageView imageView;
    protected EditText editText;

    public GalerieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_galerie, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String folderPath = VueSlideMenu.folderPath;
        listeImage = chargementImage(new File(folderPath));
        if (listeImage.isEmpty()){
            Toast.makeText(getActivity(),"Vous n'avez pas encore pris de photos",Toast.LENGTH_LONG).show();
        }

        gridView = (GridView) view.findViewById(R.id.gridView);
        ImageAdapter imageAdapter = new ImageAdapter(listeImage,getContext());
        gridView.setAdapter(imageAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),VueAffichagePhoto.class);
                intent.putExtra("pathPhoto",listeImage.get(i).getAbsolutePath());
                startActivityForResult(intent,1);
            }
        });


    }

    protected ArrayList<File> chargementImage(File file){

        ArrayList<File> arrayList = new ArrayList<>();
        File[] files = file.listFiles();

        for(int i=0; i<files.length;i++){
            if(files[i].getName().endsWith(".jpg")){
                arrayList.add(files[i]);
            }
        }
        return arrayList;
    }
}
