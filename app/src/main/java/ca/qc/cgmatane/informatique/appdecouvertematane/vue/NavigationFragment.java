package ca.qc.cgmatane.informatique.appdecouvertematane.vue;

import android.Manifest;
import android.content.Context;
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
import java.util.List;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;

import static android.content.Context.LOCATION_SERVICE;

/**
 * A simple {@link Fragment} subclass.
 */
public class NavigationFragment extends Fragment implements OnMapReadyCallback{

    private GoogleMap mMap;

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

        if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {

            mMap.setMyLocationEnabled(true);

            Location location = getLastKnownLocation();

            if (location != null) {

                double latitude = location.getLatitude();
                double longitude = location.getLongitude();

                LatLng localisation = new LatLng(latitude, longitude);
                LatLng iga = new LatLng(48.852337, -67.512331);
                mMap.addMarker(new MarkerOptions().position(iga).title("IGA"));
                LatLng cinema = new LatLng(48.845218, -67.535614);
                mMap.addMarker(new MarkerOptions().position(cinema).title("Cinema Gaiete"));
                LatLng walmart = new LatLng(48.843711, -67.556267);
                mMap.addMarker(new MarkerOptions().position(walmart).title("Walmart"));

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
                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, new android.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
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
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new android.location.LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
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
}
