package com.example.projeto;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
public class activity_cadastro extends AppCompatActivity {

    // Declaração dos campos do formulário
    private EditText editTextNome, editTextEmail, editTextSenha, editTextCpf, editTextRg, editTextEndereco, editTextTelefone;
    private Button buttonCadastrar;
    private TextView textViewVoltarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro); // Certifique-se de que o layout correto seja usado

        // Inicializando os componentes
        editTextNome = findViewById(R.id.editTextNome);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextSenha = findViewById(R.id.editTextSenha);
        editTextCpf = findViewById(R.id.editTextCpf);
        editTextRg = findViewById(R.id.editTextRg);
        editTextEndereco = findViewById(R.id.editTextEndereco);
        editTextTelefone = findViewById(R.id.editTextTelefone);
        buttonCadastrar = findViewById(R.id.buttonCadastrar);
        textViewVoltarLogin = findViewById(R.id.textViewVoltarLogin);

        // Ao clicar no botão de "Cadastrar", você pode adicionar sua lógica de cadastro aqui.
        // Por enquanto, apenas volta para o Login.
        buttonCadastrar.setOnClickListener(v -> {
            // Lógica de cadastro pode ser inserida aqui.
            // Após cadastro, volta para a tela de login.
            Intent intent = new Intent(activity_cadastro.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza a Activity de Cadastro
        });

        // Ao clicar em "Voltar para o login", volta para a tela de login
        textViewVoltarLogin.setOnClickListener(v -> {
            Intent intent = new Intent(activity_cadastro.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza a Activity de Cadastro
        });



    }
}