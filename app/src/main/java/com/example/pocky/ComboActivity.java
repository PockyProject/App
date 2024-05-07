package com.example.pocky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.pocky.mainActivity.MainActivity;

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

        switch (burger){
            case "Classic_Burger":
                imageView_meal.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_burger_classic,0,0);
                imageView_meal.setText(getResources().getString(R.string.classic_burger));
                imageView_combo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_combo_classic,0,0);
                imageView_combo.setText(getResources().getString(R.string.classic_combo));
                break;
            case "Cheese_Burger":
                imageView_meal.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_burger_cheese,0,0);
                imageView_meal.setText(getResources().getString(R.string.cheese_burger));
                imageView_combo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_combo_cheese,0,0);
                imageView_combo.setText(getResources().getString(R.string.cheese_combo));
                break;
            case "Double_Cheese_Burger":
                imageView_meal.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_burger_doublecheese,0,0);
                imageView_meal.setText(getResources().getString(R.string.doublecheese_burger));
                imageView_combo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_combo_double,0,0);
                imageView_combo.setText(getResources().getString(R.string.doublecheese_combo));
                break;
            case "Pound_Burger":
                imageView_meal.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_burger_pound,0,0);
                imageView_meal.setText(getResources().getString(R.string.pound_burger));
                imageView_combo.setCompoundDrawablesWithIntrinsicBounds(0,R.drawable.resize_burger_pound,0,0);
                imageView_combo.setText(getResources().getString(R.string.pound_combo));
                break;
        }

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