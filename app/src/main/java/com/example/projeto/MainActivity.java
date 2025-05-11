package com.example.projeto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private TextView textViewCadastro;
    private Button buttonLogin;

    CriaBanco dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializando os componentes
        editTextUsername = findViewById(R.id.editTextUsername);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);
        textViewCadastro = findViewById(R.id.textViewCadastro);

        // Inicializa o banco
        dbHelper = new CriaBanco(this);

        // Clique no botão "Entrar"
        buttonLogin.setOnClickListener(v -> {
            String usuario = editTextUsername.getText().toString();
            String senha = editTextPassword.getText().toString();
            if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                boolean sucessoLogin = verificaLogin(usuario, senha);
                if (sucessoLogin) {
                    Intent intent = new Intent(MainActivity.this, EstacionamentoActivity.class); // Tela principal
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(MainActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }



           /* if (usuario.isEmpty() || senha.isEmpty()) {
                Toast.makeText(MainActivity.this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            } else {
                boolean sucessoLogin = verificaLogin(usuario, senha);
                if (sucessoLogin) {
                    // Login bem-sucedido, redireciona para a tela principal
                    Intent intent = new Intent(MainActivity.this, EstacionamentoActivity.class); // Tela principal
                    startActivity(intent);
                    finish(); // Finaliza a tela de login
                } else {
                    Toast.makeText(MainActivity.this, "Usuário ou senha inválidos", Toast.LENGTH_SHORT).show();
                }
            }
            */

        });


        // Clique no texto "Cadastre-se"
        textViewCadastro.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, activity_cadastro.class);
            startActivity(intent);
        });
    }

    // Método que verifica se o usuário existe no banco
    private boolean verificaLogin(String usuario, String senha) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = null;
        boolean encontrado = false;

        try {
            // Consulta para verificar se o usuário e senha existem
            cursor = db.rawQuery(
                    "SELECT * FROM usuarios WHERE nome = ? AND senha = ?",
                    new String[]{usuario, senha}
            );

            // Verifica se a consulta retornou algum registro
            if (cursor != null && cursor.getCount() > 0) {
                encontrado = true;
            }

        } catch (Exception e) {
            e.printStackTrace();  // Log para debug, pode ser removido em produção
        } finally {
            // Fecha o cursor e o banco de dados para evitar vazamento de recursos
            if (cursor != null) {
                cursor.close();
            }
            db.close();
        }

        return encontrado;
    }

}