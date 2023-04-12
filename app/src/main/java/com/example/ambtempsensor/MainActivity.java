package com.example.ambtempsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //create an instance of SensorManger class to get an instance of  physical sensor
    private SensorManager sensorManager;
    private Sensor ambTemp;

    //check if phone has ambient temp sensor installed

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get instance of sensor service, an use that to get an instance of a particular sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //instance of ambient temp sensor
        ambTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
    }// end of onCreate

    //@Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy){
        //do something here if sensor accuracy changes
    }

    //@Override
    public final void onSensorChanged(SensorEvent event){
        //example from pressure sensor:
            //float millibarsOfPressure = event.values[0];

        //take the temp from the event value (amb temp in degrees celsius)
        float currentTemp = event.values[0];
        // Do something with this sensor data.
    }

    @Override
    protected void onResume() {
        //register a listener for the sensor
        super.onResume();
        sensorManager.registerListener((SensorEventListener) this, ambTemp, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        //be sure to unregister the sensor when the activity pauses
        super.onPause();
        sensorManager.unregisterListener((SensorEventListener) this);
    }

}