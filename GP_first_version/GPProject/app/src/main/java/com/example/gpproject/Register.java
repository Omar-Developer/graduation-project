package com.example.gpproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Register extends AppCompatActivity {

    ImageView backbttn;
    Button regbttn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backbttn = findViewById(R.id.back);
        regbttn2 = findViewById(R.id.regbttn2);

        backbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        regbttn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, Register2.class);
                startActivity(intent);
            }
        });
    }
}
