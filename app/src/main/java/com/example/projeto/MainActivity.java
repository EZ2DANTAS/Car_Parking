package com.example.projeto;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextUsername, editTextPassword;
    private TextView  textViewCadastro;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // Certifique-se de que o layout correto seja usado

        // Inicializando os componentes
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewCadastro = findViewById(R.id.textViewCadastro);

        // Ao clicar no botão de Login, você pode verificar as credenciais ou apenas navegar
        buttonLogin.setOnClickListener(v -> {
            // Lógica de login pode ser inserida aqui.
            // Após login, redireciona para a tela principal (ou dashboard, etc).
            Intent intent = new Intent(MainActivity.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza a tela de login
        });





        // Clique no botão "Entrar"
        buttonLogin.setOnClickListener(v -> {
            // Sua lógica de login aqui
        });

        // Clique no texto "Cadastre-se"
        textViewCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_cadastro.class);
            startActivity(intent);
        });
    }
}