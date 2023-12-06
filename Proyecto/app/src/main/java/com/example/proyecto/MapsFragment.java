package com.example.proyecto;

import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsFragment extends Fragment {

    //private GoogleMap mMap;
    //protected int minimunDistance = 30;
    //private final int PERMISSION_LOCATION = 999;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_maps, container, false);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {

                GoogleMap mMap = googleMap;

                LatLng sucursal1 = new LatLng(20.678368705385004, -103.36794769765814);
                LatLng sucursal2 = new LatLng(20.673026983686842, -103.35913682611769);
                LatLng sucursal3 = new LatLng(20.664651313349893, -103.3586786607976);
                LatLng sucursal4 = new LatLng(20.669894405152426, -103.36967462848007);
                LatLng sucursal5 = new LatLng(20.648743054602125, -103.36112871530108);

                MarkerOptions markerOptions = new MarkerOptions();
                markerOptions.position(sucursal1);
                markerOptions.title( "Sucursal 1" );
                markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconubi)).anchor(0.0f,0.0f);

                MarkerOptions markerOptions2 = new MarkerOptions();
                markerOptions2.position(sucursal2);
                markerOptions2.title( "Sucursal 2" );
                markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconubi)).anchor(0.0f,0.0f);

                MarkerOptions markerOptions3 = new MarkerOptions();
                markerOptions3.position(sucursal3);
                markerOptions3.title( "Sucursal 3" );
                markerOptions3.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconubi)).anchor(0.0f,0.0f);

                MarkerOptions markerOptions4 = new MarkerOptions();
                markerOptions4.position(sucursal4);
                markerOptions4.title( "Sucursal 4" );
                markerOptions4.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconubi)).anchor(0.0f,0.0f);

                MarkerOptions markerOptions5 = new MarkerOptions();
                markerOptions5.position(sucursal5);
                markerOptions5.title( "Sucursal 5" );
                markerOptions5.icon(BitmapDescriptorFactory.fromResource(R.drawable.iconubi)).anchor(0.0f,0.0f);

                mMap.clear();
                mMap.addMarker(markerOptions);
                mMap.addMarker(markerOptions2);
                mMap.addMarker(markerOptions3);
                mMap.addMarker(markerOptions4);
                mMap.addMarker(markerOptions5);
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sucursal3,14));

            }
        });

        return view;
    }

}