package com.example.dice_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;

import com.example.dice_app.databinding.Main;

import java.util.Random;

/**
 * 구현 기능 목록
 * 버튼을 누를 시 주사위이미지 랜덤 출력 - 완
 * shake 모션으로 버튼 클릭 효과 나타내기 - 완
 * 두 주사위의 값의 합산 결과를 화면에 나타내기 - 완
 * */
public class MainActivity extends AppCompatActivity implements SensorEventListener {
    Main binding;

    private SensorManager sensorManager;
    private Sensor sensor;
    private long shakeTime;
    private static final int SHAKE_SKIP_TIME = 500;
    private static final float SHAKE_THRESHOLD_GRAVITY = 2.7F;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.diceStartBtn.setOnClickListener(v -> startBtn());

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    public void startBtn() {
        Random random = new Random();
        int num1 = random.nextInt(6) + 1;
        int num2 = random.nextInt(6) + 1;
        Log.d("RandomNumber", "첫번째 주사위의 값: " + num1);
        Log.d("RandomNumber", "두번째 주사위의 값: " + num2);

        // 두 주사위의 값 합산 후 TextView 로 나타남.
        binding.sumNumber.setText(String.valueOf(num1 + num2));

        int[] img = {R.drawable.dice_1, R.drawable.dice_2, R.drawable.dice_3, R.drawable.dice_4, R.drawable.dice_5, R.drawable.dice_6};

        // 첫번째 주사위 이미지 출력
        for (int i = 0; i < img.length; i++ ) {
            int j = num1 - 1;
            if(i == j){
                binding.dice1.setImageResource(img[i]);
                break;
            }
        }
        // 두번째 주사위 이미지 출력
        for (int i = 0; i < img.length; i++ ) {
            int j = num2 - 1;
            if(i == j){
                binding.dice2.setImageResource(img[i]);
                break;
            }
        }

    }

    // SensorEventListener 등록
    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor,SensorManager.SENSOR_DELAY_NORMAL);
    }

    // SensorEventListener 해제
    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float axiosX = event.values[0];
            float axiosY = event.values[1];
            float axiosZ = event.values[2];
            float gravityX = axiosX / SensorManager.GRAVITY_EARTH;
            float gravityY = axiosY / SensorManager.GRAVITY_EARTH;
            float gravityZ = axiosZ / SensorManager.GRAVITY_EARTH;

            Float f = gravityX * gravityX * gravityY * gravityY * gravityZ * gravityZ;
            double squaredD = Math.sqrt(f.doubleValue());
            float gForce = (float) squaredD;
            if (gForce > SHAKE_THRESHOLD_GRAVITY) {
                long currentTime = System.currentTimeMillis();
                if (shakeTime + SHAKE_SKIP_TIME > currentTime) {
                    return;
                }
                shakeTime = currentTime;
                startBtn();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}