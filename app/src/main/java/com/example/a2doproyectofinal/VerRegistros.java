package com.example.a2doproyectofinal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class VerRegistros extends AppCompatActivity {
 ListView listView;
 AdaptadorU adaptadorU;

    public static ArrayList<datosregistros> datosregistrossArrayList = new ArrayList<>();
    String url = "https://teen-informatics.000webhostapp.com/proyecto%20final/VerRegistros.php";
    datosregistros datosregistros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_registros);

        listView=findViewById(R.id.ListVerRegistros);
        adaptadorU = new AdaptadorU(this, datosregistrossArrayList);
        listView.setAdapter(adaptadorU);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                ProgressDialog progressDialog = new ProgressDialog(view.getContext());
                CharSequence[] dialogItem = {"Modificar Datos del Registro"};
                builder.setTitle(datosregistrossArrayList.get(position).getNombre());
                builder.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i){
                            case 0:
                                startActivity(new Intent(getApplicationContext(),EditarDatosRegistro.class)
                                        .putExtra("position",position));
                                break;
                        }
                    }
                });
                builder.create().show();
            }
        });
        OrdenarDatos();
    }
    private void OrdenarDatos() {
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        datosregistrossArrayList.clear();
                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String Abandonar = jsonObject.getString("Abandonar");
                            JSONArray jsonArray = jsonObject.getJSONArray("Los datos registrados son:");

                            if(Abandonar.equals("1")){
                                for(int i=0;i<jsonArray.length();i++){
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String id = object.getString("id");
                                    String nombre = object.getString("nombre");
                                    String contraseña = object.getString("contraseña");
                                    String email = object.getString("email");

                                    datosregistros = new datosregistros(id,nombre,contraseña,email);
                                    datosregistrossArrayList.add(datosregistros);
                                    adaptadorU.notifyDataSetChanged();
                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(VerRegistros.this, error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
    public void añadir(View view){
        startActivity(new Intent(getApplicationContext(),AgregarRegistro.class));
    }
}