package com.example.pocky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.pocky.presentation.screen.main.MainActivity;

public class ComboActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_combo);

        AppCompatButton imageView_meal = findViewById(R.id.meal_button);
        AppCompatButton imageView_combo = findViewById(R.id.combo_button);
        AppCompatButton button_cancel = findViewById(R.id.cancel_button);

        Intent intent_to_main = getIntent();
        String burger = intent_to_main.getStringExtra("put_burger");


        imageView_meal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent combo_meal = new Intent(getApplicationContext(), AdditionalActivity.class);
                combo_meal.putExtra("putside", burger+" Meal");
                startActivity(combo_meal);
                finish();
            }
        });

        imageView_combo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent combo_combo = new Intent(getApplicationContext(), SideActivity.class);
                combo_combo.putExtra("putside", "Combo");
                combo_combo.putExtra("putside", burger+" Combo");
                startActivity(combo_combo);
                finish();
            }
        });

        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancel);
                finish();
            }
        });

    }
}