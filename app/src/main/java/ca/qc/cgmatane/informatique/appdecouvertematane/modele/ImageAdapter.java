package ca.qc.cgmatane.informatique.appdecouvertematane.modele;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;

/**
 * Created by 1743002 on 2017-09-28.
 */

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<File> liste;
    private LayoutInflater layoutInflater;

    public ImageAdapter (ArrayList<File> liste, Context context){
        this.liste = liste;
        this.context = context;
    }

    @Override
    public int getCount() {
        return liste.size();
    }

    @Override
    public Object getItem(int i) {
        return liste.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = layoutInflater.inflate(R.layout.single_grid,viewGroup,false);
        ImageView imageView =  (ImageView) view.findViewById(R.id.imageView3);
        Picasso.with(context).load(liste.get(i).getAbsoluteFile()).resize(600,600).centerInside().into(imageView);

        return  view;
    }
}
