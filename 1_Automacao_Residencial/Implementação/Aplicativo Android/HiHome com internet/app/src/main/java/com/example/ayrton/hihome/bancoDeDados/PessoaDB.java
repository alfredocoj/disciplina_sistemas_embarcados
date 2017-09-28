package com.example.ayrton.hihome.bancoDeDados;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import com.example.ayrton.hihome.MainActivity_Login;

import java.util.ArrayList;

public class PessoaDB {
    bancodeDados banco;
    SQLiteDatabase db;

    public PessoaDB(Context context){
        banco = new bancodeDados(context);
    }

    public boolean inserir(String pin, String login, String senha){

        db = banco.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(bancodeDados.COLUMN_PIN, pin);
        valores.put(bancodeDados.COLUMN_LOGIN, login);
        valores.put(bancodeDados.COLUMN_SENHA, senha);

        long idInserido = db.insert(bancodeDados.TABLE_NAME, null, valores);
        db.close();

        if(idInserido > 0){
            return true;
        } else {
            return false;
        }

    }

    public ArrayList<clienteBanco> select(String login, String senha) {

        db = banco.getReadableDatabase();

        String[] colunas = {
                bancodeDados.COLUMN_ID,
                bancodeDados.COLUMN_PIN,
                bancodeDados.COLUMN_LOGIN,
                bancodeDados.COLUMN_SENHA
        };

        String whereColunas = bancodeDados.COLUMN_LOGIN+" =? and "+bancodeDados.COLUMN_SENHA+" =?";

        String[] whereValores = {login, senha};

        Cursor cursor = db.query(
                bancodeDados.TABLE_NAME,    //Nome da tabela
                colunas,                    //Colunas a serem pesquisadas
                whereColunas,               //Colunas a serem utilizadas no where
                whereValores,               //Valores das colunas no where
                null,
                null,
                null
        );

        ArrayList<clienteBanco> resultQuery = new ArrayList<>();
//        clienteBanco cliente = new clienteBanco();
//
//        if(!cursor.moveToFirst()) {
//            return null;
//        } else {
//            cursor.moveToFirst();
//            cliente.setPin(cursor.getString(0));
//            cliente.setLogin(cursor.getString(1));
//            cliente.setSenha(cursor.getString(2));
//            resultQuery.add(cliente);
//        }

        cursor.close();
        db.close();

        return resultQuery;
    }


}
