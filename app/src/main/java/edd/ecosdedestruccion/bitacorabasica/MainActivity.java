package edd.ecosdedestruccion.bitacorabasica;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOError;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    //Variables para los componetes
    private EditText edt1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Relación componentes con lógica
        edt1 = (EditText) findViewById(R.id.txt_bitacora);

        //Búsqueda de ficheros de la aplicación
        //fileList() -> Array con la lista de ficheros
        String[] archivos = fileList();

        if(archivoExiste(archivos, "bitacora.txt")){
            try {
                //Apertura y lectura del archivo
                InputStreamReader archivo = new InputStreamReader(openFileInput("bitacora.txt"));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                //variabla para acumular el texto leído
                String bitacoraCompleta = "";
                //lectura de líneas
                while(linea != null){
                    bitacoraCompleta = bitacoraCompleta+ linea + "\n";
                    linea = br.readLine();
                }
                //fin de lectura
                br.close();
                //cierre del archivo
                archivo.close();
                //Colocación del texto completo
                edt1.setText(bitacoraCompleta);
            }catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean archivoExiste(String[] archivos, String file){
        for(int i = 0; i < archivos.length; i++){
            if(file.equals((archivos[i])));
            return true;
        }
        return false;
    }

    public void guardar(View view){
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput("bitacora.txt", Activity.MODE_PRIVATE));
            archivo.write(edt1.getText().toString());
            archivo.flush();    //limpieza de restos
            archivo.close();

        } catch (IOException e){
            e.printStackTrace();
        }
        Toast.makeText(this, "Guardado correcto", Toast.LENGTH_SHORT).show();
        finish();
    }
}
