package com.example.a2doproyectofinal;

import androidx.activity.OnBackPressedDispatcherOwner;
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
import java.util.logging.ErrorManager;

public class EditarDatosRegistro extends AppCompatActivity {

    EditText text13, text14, text15, text16;
    Button button10;
    private TextView cancelar2;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_datos_registro);

        text13 = (EditText) findViewById(R.id.text13);
        text14 = (EditText) findViewById(R.id.text15);
        text15 = (EditText) findViewById(R.id.text14);
        text16 = (EditText) findViewById(R.id.text16);
        button10 = (Button) findViewById(R.id.button10);
        cancelar2=(TextView) findViewById(R.id.cancelar2);

        Intent intent = getIntent();
        position = intent.getExtras(). getInt("position");

        text13.setText(VerRegistros.datosregistrossArrayList.get(position).getNombre());
        text14.setText(VerRegistros.datosregistrossArrayList.get(position).getContraseña());
        text15.setText(VerRegistros.datosregistrossArrayList.get(position).getEmail());
        text16.setText(VerRegistros.datosregistrossArrayList.get(position).getId());

        cancelar2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EditarDatosRegistro.this,VerRegistros.class);
                startActivity(intent);
            }
        });
    }


    public void actualizar(View view) {
        final String id = text16.getText().toString();
        final String nombre = text13.getText().toString();
        final String contraseña = text14.getText().toString();
        final String email = text15.getText().toString();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Actualizando Datos, Espere...");

        StringRequest request = new StringRequest(Request.Method.POST, "https://teen-informatics.000webhostapp.com/proyecto%20final/update.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(EditarDatosRegistro.this, response, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(getApplicationContext(), VerRegistros.class));
                finish();
                progressDialog.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(EditarDatosRegistro.this, error.getMessage(),Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        }){

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>params=new HashMap<>();
                params.put("id",id);
                params.put("nombre",nombre);
                params.put("contraseña",contraseña);
                params.put("email",email);

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(EditarDatosRegistro.this);
        requestQueue.add(request);
    }
}