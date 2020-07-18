package com.example.loginapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class OpportunisticActivity extends AppCompatActivity implements LocationListener,SensorEventListener{

    private LocationManager locationManager;
    private SensorManager sensorManager;
    private Sensor accelerometer, mgyro;
    TextView xValue, yValue, zValue, xgyroValue, ygyroValue, zgyroValue;
    private static final String TAG = "OpportunisticActivity";
    EditText editTextName;
    EditText editTextLocation;
    Button buttonStart, buttonStop;
    Spinner spinnerAllowPermission;
    TextView textView1;
    ListView listView;
    DatabaseReference sensordatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opportunistic);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        mgyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        Toast.makeText(OpportunisticActivity.this, "Firebase connection success", Toast.LENGTH_LONG).show();

        sensordatabase = FirebaseDatabase.getInstance().getReference("sensors");

        editTextName = (EditText) findViewById(R.id.editTextName);
        textView1 = (TextView) findViewById(R.id.textView1);
        spinnerAllowPermission = (Spinner) findViewById(R.id.spinnerAllowPermission);
        editTextLocation = (EditText) findViewById(R.id.editTextLocation);
        buttonStart = (Button) findViewById(R.id.buttonStart);
        buttonStop = (Button) findViewById(R.id.buttonStop);
        listView = (ListView) findViewById(R.id.listView);

        xValue = (TextView) findViewById(R.id.xValue);
        yValue = (TextView) findViewById(R.id.yValue);
        zValue = (TextView) findViewById(R.id.zValue);
        xgyroValue = (TextView) findViewById(R.id.xgyroValue);
        ygyroValue = (TextView) findViewById(R.id.ygyroValue);
        zgyroValue = (TextView) findViewById(R.id.zgyroValue);

        buttonStart.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                onStart();
                addValues();

                Timer timer = new Timer();
                timer.schedule(new TimerTask() {

                    public void run() {
                        addValues();
                    }
                }, 30 * 1000, 30 * 1000);
            }
        });

        final ArrayList<String> list = new ArrayList<>();
        final ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.list_item, list);
        listView.setAdapter(adapter);
        sensordatabase.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot dataSnapshot) {
                list.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    list.add(snapshot.getValue().toString());
                }
                adapter.notifyDataSetChanged();

            }

            public void onCancelled(DatabaseError databaseError) {

            }
        });

        buttonStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onStop();
                Intent intent = new Intent(OpportunisticActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });

    }


    public void onLocationChanged(Location location) {
        double longitude = location.getLongitude();
        double latitude = location.getLatitude();
    }

    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(final SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            xValue.setText("xValue:" + sensorEvent.values[0]);
            yValue.setText("yValue:" + sensorEvent.values[1]);
            zValue.setText("zValue:" + sensorEvent.values[2]);

        }

        if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            xgyroValue.setText("xgyroValue:" + sensorEvent.values[0]);
            ygyroValue.setText("ygyroValue:" + sensorEvent.values[1]);
            zgyroValue.setText("zgyroValue:" + sensorEvent.values[2]);

        }
    }

    private void addValues() {
        String name = editTextName.getText().toString().trim();
        String allowPermission = spinnerAllowPermission.getSelectedItem().toString();
        String location = editTextLocation.getText().toString().trim();

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Location location1 = locationManager.getLastKnownLocation(locationManager.NETWORK_PROVIDER);
        double latitude=location1.getLatitude();
        double longitude=location1.getLongitude();

        double xValue1= Double.parseDouble(xValue.getText().toString().substring(7));
        double yValue1= Double.parseDouble(yValue.getText().toString().substring(7));
        double zValue1= Double.parseDouble(zValue.getText().toString().substring(7));
        double xgyroValue1= Double.parseDouble(xgyroValue.getText().toString().substring(11));
        double ygyroValue1= Double.parseDouble(ygyroValue.getText().toString().substring(11));
        double zgyroValue1= Double.parseDouble(zgyroValue.getText().toString().substring(11));



        int n = 5;
        ArrayList<Double> arrli1 = new ArrayList<Double>(n);
        ArrayList<Double> arrli2 = new ArrayList<Double>(n);
        ArrayList<Double> arrli3 = new ArrayList<Double>(n);
        ArrayList<Double> arrli4 = new ArrayList<Double>(n);
        ArrayList<Double> arrli5 = new ArrayList<Double>(n);
        ArrayList<Double> arrli6 = new ArrayList<Double>(n);
        ArrayList<Double> arrli7 = new ArrayList<Double>(n);
        ArrayList<Double> arrli8 = new ArrayList<Double>(n);

        arrayli arrl=new arrayli(n,arrli1,arrli2,arrli3,arrli4,arrli5,arrli6,arrli7,arrli8);
        arrl.arrli1.add(xValue1);
        arrl.arrli2.add(yValue1);
        arrl.arrli3.add(zValue1);
        arrl.arrli4.add(xgyroValue1);
        arrl.arrli5.add(ygyroValue1);
        arrl.arrli6.add(zgyroValue1);
        arrl.arrli7.add(latitude);
        arrl.arrli8.add(longitude);


        if(!TextUtils.isEmpty(name)){

            String id=sensordatabase.push().getKey();
            Values values= new Values(id,name,location,arrli1.get(0),arrli2.get(0),arrli3.get(0),arrli4.get(0),arrli5.get(0),arrli6.get(0),arrli7.get(0),arrli8.get(0));
            sensordatabase.child(id).setValue(values);

            for (int i=1; i<arrli1.size(); i++) {
                Values values1= new Values(id,name,location,arrli1.get(i),arrli2.get(i),arrli3.get(i),arrli4.get(i),arrli5.get(i),arrli6.get(i),arrli7.get(i),arrli8.get(i));
                sensordatabase.child(id).setValue(values1);
            }

        }

        else{
            Toast.makeText(this,"You should enter a name",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();


        if (accelerometer != null) {
            sensorManager.registerListener(this, accelerometer, 1000000, 1000000);    //1 sec delay
        }
        if (mgyro != null) {
            sensorManager.registerListener(this,mgyro, 1000000, 1000000);  //1 sec delay
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        sensorManager.unregisterListener(this);
    }


    public void onStatusChanged(String provider, int status, Bundle extras) {

    }


    public void onProviderEnabled(String provider) {

    }


    public void onProviderDisabled(String provider) {

    }

}
