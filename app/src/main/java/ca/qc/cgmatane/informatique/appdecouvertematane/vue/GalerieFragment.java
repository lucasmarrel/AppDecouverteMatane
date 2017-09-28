package ca.qc.cgmatane.informatique.appdecouvertematane.vue;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalerieFragment extends Fragment {


    public GalerieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_galerie, container, false);
    }

}
