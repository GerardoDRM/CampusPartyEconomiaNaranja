package com.campusparty.android.economianaranja;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class MapFragment extends Fragment implements OnMapReadyCallback {

    private GoogleMap mMap;


    public MapFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        MapsInitializer.initialize(getContext());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_map, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        MapView mapFragment =  (MapView) view.findViewById(R.id.map);
        mapFragment.onCreate(savedInstanceState);
        mapFragment.onResume();
        mapFragment.getMapAsync(this);

        // Inflate the layout for this fragment
        return view;
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng pos1 = new LatLng(19.3863928, -99.2255625);
        LatLng pos2 = new LatLng(19.379984, -99.225666);
        LatLng pos3 = new LatLng(19.382393, -99.218789);
        LatLng pos4 = new LatLng(19.378426, -99.213500);
        LatLng pos5 = new LatLng(19.372697, -99.215635);

        mMap.addMarker(new MarkerOptions().position(pos1).title("Incubadora").snippet("Direccion: Santa Fe 1221")
                .draggable(true));
        mMap.addMarker(new MarkerOptions().position(pos2).title("Aceleradora").snippet("Direccion: Santa Fe 1221")
                .draggable(true));
        mMap.addMarker(new MarkerOptions().position(pos3).title("Secretaria de ...").snippet("Direccion: Santa Fe 1221")
                .draggable(true));
        mMap.addMarker(new MarkerOptions().position(pos4).title("Fundacion de ...").snippet("Direccion: Santa Fe 1221")
                .draggable(true));
        mMap.addMarker(new MarkerOptions().position(pos5).title("Incubadora").snippet("Direccion: Santa Fe 1221")
                .draggable(true));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(pos1));

    }
}
