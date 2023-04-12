package com.example.ambtempsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //create an instance of SensorManger class to get an instance of  physical sensor
    private SensorManager sensorManager;
    private Sensor pressure;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get instance of sensor service, an use that to get an instance of a particular sensor
        sensorManager = (SensorManager) getSystemService(Sensor)
    }
}