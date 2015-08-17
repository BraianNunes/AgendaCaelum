package br.com.caelum.cadastro.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.cadastro.modelo.Aluno;

/**
 * Created by Braian Nunes on 8/16/2015.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "CadastroCaelum";
    private static final int VERSION = 2;

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSION);
    }

    public void salva(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("telefone", aluno.getTelefone());
        values.put("endereco", aluno.getEndereco());
        values.put("site", aluno.getSite());
        values.put("foto", aluno.getFoto());
        values.put("nota", aluno.getNota());

        getWritableDatabase().insert("Alunos", null, values);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE Alunos (id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT UNIQUE NOT NULL," +
                "telefone TEXT," +
                "endereco TEXT," +
                "site TEXT, " +
                "foto TEXT," +
                "nota REAL);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String ddl = "DROP TABLE IF EXISTS Alunos";
        db.execSQL(ddl);

        this.onCreate(db);
    }

    public List<Aluno> getLista() {
        String[] colunas = new String[]{"id", "nome", "site", "telefone", "endereco", "foto", "nota"};
        Cursor cursor = getWritableDatabase().query("Alunos", colunas, null, null, null, null, null);
        List<Aluno> listaAlunos = new ArrayList<>();


        while (cursor.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(cursor.getInt(0));
            aluno.setNome(cursor.getString(1));
            aluno.setSite(cursor.getString(1));
            aluno.setTelefone(cursor.getString(1));
            aluno.setEndereco(cursor.getString(1));
            aluno.setFoto(cursor.getString(1));
            aluno.setNota(cursor.getDouble(1));

            listaAlunos.add(aluno);
        }

        return listaAlunos;
    }

    public void deletar(Aluno aluno) {
        String[] args = new String[]{aluno.getId().toString()};
        getWritableDatabase().delete("Alunos", "id=?", args);
    }
}
