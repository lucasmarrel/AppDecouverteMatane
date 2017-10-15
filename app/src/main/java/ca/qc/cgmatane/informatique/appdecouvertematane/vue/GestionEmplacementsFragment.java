package ca.qc.cgmatane.informatique.appdecouvertematane.vue;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.HashMap;
import java.util.List;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;
import ca.qc.cgmatane.informatique.appdecouvertematane.donnees.EmplacementDAO;

/**
 * A simple {@link Fragment} subclass.
 */
public class GestionEmplacementsFragment extends Fragment {

    protected EmplacementDAO emplacementDAO;
    protected ListView vueListeEmplacements;
    protected List<HashMap<String, String>> listeEmplacements;
    protected FloatingActionButton boutonAjouter;

    protected final static int ACTIVITE_AJOUTER_EMPLACEMENT = 1;
    protected final static int ACTIVITE_MODIFIER_EMPLACEMENT = 2;
    public GestionEmplacementsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gestion_emplacements, container, false);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        vueListeEmplacements = view.findViewById(R.id.liste_emplacements_gestion_emplacements_fragment);
        boutonAjouter = view.findViewById(R.id.bouton_ajouter_fragment_gestion_emplacements);
        emplacementDAO = EmplacementDAO.getInstance();

        afficherTousLesEmplacements();

        vueListeEmplacements.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try{
                    ListView vueListeEmplacements = (ListView) view.getParent();
                    HashMap<String, String> emplacement = (HashMap<String, String>)vueListeEmplacements.getItemAtPosition(position);
                    Intent intentionNaviguerVueModifierEmplacement = new Intent(getActivity(),VueModifierEmplacements.class);
                    intentionNaviguerVueModifierEmplacement.putExtra("id",Integer.parseInt(emplacement.get("id")));

                    startActivityForResult(intentionNaviguerVueModifierEmplacement, ACTIVITE_MODIFIER_EMPLACEMENT);
                }catch (Exception ex){
                    Log.d("APPERROR",ex.getMessage());
                }

            }
        });


        boutonAjouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentionNaviguerVueAjouterEmplacement = new Intent(getActivity(), VueAjouterEmplacements.class);
                startActivityForResult(intentionNaviguerVueAjouterEmplacement, ACTIVITE_AJOUTER_EMPLACEMENT);
            }
        });
    }



    protected void afficherTousLesEmplacements()
    {
        listeEmplacements = emplacementDAO.listerTousLesEmplacementsEnHashMap();

        SimpleAdapter adapterListeEmplacements = new SimpleAdapter(
                getContext(),
                listeEmplacements,
                android.R.layout.two_line_list_item,
                new String[] {"nom" , "coordonnees"},
                new int[] {android.R.id.text1, android.R.id.text2}
        );

        vueListeEmplacements.setAdapter(adapterListeEmplacements);
    }

}
