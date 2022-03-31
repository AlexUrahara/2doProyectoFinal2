package com.example.a2doproyectofinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdaptadorU extends ArrayAdapter<datosregistros>{

    Context context;
    List<datosregistros> arraydatosregistros;

    public AdaptadorU(@NonNull Context context,  List<datosregistros> arraydatosregistros) {
        super(context, R.layout.list_users,arraydatosregistros);
        this.context=context;
        this.arraydatosregistros=arraydatosregistros;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_users,null,true);
        TextView ID = view.findViewById(R.id.id);
        TextView Nombre = view.findViewById(R.id.nombre);

        ID.setText(arraydatosregistros.get(position).getId());
        Nombre.setText(arraydatosregistros.get(position).getNombre());

        return view;
    }}
