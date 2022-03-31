package com.example.a2doproyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Menu extends AppCompatActivity {

    private TextView ver;
    private ImageButton botoncaballero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ver=(TextView) findViewById(R.id.ver);
        botoncaballero= (ImageButton) findViewById(R.id.butoncaballero);

        botoncaballero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this, RopaCaballeroMenu.class);
                startActivity(intent);
            }
        });

        ver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Menu.this,VerRegistros.class);
                startActivity(intent);
            }
        });


    }
}