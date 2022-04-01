package com.example.a2doproyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class Menu extends AppCompatActivity {

    private TextView ver, hola;
    private ImageButton botoncaballero;
    String usuario;
    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        ver=(TextView) findViewById(R.id.ver);
        hola=(TextView) findViewById(R.id.hola);
        botoncaballero= (ImageButton) findViewById(R.id.butoncaballero);
        usuario="";

        Bundle extras = getIntent().getExtras();
        if(extras!= null){
            if(extras.get("user")!=null){
                usuario=extras.get("user").toString();
            }else
                Toast.makeText(getApplicationContext(),"No existe user",Toast.LENGTH_LONG).show();

        }

        preferences=getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        usuario=preferences.getString("user","No existe el usuario");

        hola.setText("Bienvenido "+usuario);


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