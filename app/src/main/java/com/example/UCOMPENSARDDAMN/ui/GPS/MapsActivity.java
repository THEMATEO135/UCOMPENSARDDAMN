package com.example.UCOMPENSARDDAMN.ui.GPS;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.UCOMPENSARDDAMN.R;
import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


    public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {

        MyLocationPlaceMap myLocationplaces;

        GoogleMap mMap;

  //  public ActivityMapsBinding binding;

        MyLocationPlaceMap myLocationPlaceMap;
        Double lati;
        Double lati1;
        Double longi;
        Double longi1;
        String address;
        //    String marker;
        ArrayList<MyLocationPlace> myLocations = new ArrayList<>();
        MyLocationPlace myLocation;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

//        binding = ActivityMapsBinding.inflate(getLayoutInflater());
            setContentView(R.layout.activity_maps);
            try {
                Bundle bundle = getIntent().getExtras();

                double latitudFija = 2.9081552;
                double longitudFija = -75.2719591;
                //lati = bundle.getDouble("latitud");
                //longi = bundle.getDouble("longitud");
               // address = bundle.getString("direccion");
                lati = bundle.getDouble("latitud");
                longi = bundle.getDouble("longitud");

                lati1 = latitudFija;
                longi1 = longitudFija;
                address = "CL 21S 3 8";
                TextView tvlat = findViewById(R.id.textLatitude);
                TextView tvlng = findViewById(R.id.textLongitude);
                TextView tvaddr = findViewById(R.id.textAddress);

//            TextView tvaddr = findViewById(R.id.textAddress);
                tvlat.setText("Latitude: " + lati);
                tvlng.setText("Longitude: " + longi);
                tvaddr.setText("Address: " + address);
//            tvaddr.setText("Address: " + address);
//            final double LATI1= lati;
//            //            public enum LATI1 = lati;
//            Toast.makeText((Context) MapsActivity.this, (int) LATI1, Toast.LENGTH_SHORT).show();*/
            }

            catch (Exception e){
                Toast.makeText(this, "catch error", Toast.LENGTH_SHORT).show();
            }
//
            try {

            // Obtain the SupportMapFragment and get notified when the map is ready to be used.
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
            myLocationplaces = new MyLocationPlaceMap(getApplicationContext(), com.example.UCOMPENSARDDAMN.ui.GPS.MapsActivity.this);
            }
            catch (Exception e){
                Toast.makeText(this, "catch error", Toast.LENGTH_SHORT).show();
            }


            Button button1 = findViewById(R.id.buttonNearbyPlaces);
            button1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    myLocationplaces.getNearbyPlaces(mMap,"AIzaSyCysV_Szl2y0jl5IXmOnlVnlzZ5n5EaEf0");
                }
            });

            Button button2 = findViewById(R.id.buttonShowStreetView);
            button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    openStreet();
                }
            });


//        LatLng latLng = new LatLng(MainActivity.class.getDeclaredMethod(void buttonMyLocation1()))


        }

        private void openStreet() {
            abrirGoogleMaps();
            setContentView(R.layout.activity_street_view);
            SupportStreetViewPanoramaFragment streetViewPanoramaFragment =
                    (SupportStreetViewPanoramaFragment)
                            getSupportFragmentManager().findFragmentById(R.id.streetView);
            streetViewPanoramaFragment.getStreetViewPanoramaAsync(
                    new OnStreetViewPanoramaReadyCallback() {
                        @Override
                        public void onStreetViewPanoramaReady(StreetViewPanorama panorama) {

                            TextView tvlat = findViewById(R.id.textLatitude);
                            TextView tvlng = findViewById(R.id.textLongitude);
                            TextView tvaddr = findViewById(R.id.textAddress);
                            tvlat.setText("Latitud: " + lati);
                            tvlng.setText("Longitud: " + longi);
                            tvaddr.setText("Direccion: " + address);

                            LatLng uc = new LatLng(lati, longi);
                            panorama.setPosition(uc);
                            Button button = findViewById(R.id.buttonShowMap);
                            button.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {

                                    openMap();


                                }
                            });
                        }
                    });
        }

        private void abrirGoogleMaps() {
            // Coordenadas fijas del enlace proporcionado
            double latitudFija = 2.9081552;
            double longitudFija = -75.2719591;

            // Construir la URL de Google Maps en modo Street View con las coordenadas fijas
            String googleMapsUrl = "http://maps.google.com/maps?q=loc:" + latitudFija + "," + longitudFija + "&layer=c&cbll=" + latitudFija + "," + longitudFija;

            // Crear un Intent para abrir Google Maps con las coordenadas fijas en modo Street View
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(googleMapsUrl));
            intent.setPackage("com.google.android.apps.maps");

            // Verificar si hay una aplicación que puede manejar este Intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                // Abrir la aplicación de Google Maps
                startActivity(intent);
            } else {
                // Manejar la situación donde no hay una aplicación para manejar el Intent
                Toast.makeText(this, "La aplicación de Google Maps no está instalada.", Toast.LENGTH_SHORT).show();
            }
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

            // Add a marker in Sydney and move the camera
            LatLng sydney = new LatLng(lati,longi);
            mMap.addMarker(new MarkerOptions().position(sydney).title(address));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

            mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                //            @Override
                public boolean onMarkerClick(Marker marker) {
                    // on marker click we are getting the title of our marker
                    // which is clicked and displaying it in a toast message.
//
                    String markerName = marker.getTitle();
                    lati = marker.getPosition().latitude;
                    longi = marker.getPosition().longitude;

//                Toast.makeText(MapsActivity.this, "Clicked location is " + LATI1, Toast.LENGTH_SHORT).show();
//                Toast.makeText(MapsActivity.this, latitudee, Toast.LENGTH_SHORT).show();
                    openStreet();
                    return false;
//                marker = arg0.ger
                }
            });

        }

        private void openMap() {
            Intent intent = new Intent(this, com.example.UCOMPENSARDDAMN.ui.GPS.MapsActivity.class);
            intent.putExtra("Latitud",  lati1 );
            intent.putExtra("Longitud",  longi1 );
            intent.putExtra("Direccion",  address );
            startActivity(intent);
        }


        }








