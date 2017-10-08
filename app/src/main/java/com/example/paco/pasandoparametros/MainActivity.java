package com.example.paco.pasandoparametros;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText cajaNombre;
    private Button botonEnviar;
    private RadioButton opcion1, opcion2;
    private TextView textoInfo;
    private static final int PETICION = 1;
    private CharSequence cadenaEdad;
    private int edad = 19;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Enlazamos elementos del xml a los objetos Java creados previamente en esta clase
        cajaNombre=(EditText)findViewById(R.id.cajaNombre);
        botonEnviar=(Button)findViewById(R.id.botonEnviar);
        textoInfo=(TextView)findViewById(R.id.textoInfo);
        opcion1=(RadioButton)findViewById(R.id.opcionHombre);
        opcion2=(RadioButton)findViewById(R.id.opcionMujer);

        //Añadimos un listener al boton enviar
        botonEnviar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //Creamos un intent (no ponemos this porque sería el contexto del listener)
                Intent i = new Intent(getApplicationContext(), Activity_2.class);
                //Con un objeto bundle pasaremos información a otra actividad
                Bundle parametrosAPasar = new Bundle();
                parametrosAPasar.putCharSequence("nombre", cajaNombre.getText());
                //Añadimos el bundle al Intent
                i.putExtras(parametrosAPasar);
                //Lanzamos la actividad 2
                startActivityForResult(i, PETICION);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent datos) {

        super.onActivityResult(requestCode, resultCode, datos);

        if(requestCode == PETICION){
            switch (resultCode) {
                case RESULT_OK:
                    Bundle parametrosRecibidos = datos.getExtras();
                    if(parametrosRecibidos != null){
                        cadenaEdad = parametrosRecibidos.getCharSequence("Edad");
                        //Pasamos la cadena de la edad a entero
                        edad = Integer.parseInt(cadenaEdad.toString());
                    }
                    break;
                case RESULT_CANCELED:
                    // Cancelación o cualquier situación de error
                    break;
            }
        }
        //Desactivamos boton y caja de texto
        botonEnviar.setEnabled(false);
        cajaNombre.setEnabled(false);
        opcion1.setEnabled(false);
        opcion2.setEnabled(false);


        //Finalmente mostramos la información según la edad
        if(edad > 18 && edad < 25){
            textoInfo.setText("Como tienes " + edad + " años, ya eres mayor de edad");
        }
        else if(edad >=25 && edad < 35){
            textoInfo.setText("Tienes " + edad + " años, estás en la flor de la vida");
        }
        else if(edad >= 35){
            textoInfo.setText("Tienes " + edad + " años, tienes que empezar a cuidarte..");
        }
        else{
            textoInfo.setText("Tienes " + edad + " años, eres menor de edad");
        }
    }
}
