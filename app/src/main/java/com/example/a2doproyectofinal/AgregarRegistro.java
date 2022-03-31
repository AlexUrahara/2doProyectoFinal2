package com.example.a2doproyectofinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class AgregarRegistro extends AppCompatActivity {
    private TextView cancelar2;
    private EditText text6,text7,text8;
    private Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_registro);

        cancelar2=(TextView) findViewById(R.id.cancelar2);
        button5=(Button) findViewById(R.id.button5);
        text6=(EditText) findViewById(R.id.text6);
        text7=(EditText) findViewById(R.id.text7);
        text8=(EditText) findViewById(R.id.text8);

        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                añadirDatos();
            }
        });

        cancelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AgregarRegistro.this,VerRegistros.class);
                startActivity(intent);
            }
        });


    }

    private void añadirDatos() {
        final String nombre=text6.getText().toString().trim();
        final String contraseña=text7.getText().toString().trim();
        final String email=text8.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Cargando...");

        if(nombre.isEmpty()){
            text6.setError("coloca tu nombre");
            return;
        }else if (contraseña.isEmpty()){
            text7.setError("Coloca una contraseña");
            return;
        } else if(email.isEmpty()){
            text8.setError("Coloca tu Email");
            return;
        }else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST,
                    "https://teen-informatics.000webhostapp.com/proyecto%20final/index.php", new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Los Datos Fueron Ingresados Correctamente")) {
                        Toast.makeText(AgregarRegistro.this, "Los Datos Fueron Ingresados Correctamente", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(AgregarRegistro.this, VerRegistros.class);
                        finish();
                    } else {
                        Toast.makeText(AgregarRegistro.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(AgregarRegistro.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Nullable
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String >params=new HashMap<String, String>();
                    params.put("nombre",nombre);
                    params.put("contraseña",contraseña);
                    params.put("email",email);
                    return params;
                }
                };
            RequestQueue requestQueue = Volley.newRequestQueue(AgregarRegistro.this);
            requestQueue.add(request);
            }
        }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}