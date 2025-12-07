package com.example.daily_pet;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "habitos_db";
    private static final String TABELA_NAME = "habitos";
    private static DatabaseHelper sInstance;

    private static final String COL_1 = "id";
    private static final String COL_2 = "nome";
    private static final String COL_3 = "data_criacao";
    private static final String COL_4 = "dias_streak";
    private static final String COL_5 = "nome_pet";
    private static final String COL_6 = "objetivo";

    public static  synchronized DatabaseHelper getInstance(Context context){

        if (sInstance == null){
            sInstance = new DatabaseHelper(context.getApplicationContext());
        }

        return sInstance;
    }
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABELA_NAME + "(id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT, data_criacao TEXT, dias_streak INTEGER, nome_pet String, objetivo String )");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABELA_NAME);
        onCreate(db);
    }

    public boolean insertData(String nome, String data_criacao, String dias_streak, String nome_pet, Integer objetivo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COL_2,nome);
        values.put(COL_3, data_criacao);
        values.put(COL_4, dias_streak);
        values.put(COL_5, nome_pet);
        values.put(COL_6, objetivo);


        long var = db.insert(TABELA_NAME, null, values);

        if (var == -1){
            return false;
        }

        return true;

    }

    public Cursor getAll(){
        SQLiteDatabase db = this.getReadableDatabase();
        var query = db.query(TABELA_NAME,null,null,null,null,null,null);

        return query;

    }

    public Cursor getOne(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        var query = db.query(TABELA_NAME, null,"id = ?",new String[]{id},null,null,null);

        return query;
    }

    public boolean updateOne(String id, String campo, String valor) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(campo, valor);

        // db.update returns the number of rows affected
        int count = db.update(
                TABELA_NAME,         // table
                valores,             // new values
                "id = ?",            // where clause
                new String[]{ id }   // where arguments as array
        );

        return count > 0; // true if at least one row was updated
    }

    public boolean deleteOne(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        int result = db.delete(TABELA_NAME, "id = ?", new String[]{id});
        return result > 0;
    }
}
