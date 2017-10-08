package com.example.paco.pasandoparametros;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Activity_2 extends AppCompatActivity {

    TextView textoInfo;
    Button botonContinuar;
    EditText cajaEdad;
    CharSequence nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        //Enlazamos elementos del xml a los objetos de la clase
        textoInfo = (TextView)findViewById(R.id.textoSaludo);
        botonContinuar = (Button)findViewById(R.id.botonContinuar);
        cajaEdad = (EditText)findViewById(R.id.cajaEdad);

        //Cogemos el intent que ha lanzado esta actividad
        Intent i = getIntent();
        Bundle parametrosRecibidos = i.getExtras();
        if(parametrosRecibidos != null){
            nombre = parametrosRecibidos.getCharSequence("nombre");
            //Mostramos la información en el texto del layout
            textoInfo.setText("Hola " + nombre + ", introduce tu edad");
        }

        //Añadimos un listener al boton
        botonContinuar.setOnClickListener(new View.OnClickListener(){
            @Override
            //Acción a realizar al hacer click sobre el botón continuar
            public void onClick(View v) {
                //Creamos un intent para pasar la información de la edad
                Intent i2 = new Intent(getApplicationContext(), MainActivity.class);
                //Con un objeto bundle pasaremos información a otra actividad
                Bundle parametrosAPasar = new Bundle();
                parametrosAPasar.putCharSequence("Edad", cajaEdad.getText());
                //Añadimos el bundle al Intent
                i2.putExtras(parametrosAPasar);
                //Cerramos esta actividad
                setResult(RESULT_OK, i2);
                finish();
            }
        });


    }
}
