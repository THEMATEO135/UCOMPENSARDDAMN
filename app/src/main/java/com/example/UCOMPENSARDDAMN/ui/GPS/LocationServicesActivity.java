package com.example.UCOMPENSARDDAMN.ui.GPS;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.UCOMPENSARDDAMN.R;

import java.util.ArrayList;

public class LocationServicesActivity extends AppCompatActivity {

    MyLocationPlaceMap myLocationPlaceMap;
    ArrayList<MyLocationPlace> myLocations = new ArrayList<>();
    MyLocationPlace myLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    /*    setContentView(R.layout.activity_maps);*/

        myLocationPlaceMap = new MyLocationPlaceMap(getApplicationContext(), LocationServicesActivity.this);
        myLocationPlaceMap.requestPermissions();
        myLocationPlaceMap.getLatLngAddress(myLocations);

    }



    public void showCurrentLocation() {
        myLocationPlaceMap.getLatLngAddress(myLocations);
        TextView tvlat = findViewById(R.id.textLatitude);
        TextView tvlng = findViewById(R.id.textLongitude);
        TextView tvaddr = findViewById(R.id.textAddress);

        if (myLocations.size() > 0) {

            myLocation = myLocations.get(0);
            myLocations.clear();
            tvlat.setText("Latitude: " + myLocation.getLatitude());
            tvlng.setText("Longitude: " + myLocation.getLongitude());
            tvaddr.setText("Address: " + myLocation.getAddress());
        }
    }

}