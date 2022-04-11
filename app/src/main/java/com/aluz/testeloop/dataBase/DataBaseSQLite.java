package com.aluz.testeloop.dataBase;

;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
//import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.aluz.testeloop.modle.Ranking;
import com.aluz.testeloop.modle.User;

import java.util.ArrayList;

public class DataBaseSQLite extends SQLiteOpenHelper {

    private static final String NAME_BANC = "Dados_Usuario.db";
    private static final String ID = "id";
    public static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    public static final String PONTUACAO = "pontuacao";
    public static final String NIVEL = "nivel";
    public ArrayList<Ranking> rankingList = new ArrayList<>();

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
                PONTUACAO + " TEXT," +
                NIVEL + " TEXT )";
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
        values.put(NIVEL, "0");

        result = db.insert(TABELA, null, values);
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public User validarLogin(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABELA, new String[]{LOGIN, PASSWORD, NIVEL}, LOGIN + " = ?",
                new String[]{String.valueOf(login)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            User user = new User(
                    cursor.getString(0),
                    cursor.getString(1),
                    cursor.getString(2));
            return (User) user.clone();
        } else {
            return null;
        }
    }

    public boolean atualizarPontos(User points) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(PONTUACAO, points.getPontuacao());

        result = db.update(TABELA, values, LOGIN + " = ?", new String[]{String.valueOf(points.getLogin())});
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }

    }

    public boolean atualizarNivel(User player) {
        long result;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NIVEL, player.getNivel());
        result = db.update(TABELA, values, LOGIN + " = ?", new String[]{String.valueOf(player.getLogin())});
        //result =  db.update(TABELA, values,LOGIN, new String[]{String.valueOf(login)});
        db.close();

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public User selecionarNivel(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABELA, new String[]{NIVEL}, LOGIN + " = ?",
                new String[]{String.valueOf(login)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            User user = new User(cursor.getString(0));
            return (User) user.clone();
        } else
            return null;

    }

    public User selecionarUser(String login) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(
                TABELA, new String[]{ID, LOGIN, PASSWORD, PONTUACAO, NIVEL}, LOGIN + " = ?",
                new String[]{String.valueOf(login)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            User user = new User(
                    Integer.parseInt(cursor.getString(0)),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(3),
                    cursor.getString(4));
            return (User) user.clone();
        } else
            return null;

    }

    public User selecionarUserPorID(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                TABELA,
                new String[]{ID, LOGIN, PASSWORD, PONTUACAO, NIVEL},
                ID + " = ?",
                new String[]{String.valueOf(id)}, null, null, null, null);
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

    public ArrayList<Ranking> rankingList() {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select LOGIN, PONTUACAO from Dados_Usuario order by PONTUACAO DESC", null);
        if(cursor != null){
            cursor.moveToFirst();
            Ranking list = new Ranking(
                    cursor.getString(0),
                    cursor.getString(1));

            rankingList.add(new Ranking(list.getLogin(),list.getPontuacao()));
            cursor.moveToNext();
            Ranking list1 = new Ranking(
                    cursor.getString(0),
                    cursor.getString(1));
            rankingList.add(new Ranking(list1.getLogin(),list1.getPontuacao()));
            cursor.moveToNext();
            Ranking list2 = new Ranking(
                    cursor.getString(0),
                    cursor.getString(1));
            rankingList.add(new Ranking(list2.getLogin(),list2.getPontuacao()));
            return rankingList;

        }else
            return null;
    }
}
