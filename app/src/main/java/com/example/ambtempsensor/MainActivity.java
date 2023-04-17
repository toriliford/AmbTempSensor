package com.example.ambtempsensor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener{

    //create an instance of SensorManger class to get an instance of  physical sensor
    private SensorManager sensorManager;
    private Sensor ambTemp;

    private TextView tvTemp;
    private Switch tempSwitch;

    private TextView tvTempF;
    private String errorMsg = "Sensor Not Supported";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //set the textview
        tvTemp = findViewById(R.id.temperature);
        tvTempF = findViewById(R.id.temperature2);


        //get instance of sensor service, and use that to get an instance of a particular sensor
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        //If the phone is at least API level 14..
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH){
            //instance of ambient temp sensor
            ambTemp = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
        }
        if(ambTemp == null){
            tvTemp.setText(errorMsg);
        }

        //code the solution for the switch
        tempSwitch = findViewById(R.id.measurementSwitch);

        tempSwitch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(!tempSwitch.isChecked()){
                    //tvTempF.setText("--" + "\u00B0" + " F");
                    tvTempF.setVisibility(View.INVISIBLE);
                }
                else if(tempSwitch.isChecked()){
                    //calculation for changing temperature
                    String celsiusStr = tvTemp.getText().toString();
                    double celsiusTemp = Double.parseDouble(celsiusStr);
                    double calculateF = (celsiusTemp * 1.8) + 32;
                    String calcFStr = Double.toString(calculateF) + "\u00B0" + " F";

                    tvTempF.setVisibility(View.VISIBLE);
                    tvTempF.setText(calcFStr);
                }
            }
        });

    }// end of onCreate



    //@Override
    public final void onAccuracyChanged(Sensor sensor, int accuracy){
        //do something here if sensor accuracy changes
    }

    @Override
    public final void onSensorChanged(SensorEvent event){

        //take the temp from the event value (amb temp in degrees celsius)
        float currentTemp = event.values[0];
        // Do something with this sensor data.
        String tempString = Float.toString(currentTemp);

        tvTemp.setText(tempString);
    }

    @Override
    protected void onResume() {
        //register a listener for the sensor
        super.onResume();
        sensorManager.registerListener(this, ambTemp, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        //be sure to unregister the sensor when the activity pauses
        super.onPause();
        sensorManager.unregisterListener(this);
    }



}