package com.example.dice_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.dice_app.databinding.Main;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Main binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        binding.diceStartBtn.setOnClickListener(v -> startBtn());
    }

    public void startBtn() {
        Toast.makeText(getApplicationContext(), "주사위 GO!", Toast.LENGTH_LONG).show();

        Random random = new Random();
        int num1 = random.nextInt(6) + 1;
        int num2 = random.nextInt(6) + 1;
        Log.d("RandomNumber", String.valueOf(num1));
        Log.d("RandomNumber", String.valueOf(num2));

        switch (num1) {
            case 1:
                binding.dice1.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                binding.dice1.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                binding.dice1.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                binding.dice1.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                binding.dice1.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                binding.dice1.setImageResource(R.drawable.dice_6);
                break;
        }

        switch (num2) {
            case 1:
                binding.dice2.setImageResource(R.drawable.dice_1);
                break;
            case 2:
                binding.dice2.setImageResource(R.drawable.dice_2);
                break;
            case 3:
                binding.dice2.setImageResource(R.drawable.dice_3);
                break;
            case 4:
                binding.dice2.setImageResource(R.drawable.dice_4);
                break;
            case 5:
                binding.dice2.setImageResource(R.drawable.dice_5);
                break;
            case 6:
                binding.dice2.setImageResource(R.drawable.dice_6);
                break;
        }
    }
}