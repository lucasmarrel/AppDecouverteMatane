package ca.qc.cgmatane.informatique.appdecouvertematane.vue;

import android.Manifest;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationBuilderWithBuilderAccessor;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.vision.barcode.Barcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;
import ca.qc.cgmatane.informatique.appdecouvertematane.donnees.EmplacementDAO;
import ca.qc.cgmatane.informatique.appdecouvertematane.modele.Emplacement;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;
    private EmplacementDAO emplacementDAO;
    private Emplacement emplacement;

    public NavigationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_navigation, container, false);

        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        emplacementDAO  = EmplacementDAO.getInstance();
        emplacement = new Emplacement();

        positionnementEmplacements();

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

            Location location = getLastKnownLocation();

            if (location != null) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                LatLng localisation = new LatLng(latitude, longitude);

                Location test = new Location("test");
                test.setLatitude(48.845218);
                test.setLongitude(-67.556267);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(new LatLng(localisation.latitude, localisation.longitude))      // Sets the center of the map to myLocation user
                        .zoom(15)                   // Sets the zoom
                        .bearing(0)                // Sets the orientation of the camera to east
                        .tilt(0)                   // Sets the tilt of the camera to 30 degrees
                        .build();                   // Creates a CameraPosition from the builder
                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
            } else {
                Toast.makeText(getActivity(), "Veuillez activer la localisation", Toast.LENGTH_LONG).show();
            }

        }

    }

    private Location getLastKnownLocation() {
        LocationManager locationManager;
        Location bestLocation = null;


        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED ) {
            locationManager = (LocationManager) getActivity().getSystemService(LOCATION_SERVICE);
            List<String> providers =  locationManager.getProviders(true);

            for (String provider : providers) {

                Location location =  locationManager.getLastKnownLocation(provider);
                if (location == null) {
                    continue;
                }
                if (bestLocation == null || location.getAccuracy() < bestLocation.getAccuracy()) {
                    // Found best last known location: %s", l);
                    bestLocation = location;
                }
            }

            if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 100, new android.location.LocationListener() {

                    private  EmplacementDAO emplacementDAO  = EmplacementDAO.getInstance();
                    private  Location nearestLocation = new Location("location");

                    @Override
                    public void onLocationChanged(Location location) {

                        List<Emplacement> listeEmplacement = emplacementDAO.listerEmplacements();

                        for (Emplacement emplacement : listeEmplacement){
                            nearestLocation.setLatitude(emplacement.getLatitude());
                            nearestLocation.setLongitude(emplacement.getLongitude());

                            int distance = (int) location.distanceTo(nearestLocation);
                            if (distance<400){
                                afficherAlarme(emplacement,distance);
                            }

                        }

//                        double latitute = location.getLatitude();
//                        double longitude = location.getLongitude();
//                        LatLng latLng = new LatLng(latitute,longitude);
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {
                        Toast.makeText(getActivity(), "Veuillez activer la localisation", Toast.LENGTH_LONG).show();

                    }
                });
            } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 100, new android.location.LocationListener() {

                    private  Location nearestLocation = new Location("location");
                    private  EmplacementDAO emplacementDAO  = EmplacementDAO.getInstance();

                    @Override
                    public void onLocationChanged(Location location) {

                        List<Emplacement> listeEmplacement = emplacementDAO.listerEmplacements();

                        for (Emplacement emplacement : listeEmplacement){
                            nearestLocation.setLatitude(emplacement.getLatitude());
                            nearestLocation.setLongitude(emplacement.getLongitude());

                            int distance = (int) location.distanceTo(nearestLocation);
                            if (distance<400){
                                afficherAlarme(emplacement,distance);
                            }

                        }

//                        double latitute = location.getLatitude();
//                        double longitude = location.getLongitude();
//                        LatLng latLng = new LatLng(latitute,longitude);
//                        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    }

                    @Override
                    public void onStatusChanged(String s, int i, Bundle bundle) {

                    }

                    @Override
                    public void onProviderEnabled(String s) {

                    }

                    @Override
                    public void onProviderDisabled(String s) {
                        Toast.makeText(getActivity(), "Veuillez activer la localisation", Toast.LENGTH_LONG).show();
                    }
                });
            }
        }
        return bestLocation;
    }

    protected void positionnementEmplacements() {

        List<Emplacement> listeEmplacement = emplacementDAO.listerEmplacements();

        for(Emplacement emplacement: listeEmplacement) {
            mMap.addMarker(new MarkerOptions().position(emplacement.getLocation()).title(emplacement.getNom()));

        }
    }

    protected void afficherAlarme(Emplacement emplacement, int distance){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity(), "AppMatane");
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("App Decouverte Matane");
        builder.setContentText("Vous êtes à "+ distance + "m" +" de " + emplacement.getNom());
        builder.setVibrate(new long[]{200,200,80,80,80,80,80,80});
        Intent intent = new Intent(getActivity(),VueSlideMenu.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(getActivity());
        stackBuilder.addParentStack(VueSlideMenu.class);
        stackBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager notificationManager = (NotificationManager)getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(emplacement.getId(),builder.build());
    }


}
