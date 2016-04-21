package ff.findyourfriend.activity;

/**
 * Created by lmartinr on 31/03/16.
 */

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.location.Location;
import android.location.LocationManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.Map;

import ff.findyourfriend.R;
import ff.findyourfriend.asyncTask.ReadCoordAsyncTask;
import ff.findyourfriend.interfaces.UpdaterListener;


public class MainActivity extends AppCompatActivity  implements OnMapReadyCallback, android.location.LocationListener{

    // Navigator drawer
    private DrawerLayout drawerLayout;

    // Map
    private GoogleMap googleMap;
    private UpdaterListener updaterListener;
    private LocationManager locationManager;
    public static double lat = 40.300280;

    // LocationManager
    private static final long MIN_TIME = 400;
    private static final float MIN_DISTANCE = 1000;

    // Change view button
    private Button viewModeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Login activity
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);

        // Navigator view
        setContentView(R.layout.activity_main);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setHomeAsUpIndicator(R.drawable.ic_menu);
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // Map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        updaterListener = new UpdaterListener() {
            @Override
            public void updateCoord(final double value) {
                lat = value;
                LatLng my_position = new LatLng(lat, -3.811728);
                Log.d("Updating Coord", String.valueOf(value));
                if (googleMap != null) {
                    googleMap.clear();
                    googleMap.addMarker(new MarkerOptions().position(my_position).title("Marker in my_position"));
                    if (ActivityCompat.checkSelfPermission(
                            getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                            ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        return;
                    }
                }
            }
        };

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, MIN_TIME, MIN_DISTANCE, this);

        ReadCoordAsyncTask CoordRefreshThraed = new ReadCoordAsyncTask(updaterListener);
        CoordRefreshThraed.execute();

        // Change view mode button
        viewModeBtn = (Button) findViewById(R.id.view_mode_btn);
        viewModeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChangeViewModeDialog();
            }
        });
    }

    private void showChangeViewModeDialog() {
        final String dialogTitle = getResources().getString(R.string.map_view_mode_title_text);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogTitle);

        CharSequence[] MAP_OPTIONS = {
                getResources().getString(R.string.mode_normal),
                getResources().getString(R.string.mode_satellite),
                getResources().getString(R.string.mode_terrain),
                getResources().getString(R.string.mode_hybrid)
        };

        int checkItem = googleMap.getMapType() - 1;
        builder.setSingleChoiceItems(
                MAP_OPTIONS,
                checkItem,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        Log.d(getClass().getName(), "item: " + item);
                        switch (item) {
                            case 0:
                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                                break;
                            case 1:
                                googleMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
                                break;
                            case 2:
                                googleMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
                                break;
                            case 3:
                                googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
                                break;
                            default:
                                googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
                        }
                        dialog.dismiss();
                    }
                }
        );
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.nav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {

                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // Marcar item presionado
                        menuItem.setChecked(true);
                        Intent intent = null;
                        switch (menuItem.getItemId()) {
                            case R.id.nav_map:
                                intent = new Intent(MainActivity.this, MainActivity.class);
                                startActivity(intent);
                                return true;
                            case R.id.nav_friend_list:
                                return true;
                            case R.id.nav_log_out:
                                intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                        }
                        return true;
                    }
                }
        );
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Add a marker in my_position and move the camera
        LatLng my_position = new LatLng(lat, -3.811728);
        this.googleMap.addMarker(new MarkerOptions().position(my_position).title("Marker in my_position"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(my_position));
    }

    @Override
    public void onLocationChanged(Location location) {
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 15);
        googleMap.animateCamera(cameraUpdate);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        locationManager.removeUpdates(this);
    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }
}
