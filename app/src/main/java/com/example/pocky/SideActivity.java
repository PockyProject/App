package com.example.pocky;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.pocky.mainActivity.MainActivity;

public class SideActivity extends AppCompatActivity {

    Button cheese_stick, chicken, onion_ring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_side);

        cheese_stick = (Button) findViewById(R.id.cheese_stick_button);
        chicken = (Button) findViewById(R.id.chicken_button);
        onion_ring = (Button) findViewById(R.id.onion_ring_button);

        Intent intent_to_combo = getIntent();
        String menu = intent_to_combo.getExtras().getString("putside");
        AppCompatButton button_cancel = findViewById(R.id.button_cancel);


        //데이터 가져오는거 확인용
        /*String name = intent_to_combo.getExtras().getString("putside");
        TextView test = findViewById(R.id.set_text_test);
        test.setText(name);*/

        final int cheeseStickBtn = R.id.cheese_stick_button;


        View.OnClickListener side = new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                int id = v.getId();
                if (id == R.id.cheese_stick_button) {
                    Toast.makeText(SideActivity.this, "치즈스틱", Toast.LENGTH_SHORT).show();
                    Intent intent_cheese_stick = new Intent(SideActivity.this, DrinkActivity.class);
                    intent_cheese_stick.putExtra("put_side", menu + " Cheese_Stick");
                    startActivity(intent_cheese_stick);
                    finish();
                } else if (id == R.id.onion_ring_button) {
                    Toast.makeText(SideActivity.this, "어니언링", Toast.LENGTH_SHORT).show();
                    Intent intent_onion_ring = new Intent(SideActivity.this, DrinkActivity.class);
                    intent_onion_ring.putExtra("put_side", menu + " Onion_Ring");
                    startActivity(intent_onion_ring);
                    finish();
                } else if (id == R.id.chicken_button) {
                    Toast.makeText(SideActivity.this, "크리스피 치킨", Toast.LENGTH_SHORT).show();
                    Intent intent_chicken = new Intent(SideActivity.this, DrinkActivity.class);
                    intent_chicken.putExtra("put_side", menu + " Chicken");
                    startActivity(intent_chicken);
                    finish();
                }
            }
        };


        button_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancel);
                finish();
            }
        });

        cheese_stick.setOnClickListener(side);
        chicken.setOnClickListener(side);
        onion_ring.setOnClickListener(side);
    }

}

