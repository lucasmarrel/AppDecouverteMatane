package ca.qc.cgmatane.informatique.appdecouvertematane.vue;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
        emplacementDAO = EmplacementDAO.getInstance();

        afficherTousLesEmplacements();
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
