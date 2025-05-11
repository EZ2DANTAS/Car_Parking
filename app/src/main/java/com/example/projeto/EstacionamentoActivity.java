package com.example.projeto;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EstacionamentoActivity extends AppCompatActivity {

    EditText editId, editPlaca, editModelo, editCor;
    Button btnSalvar, btnListar, btnAtualizar, btnExcluir;
    SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estacionamento);

        editId = findViewById(R.id.editId);
        editPlaca = findViewById(R.id.editPlaca);
        editModelo = findViewById(R.id.editModelo);
        editCor = findViewById(R.id.editCor);

        btnSalvar = findViewById(R.id.btnSalvar);
        btnListar = findViewById(R.id.btnListar);
        btnAtualizar = findViewById(R.id.btnAtualizar);
        btnExcluir = findViewById(R.id.btnExcluir);

        CriaBanco banco = new CriaBanco(this);
        db = banco.getWritableDatabase();

        btnSalvar.setOnClickListener(v -> {
            ContentValues valores = new ContentValues();
            valores.put("placa", editPlaca.getText().toString());
            valores.put("modelo", editModelo.getText().toString());
            valores.put("cor", editCor.getText().toString());

            long resultado = db.insert("carros", null, valores);
            if (resultado != -1) {
                Toast.makeText(this, "Carro salvo!", Toast.LENGTH_SHORT).show();
                limparCampos();
            } else {
                Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
            }
        });

        btnListar.setOnClickListener(v -> {
            Cursor cursor = db.rawQuery("SELECT * FROM carros", null);
            if (cursor.getCount() > 0) {
                StringBuilder sb = new StringBuilder();
                while (cursor.moveToNext()) {
                    sb.append("ID: ").append(cursor.getInt(0)).append("\n");
                    sb.append("Placa: ").append(cursor.getString(1)).append("\n");
                    sb.append("Modelo: ").append(cursor.getString(2)).append("\n");
                    sb.append("Cor: ").append(cursor.getString(3)).append("\n\n");
                }
                mostrarDialogo("Lista de Carros", sb.toString());
            } else {
                Toast.makeText(this, "Nenhum carro cadastrado", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        });

        btnAtualizar.setOnClickListener(v -> {
            String id = editId.getText().toString();
            if (id.isEmpty()) {
                Toast.makeText(this, "Informe o ID", Toast.LENGTH_SHORT).show();
                return;
            }

            ContentValues valores = new ContentValues();
            valores.put("placa", editPlaca.getText().toString());
            valores.put("modelo", editModelo.getText().toString());
            valores.put("cor", editCor.getText().toString());

            int linhas = db.update("carros", valores, "id = ?", new String[]{id});
            if (linhas > 0) {
                Toast.makeText(this, "Carro atualizado!", Toast.LENGTH_SHORT).show();
                limparCampos();
            } else {
                Toast.makeText(this, "ID não encontrado", Toast.LENGTH_SHORT).show();
            }
        });

        btnExcluir.setOnClickListener(v -> {
            String id = editId.getText().toString();
            if (id.isEmpty()) {
                Toast.makeText(this, "Informe o ID", Toast.LENGTH_SHORT).show();
                return;
            }

            int linhas = db.delete("carros", "id = ?", new String[]{id});
            if (linhas > 0) {
                Toast.makeText(this, "Carro excluído!", Toast.LENGTH_SHORT).show();
                limparCampos();
            } else {
                Toast.makeText(this, "ID não encontrado", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void limparCampos() {
        editId.setText("");
        editPlaca.setText("");
        editModelo.setText("");
        editCor.setText("");
    }

    private void mostrarDialogo(String titulo, String mensagem) {
        new AlertDialog.Builder(this)
                .setTitle(titulo)
                .setMessage(mensagem)
                .setPositiveButton("OK", null)
                .show();
    }
}