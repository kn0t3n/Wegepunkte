package wegepunkte.sabel.com.wegepunkte;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import java.util.Date;

import wegepunkt.sabel.com.wegepunkt.R;

public class MainActivity extends AppCompatActivity {

    private Button btn_wpAnzeigen, btn_wpSpeichern;
    private LocationManager locationManager;
    private boolean isGPSenabled;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_wpAnzeigen = findViewById(R.id.btn_wpAnzeigen);
        btn_wpSpeichern = findViewById(R.id.btn_wpSpeichern);

        btn_wpSpeichern.setEnabled(false);
        isGPSenabled = false;


        btn_wpSpeichern.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                    return;
                }
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 6000, 10, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {

                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {

                    }

                    @Override
                    public void onProviderDisabled(String provider) {

                    }
                });
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                Wegepunkt wegepunkt = new Wegepunkt(new Date(), location.getLatitude(), location.getLongitude());
                Log.d("Meins", wegepunkt.toString());
            }
        });

        btn_wpAnzeigen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    private void initLocationManager(){
        locationManager = (LocationManager) this.getSystemService(this.LOCATION_SERVICE);
        isGPSenabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (shouldShowRequestPermissionRationale(Manifest.permission.ACCESS_FINE_LOCATION)) {
                Toast.makeText(this, "Ich brauche diese Berechtigung", Toast.LENGTH_SHORT);
            }
            requestPermission();
        } else {

           activateSaveLocation();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 4711 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            activateSaveLocation();
            Log.d("Meins", "Berechtigung da");
        }
    }

    private void requestPermission() {
        String[] permissions = new String[1];
        permissions[0] = Manifest.permission.ACCESS_FINE_LOCATION;
        requestPermissions(permissions, 4711);
    }

    private void activateSaveLocation(){
        this.initLocationManager();
        btn_wpSpeichern.setEnabled(isGPSenabled);
    }
}
