package com.example.a2doproyectofinal;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class Registro extends AppCompatActivity {
    EditText text3,text4,text5;
    private ImageButton vinculo;
    Button button3;
    String _url = "https://www.youtube.com/channel/UCo_RZIP6prunIEL8tlLz7VQ/featured";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        text3=(EditText) findViewById(R.id.text3);
        text4=(EditText) findViewById(R.id.text4);
        text5=(EditText) findViewById(R.id.text5);
        vinculo = findViewById(R.id.vinculo);
        button3=findViewById(R.id.button3);

        vinculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri _Link = Uri.parse(_url);
                Intent i = new Intent(Intent.ACTION_VIEW,_Link);
                startActivity(i);
            }
        });

        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insertData();
            }
        });
    }
    private void insertData() {

        final String nombre = text3.getText().toString().trim();
        final String contraseña = text4.getText().toString().trim();
        final String email = text5.getText().toString().trim();

        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("cargando...");

        if(nombre.isEmpty()){
            text3.setError("complete los campos");
            return;
        }else if (contraseña.isEmpty()){
            text4.setError("Complete los campos");
        } else if(email.isEmpty()){
            text5.setError("complete los campos");
            return;
        }else{
            progressDialog.show();
            StringRequest request=new StringRequest(Request.Method.POST,
                    "https://teen-informatics.000webhostapp.com/proyecto%20final/index.php", new Response.Listener<String>() {

                   @Override
                public void onResponse(String response) {
                    if (response.equalsIgnoreCase("Los Datos Fueron Ingresados Correctamente")) {
                        Toast.makeText(Registro.this, "Los Datos Fueron Ingresados Correctamente", Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();

                        Intent intent = new Intent(Registro.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Registro.this, response, Toast.LENGTH_SHORT).show();
                        progressDialog.dismiss();
                    }
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(Registro.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }){
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String >params=new HashMap<String, String>();
                    params.put("nombre",nombre);
                    params.put("contraseña",contraseña);
                    params.put("email",email);
                    return params;
                }
            };
           RequestQueue requestQueue = Volley.newRequestQueue(Registro.this);
           requestQueue.add(request);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void MainActivi(View view){
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}