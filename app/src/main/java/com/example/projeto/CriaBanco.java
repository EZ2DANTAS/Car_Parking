package com.example.projeto;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CriaBanco extends SQLiteOpenHelper {

    public CriaBanco(Context context) {
        super(context, "Estacionamento.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE usuarios("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nome TEXT NOT NULL, "+
                "email TEXT NOT NULL, "+
                "senha TEXT NOT NULL, "+
                "cpf TEXT NOT NULL, "+
                "rg TEXT NOT NULL, "+
                "endereco TEXT NOT NULL,"+
                "telefone TEXT NOT NULL);";

        String sqlCarros = "CREATE TABLE carros (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "placa TEXT NOT NULL, " +
                "modelo TEXT NOT NULL, " +
                "cor TEXT NOT NULL, " +
                "id_usuario INTEGER, " +
                "FOREIGN KEY(id_usuario) REFERENCES usuarios(id));";

        sqLiteDatabase.execSQL(sql);
        sqLiteDatabase.execSQL(sqlCarros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int antigaVersao, int novaVersao) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS carros");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS usuarios");
        onCreate(sqLiteDatabase);
    }
}
