package com.mapstse.case13658323;

import android.content.res.Resources;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.m4b.maps.CameraUpdateFactory;
import com.google.android.m4b.maps.GoogleMap;
import com.google.android.m4b.maps.OnMapReadyCallback;
import com.google.android.m4b.maps.SupportMapFragment;
import com.google.android.m4b.maps.model.LatLng;
import com.google.android.m4b.maps.model.MarkerOptions;
import com.google.android.m4b.maps.model.MapStyleOptions;

import static android.util.Log.e;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final String TAG = MapsActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        mMap = googleMap;

        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.map_style_1));

            if (!success) {
                Log.e(TAG, "Style parsing failed.");
            } else {
                Log.i(TAG, "Style parsing OK");
            }
        } catch (Resources.NotFoundException e) {
            Log.e(TAG, "Can't find style. Error: ", e);
        }

        // Add a marker in Sydney and move the camera
        LatLng point = new LatLng(25.062762,121.614425);
        mMap.addMarker(new MarkerOptions().position(point).title("Test location"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(point, 19.0f));
    }
}
