package ff.findyourfriend.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import ff.findyourfriend.R;
import ff.findyourfriend.asyncTask.ReadCoordAsyncTask;
import ff.findyourfriend.interfaces.UpdaterListener;

/**
 * Created by laura on 09/04/2016.
 */
public class MapActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap googleMap;
    UpdaterListener updaterListener;
    public static double lat = 40.300280;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        updaterListener = new UpdaterListener() {
            @Override
            public void updateCoord(final double value) {
                lat = value;
                LatLng my_position = new LatLng(lat, -3.811728);
                Log.d("Updating Coord", String.valueOf(value));
                if(googleMap != null) {
                    googleMap.clear();
                    googleMap.addMarker(new MarkerOptions().position(my_position).title("Marker in my_position"));
                    googleMap.moveCamera(CameraUpdateFactory.newLatLng(my_position));
                }
            }
        };
        ReadCoordAsyncTask CoordRefreshThraed = new ReadCoordAsyncTask(updaterListener);
        CoordRefreshThraed.execute();
    }
    
    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        // Add a marker in my_position and move the camera
        LatLng my_position = new LatLng(lat, -3.811728);
        this.googleMap.addMarker(new MarkerOptions().position(my_position).title("Marker in my_position"));
        this.googleMap.moveCamera(CameraUpdateFactory.newLatLng(my_position));
    }

}
