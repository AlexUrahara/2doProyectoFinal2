package com.example.a2doproyectofinal;


import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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

public class MainActivity extends AppCompatActivity {
    Button button1, button2;
    private ImageButton vinculo;
    TextView salir;
    EditText usuario, pass;
    String T_usuario, T_contraseña;
    String url ="https://teen-informatics.000webhostapp.com/proyecto%20final/login.php";
    String _url = "https://www.youtube.com/channel/UCo_RZIP6prunIEL8tlLz7VQ/featured";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        vinculo = findViewById(R.id.vinculo);
        usuario = findViewById(R.id.usuario);
        pass = findViewById(R.id.pass);
        salir = findViewById(R.id.sailr);

        vinculo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri _Link = Uri.parse(_url);
                Intent i = new Intent(Intent.ACTION_VIEW,_Link);
                startActivity(i);
            }
        });

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(),"Saliendo de la Aplicación", Toast.LENGTH_LONG).show();
                Log.i("ERROR4","hiciste clic en salir");
                finish();
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,Registro.class);
                startActivity(intent);
            }
        });
    }
    public void MainActivity(View view) {
        if (usuario.getText().toString().equals("")) {
            Toast.makeText(this, "ingrese usuario", Toast.LENGTH_SHORT).show();
        }
        else if (pass.getText().toString().equals("")) {
            Toast.makeText(this, "ingrese contraseña", Toast.LENGTH_SHORT).show();
        }else {
            final ProgressDialog progressDialog=new ProgressDialog(this);
            progressDialog.setMessage("porfavor espere un segundo");
            progressDialog.show();

            T_usuario=usuario.getText().toString().trim();
            T_contraseña=pass.getText().toString().trim();

            StringRequest request =new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    progressDialog.dismiss();

                    if (response.equalsIgnoreCase("Iniciaste Sesión Correctamente")) {
                        usuario.setText("");
                        pass.setText("");
                        startActivity(new Intent(getApplicationContext(), Menu.class));
                    } else {
                        Toast.makeText(MainActivity.this, response, Toast.LENGTH_SHORT).show();
                    }

                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,error.getMessage().toString(),Toast.LENGTH_SHORT).show();
                }
            }){

                @Override
                protected Map<String, String> getParams() throws AuthFailureError {

                    Map<String,String>params=new HashMap<>();
                    params.put("nombre" ,T_usuario);
                    params.put("contraseña" ,T_contraseña);
                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
            requestQueue.add(request);
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    public void Registro(View view){
        startActivity(new Intent(getApplicationContext(),Registro.class));
        finish();
    }
}