package com.example.projeto;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
public class activity_cadastro extends AppCompatActivity {

    // Declaração dos campos do formulário
    private EditText editTextNome, editTextEmail, editTextSenha, editTextCpf, editTextRg, editTextEndereco, editTextTelefone;
    private Button buttonCadastrar;
    private TextView textViewVoltarLogin;
    private CriaBanco criaBanco;

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

        // Inicializa o banco de dados
        criaBanco = new CriaBanco(this);

        // Ao clicar no botão de "Cadastrar", você pode adicionar sua lógica de cadastro aqui.
        buttonCadastrar.setOnClickListener(v -> {
            String nome = editTextNome.getText().toString();
            String email = editTextEmail.getText().toString();
            String senha = editTextSenha.getText().toString();
            String cpf = editTextCpf.getText().toString();
            String rg = editTextRg.getText().toString();
            String endereco = editTextEndereco.getText().toString();
            String telefone = editTextTelefone.getText().toString();

            // Verifica se os campos estão preenchidos
            if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty() || rg.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
                Toast.makeText(activity_cadastro.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                // Se tudo estiver preenchido, insere no banco de dados
                boolean sucesso = cadastrarUsuario(nome, email, senha, cpf, rg, endereco, telefone);
                if (sucesso) {
                    // Se o cadastro for bem-sucedido, exibe uma mensagem e volta para a tela de login
                    Toast.makeText(activity_cadastro.this, "Usuário cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(activity_cadastro.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finaliza a Activity de Cadastro
                } else {
                    Toast.makeText(activity_cadastro.this, "Erro ao cadastrar usuário. Tente novamente.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Ao clicar em "Voltar para o login", volta para a tela de login
        textViewVoltarLogin.setOnClickListener(v -> {
            Intent intent = new Intent(activity_cadastro.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finaliza a Activity de Cadastro
        });
    }

    // Método para cadastrar o usuário no banco de dados
    private boolean cadastrarUsuario(String nome, String email, String senha, String cpf, String rg, String endereco, String telefone) {
        SQLiteDatabase db = criaBanco.getWritableDatabase();

        // Criação de um ContentValues para armazenar os dados
        ContentValues contentValues = new ContentValues();
        contentValues.put("nome", nome);
        contentValues.put("email", email);
        contentValues.put("senha", senha);
        contentValues.put("cpf", cpf);
        contentValues.put("rg", rg);
        contentValues.put("endereco", endereco);
        contentValues.put("telefone", telefone);

        // Tenta inserir os dados e verifica se foi bem-sucedido
        long result = db.insert("usuarios", null, contentValues);
        db.close();

        return result != -1; // Se o retorno for -1, ocorreu um erro
    }
}