package com.example.a2doproyectofinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RopaCaballeroMenu extends AppCompatActivity {
    private CheckBox camisa1, camisa2, camisa3, camisa4;
    private TextView resultadoropa, esperando, hola;
    private EditText edtCantidad1, edtCantidad2, edtCantidad3, edtCantidad4;
    String usuario;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ropa_caballero_menu);

        camisa1 = findViewById(R.id.camisa1);
        camisa2 = findViewById(R.id.camisa2);
        camisa3 = findViewById(R.id.camisa3);
        camisa4 = findViewById(R.id.camisa4);
        resultadoropa = findViewById(R.id.resultadoropa);

        edtCantidad1= findViewById(R.id.edtCantidad1);
        edtCantidad2= findViewById(R.id.edtCantidad2);
        edtCantidad3= findViewById(R.id.edtCantidad3);
        edtCantidad4= findViewById(R.id.edtCantidad4);
        esperando= findViewById(R.id.esperando);

        hola=(TextView) findViewById(R.id.hola);
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

        hola.setText(usuario+" ¿Que Llevaras Hoy?");

    }
    public void calcular (View view){
        String tex1_String =edtCantidad1.getText().toString();
        String tex2_String =edtCantidad2.getText().toString();
        String tex3_String =edtCantidad3.getText().toString();
        String tex4_String =edtCantidad4.getText().toString();

        int edtCantidad1_int = Integer.parseInt(tex1_String);
        int edtCantidad2_int = Integer.parseInt(tex2_String);
        int edtCantidad3_int = Integer.parseInt(tex3_String);
        int edtCantidad4_int = Integer.parseInt(tex4_String);
        int Sumar = edtCantidad1_int + edtCantidad2_int + edtCantidad3_int + edtCantidad4_int;       //Definitivo
        int SumaPrenda = 120 * edtCantidad1_int + 220 * edtCantidad2_int + 190 * edtCantidad3_int + 220 * edtCantidad4_int;

        if (Sumar >= 1){
            esperando.setText("Total: "+SumaPrenda);
        }
        }


    public void capturardatos(View view){
        if (camisa1.isChecked() && camisa2.isChecked() && camisa3.isChecked() && camisa4.isChecked() ){
            resultadoropa.setText("Seleccionaste: " + camisa1.getText()+ ", " + camisa2.getText() + ", " + camisa3.getText() + " y " + camisa4.getText());
        }
        else if(camisa1.isChecked() && camisa2.isChecked()&& camisa3.isChecked()&& camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText() + ", " + camisa2.getText()+ "y " + camisa3.getText() + camisa4.getText());
        }


        else if(camisa1.isChecked() && camisa2.isChecked() && camisa3.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText() + ", " + camisa2.getText()+ " y " + camisa3.getText());
        }
        else if(camisa1.isChecked() && camisa2.isChecked() && camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText() + ", " + camisa2.getText()+ " y " + camisa4.getText());
        }
        else if(camisa1.isChecked()&& camisa3.isChecked()&& camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText() + ", " + camisa3.getText()+ " y " + camisa4.getText());
        }
        else if(camisa2.isChecked() && camisa1.isChecked() && camisa3.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa2.getText() + ", " + camisa1.getText()+ " y " + camisa3.getText());
        }
        else if(camisa2.isChecked() && camisa3.isChecked() && camisa1.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa2.getText() + ", " + camisa3.getText()+ " y " + camisa4.getText());
        }
        else if(camisa2.isChecked() && camisa3.isChecked() && camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa2.getText() + ", " + camisa3.getText()+ " y " + camisa4.getText());
        }
        else if(camisa4.isChecked()&& camisa3.isChecked()&& camisa2.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa4.getText() + ", " + camisa3.getText()+ " y " + camisa2.getText());
        }
        else if(camisa4.isChecked()&& camisa2.isChecked()&& camisa1.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa4.getText() + ", " + camisa2.getText()+ " y " + camisa1.getText());
        }




        else if(camisa1.isChecked() && camisa2.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText() + " y " + camisa2.getText());
        }
        else if(camisa1.isChecked() && camisa3.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText() + " y " + camisa3.getText());
        }
        else if(camisa1.isChecked() && camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText() + " y " + camisa4.getText());
        }
        else if(camisa2.isChecked() && camisa1.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa2.getText() + " y " + camisa1.getText());
        }
        else if(camisa2.isChecked() && camisa3.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa2.getText() + " y " + camisa3.getText());
        }
        else if(camisa2.isChecked() && camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa2.getText() + " y " + camisa4.getText());
        }
        else if(camisa3.isChecked() && camisa1.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa3.getText()+ " y " + camisa1.getText());
        }
        else if(camisa3.isChecked() && camisa2.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa3.getText()+ " y " + camisa2.getText());
        }
        else if(camisa3.isChecked() && camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa3.getText()+ " y " + camisa4.getText());
        }



        else if(camisa2.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa2.getText());
    }else if(camisa1.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa1.getText());
        }else if(camisa3.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa3.getText());
        }else if(camisa4.isChecked()){
            resultadoropa.setText("Seleccionaste: "+ camisa4.getText());
        }else{
            resultadoropa.setText("¿Que te gustaría llevar?");
        }
    }}
