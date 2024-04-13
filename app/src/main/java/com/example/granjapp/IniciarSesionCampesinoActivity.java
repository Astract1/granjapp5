package com.example.granjapp;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import androidx.appcompat.widget.AppCompatButton;
import android.widget.Toast;

public class IniciarSesionCampesinoActivity extends AppCompatActivity {

    private EditText editTextCorreoCampesino;
    private EditText editTextContraseñaCampesino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion_campesino);

        // Inicializar EditText
        editTextCorreoCampesino = findViewById(R.id.editTextCorreoCampesino);
        editTextContraseñaCampesino = findViewById(R.id.editTextContraseñaCampesino);

        // Obtener referencia al botón de iniciar sesión
        AppCompatButton btnIniciarSesion = findViewById(R.id.btnIngresarCampesino);

        // Configurar OnClickListener para el botón de iniciar sesión
        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iniciarSesionCampesino();
            }
        });
    }

    private void iniciarSesionCampesino() {
        // Obtener los valores de correo y contraseña ingresados por el usuario
        String correo = editTextCorreoCampesino.getText().toString().trim();
        String contraseña = editTextContraseñaCampesino.getText().toString().trim();

        // Validar credenciales con la base de datos
        dbHelper dbHelper = new dbHelper(this);
        boolean credencialesValidas = dbHelper.validarCredencialesCampesino(correo, contraseña);

        if (credencialesValidas) {
            Intent intent = new Intent(IniciarSesionCampesinoActivity.this, MenuGranjeroActivity.class);
            // Iniciar la actividad
            startActivity(intent);
            Toast.makeText(this, "Inicio de Sesion Correcto", Toast.LENGTH_SHORT).show();
        } else {
            // Mostrar mensaje de error si las credenciales son inválidas
            Toast.makeText(this, "Correo o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }
    }
}
