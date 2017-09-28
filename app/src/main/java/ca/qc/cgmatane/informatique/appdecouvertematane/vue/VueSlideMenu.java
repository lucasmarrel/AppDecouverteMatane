package ca.qc.cgmatane.informatique.appdecouvertematane.vue;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.File;

import ca.qc.cgmatane.informatique.appdecouvertematane.R;
import ca.qc.cgmatane.informatique.appdecouvertematane.donnees.BaseDeDonnees;
import ca.qc.cgmatane.informatique.appdecouvertematane.donnees.EmplacementDAO;

public class VueSlideMenu extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, ActivityCompat.OnRequestPermissionsResultCallback {

    private final static int MY_PERMISSION_FINE_LOCATION = 101;
    private final static int ACTION_PHOTO_CAMERA = 1;
    private EmplacementDAO emplacementDAO;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.vue_slide_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        BaseDeDonnees.getInstance(getApplicationContext());


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            navigationView.getMenu().performIdentifierAction(R.id.nav_googlemap, 0);
            navigationView.setCheckedItem(R.id.nav_googlemap);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.vue_slide_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_googlemap) {

            NavigationFragment navigationFragment = new NavigationFragment();

            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, navigationFragment).commit();

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, MY_PERMISSION_FINE_LOCATION);
            }

        } else if (id == R.id.nav_scan) {
            ScanFragment scanFragment = new ScanFragment();
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.mainLayout, scanFragment).commit();

        } else if (id == R.id.nav_pictures) {

        } else if (id == R.id.nav_parametres) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSION_FINE_LOCATION:
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(getApplicationContext(),"L'application a besoin de la permission de localisation pour fonctionner",Toast.LENGTH_LONG).show();
                        finish();
                    }
                }
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == IntentIntegrator.REQUEST_CODE) {

            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                if (result.getContents() == null) {
                    Toast.makeText(this, "Vous avez annulé le scan", Toast.LENGTH_LONG).show();
                } else {
                    String resultat = result.getContents().toString();
                    Toast.makeText(this, resultat, Toast.LENGTH_LONG).show();
                    startCamera(resultat);
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }
        else if (requestCode == ACTION_PHOTO_CAMERA) {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (resultCode != RESULT_OK ) {
                Toast.makeText(this, "Vous devez prendre une photo pour valider l'emplacement", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Photo enregistrée", Toast.LENGTH_LONG).show();
            }
        }


    }

    protected void startCamera(String fileName) {
        String folderPath = Environment.getExternalStorageDirectory() + "/GallerieMatane";
        File folder = new File(folderPath);
        if (!folder.exists()) {
            File wallpaperDirectory = new File(folderPath);
            wallpaperDirectory.mkdirs();
        }

        File photo = new File(folderPath, fileName +".jpg" );

        if (photo != null) {

            Uri relativePath = Uri.fromFile(photo);
            Intent intent = new Intent (MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, relativePath);
            startActivityForResult(intent, ACTION_PHOTO_CAMERA);

        }
    }
}
