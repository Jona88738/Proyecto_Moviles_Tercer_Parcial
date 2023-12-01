package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class ActivityCupones extends AppCompatActivity {

    public static int Descuento = 100;
    public static boolean isValido = false;
    private TextView codigo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cupones);

        codigo = findViewById(R.id.txtCupon);
    }

    public void escanear(View view) {

        IntentIntegrator intentIntegrator = new IntentIntegrator(ActivityCupones.this);

        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        intentIntegrator.setPrompt("Leer cupon");
        intentIntegrator.setCameraId(0);
        intentIntegrator.setBeepEnabled(true);
        intentIntegrator.setBarcodeImageEnabled(true);
        intentIntegrator.initiateScan();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Datos leidos", Toast.LENGTH_SHORT).show();
                codigo.setText(intentResult.getContents());
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    public void validarCupon(View view) {
        if(codigo.getText().toString().equals("")) {
            Toast.makeText(this, "Se necesita leer un codigo", Toast.LENGTH_SHORT).show();
        } else {
            isValido = true;
            Toast.makeText(this, "El cupon se ha validado exitosamente", Toast.LENGTH_SHORT).show();
        }
    }
}