package my.food.business_marketing;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;
import android.Manifest;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class BottomBarActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    BottomNavigationView bottomnavigationview;
    FloatingActionButton add_business;

    //for users current location
    private static final int PERMISSIONS_REQUEST_LOCATION = 99;
    private LocationManager locationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomnavigationview = findViewById(R.id.bottomNavigationView);
        bottomnavigationview.setOnNavigationItemSelectedListener(this);
        add_business = findViewById(R.id.add_business);


        add_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(i);
            }
        });

        Home hm1 = new Home();
        loadfragment(hm1);

        //for current location  1

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                 // Check for runtime permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    PERMISSIONS_REQUEST_LOCATION);
        } else {
// Permissions granted, get current location
            getCurrentLocation();
        }

    }
    //for current location  2

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
// Permissions granted, get current location
                    getCurrentLocation();
                } else {
// Permissions denied
                    Toast.makeText(this, "Location permissions denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
    //for current location  3

    private void getCurrentLocation() {
        Location location = null;
        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            if (ActivityCompat.checkSelfPermission(this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                    ActivityCompat.checkSelfPermission(this,
                            android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
             // TODO: Consider calling
            // ActivityCompat#requestPermissions
           // here to request the missing permissions, and then overriding
          // public void onRequestPermissionsResult(int requestCode, String[] permissions,
          // int[] grantResults)
         // to handle the case where the user grants the permission. See the documentation
         // for ActivityCompat#requestPermissions for more details.
                return;
            }
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            // Use current location
                            Toast.makeText(BottomBarActivity.this,""+location.getLatitude(),Toast.LENGTH_LONG).show();
                        }



                        @Override
                        public void onStatusChanged(String provider, int status, Bundle
                                extras) {
                        }
                        @Override
                        public void onProviderEnabled(String provider) {
                        }
                        @Override
                        public void onProviderDisabled(String provider) {
                        }
                    });
            location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    0, 0, new LocationListener() {

                        @Override
                        public void onLocationChanged(Location location) {
         // Use current location
                        }
                        @Override
                        public void onStatusChanged(String provider, int status, Bundle
                                extras) {}
                        @Override
                        public void onProviderEnabled(String provider) {}
                        @Override
                        public void onProviderDisabled(String provider) {}
                    });
            location =
                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (location != null) {
// Use current location
        } else {
// Location not available
        }
    }


//upto here current location code


    public void loadfragment(Fragment fragment){
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().replace(R.id.flFragment, fragment).commit();

    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.person:
                Toast.makeText(BottomBarActivity.this,"Contact us clicked",Toast.LENGTH_SHORT).show();
              About_Us af= new About_Us();
              loadfragment(af);
                return true;

            case  R.id.home:
                Toast.makeText(BottomBarActivity.this,"home clicked",Toast.LENGTH_SHORT).show();
                Home hm=new Home();
                loadfragment(hm);
                return  true;

            case R.id.settings:
                Toast.makeText(BottomBarActivity.this,"setting clicked",Toast.LENGTH_SHORT).show();
                Contact_Us cf=new Contact_Us();
                loadfragment(cf);
                return true;

        }
        return false;
    }
}