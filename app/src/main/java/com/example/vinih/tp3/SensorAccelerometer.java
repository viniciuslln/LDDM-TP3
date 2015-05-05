package com.example.vinih.tp3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.FloatMath;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Vinih on 04/05/2015.
 */
public class SensorAccelerometer implements SensorEventListener {

    private Context context;
    LatLongDB db;
    private SensorManager sensorMan;
    private Sensor accelerometer;

    private float[] mGravity;
    private float mAccel;
    private float mAccelCurrent;
    private float mAccelLast;


    public SensorAccelerometer(Context context, LatLongDB mDB) {
        // TODO Auto-generated constructor stub

        this.context = context;
        db = mDB;

        initialiseSensor();
    }







    public void resume(){
        sensorMan.registerListener(this, accelerometer,
                SensorManager.SENSOR_DELAY_UI);
    }

    public void initialiseSensor() {
        sensorMan = (SensorManager) context.getSystemService(context.SENSOR_SERVICE);
        accelerometer = sensorMan.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mAccel = 0.00f;
        mAccelCurrent = SensorManager.GRAVITY_EARTH;
        mAccelLast = SensorManager.GRAVITY_EARTH;

    }

    public void unregisterSensor() {
        sensorMan.unregisterListener(this);
        Toast.makeText(context, "Sensor Stopped..", Toast.LENGTH_SHORT).show();
    }


    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            mGravity = event.values.clone();
            // Shake detection
            float x = mGravity[0];
            float y = mGravity[1];
            float z = mGravity[2];
            mAccelLast = mAccelCurrent;
            mAccelCurrent = FloatMath.sqrt(x*x + y*y + z*z);
            float delta = mAccelCurrent - mAccelLast;
            mAccel = mAccel * 0.9f + delta;
            // Make this higher or lower according to how much
            // motion you want to detect
            if(mAccel > 3){
                // do something
                GPSLoc mGPS = new GPSLoc(context);

                if(mGPS.canGetLocation ){
                    mGPS.getLocation();

                    LatLong ll = new LatLong(mGPS.getLatitude(), mGPS.getLongitude());
                    db.insert(ll);

                }else{
                    Toast.makeText(context, "Unabletofind", Toast.LENGTH_SHORT).show();
                    System.out.println("Unable");
                }

            }
        }
    }
}