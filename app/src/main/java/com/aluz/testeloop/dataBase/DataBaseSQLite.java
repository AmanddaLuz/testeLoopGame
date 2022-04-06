package com.aluz.testeloop.dataBase;

;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.aluz.testeloop.modle.User;

public class DataBaseSQLite extends SQLiteOpenHelper {

    private static final String NAME_BANC = "Dados_Usuario.db";
    private static final String ID = "id";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PONTUACAO = "pontuacao";

    private static final String TABELA = "Dados_Usuario";
    private static final int VERSAO = 1;

    public DataBaseSQLite(Context context) {
        super(context, NAME_BANC, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USUARIO_TABLE = "CREATE TABLE " + TABELA + " ( " +
                ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                LOGIN + " TEXT," +
                PASSWORD + " TEXT," +
                PONTUACAO + " TEXT )";
        db.execSQL(CREATE_USUARIO_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA);
        onCreate(db);

    }

    public boolean inserirUsuario(User u) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(LOGIN, u.getLogin());
        values.put(PASSWORD, u.getPassword());
        values.put(PONTUACAO, "0");

        result = db.insert(TABELA, null, values);
        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }
    public boolean atualizarPontos(String idAtualiza, int novoPonto) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PONTUACAO, novoPonto);

        result = db.update(TABELA, values, ID,new String[]{String.valueOf(idAtualiza)});
        db.close();

        if (result == -1)
            return false;
        else
            return true;

    }

    public User selecionarUser(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABELA, new String[]{ID, LOGIN, PASSWORD, PONTUACAO}, LOGIN + " = ?",
                new String[]{String.valueOf(login)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            User user = new User(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            return (User) user.clone();
        } else
            return null;

    }
    public User selecionarUserPorID(String id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA,
                new String[]{ID, LOGIN, PASSWORD, PONTUACAO},
                ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);
        if(cursor != null){
            cursor.moveToFirst();
            User user = new User(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3));
            return (User) user.clone();
        }else
            return null;

    }
}
