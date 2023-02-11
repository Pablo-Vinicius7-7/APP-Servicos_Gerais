package com.project.generalservices.helper;


import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.project.generalservices.dao.Servico;
import com.project.generalservices.dao.ServicoSolicitado;
import com.project.generalservices.dao.Usuario;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {


    public DBHelper(Context context) {
        super(context, "app_servicos_gerais.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlUsuario = "create table usuario (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,nome Text,email Text, senha Text, tipo Int)";
        String sqlEndereco = "create table endereco (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,usuario_id INTEGER,cep Text, cidade Text, bairro Text, rua Text, numero Text)";
        String sqlServico = "create table servico (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, usuario_id INTEGER, nome Text, descricao Text, valor Text)";
        String sqlServicoSolicitado = "create table servico_solicitado (id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,usuario_cliente_id INTEGER, usuario_fornecedor_id INTEGER, " +
                                      "nome_servico Text,nome_cliente Text, endereco Text)";

        db.execSQL(sqlUsuario);
        db.execSQL(sqlEndereco);
        db.execSQL(sqlServico);
        db.execSQL(sqlServicoSolicitado);
        Log.d("Banco de dados", "onCreate: OK");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String sqlUsuario = "drop table if exists usuario";
        String sqlEndereco = "drop table if exists endereco";
        String sqlServico = "drop table if exists servico";
        String sqlServicoSolicitado = "drop table if exists servico_solicitado";

        db.execSQL(sqlUsuario);
        db.execSQL(sqlEndereco);
        db.execSQL(sqlServico);
        db.execSQL(sqlServicoSolicitado);
    }

    //Funções Usuario
    public boolean cadastrarUsuario(String nome,String email, String senha, int tipo) {

        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("nome", nome);
        cv.put("email", email);
        cv.put("senha", senha);
        cv.put("tipo", tipo);

        long result = myDB.insert("usuario", null, cv);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Integer getUsuarioIdByEmail (String email) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select id from usuario where email = ?", new String[]{email});
        c.moveToFirst();
        return c.getInt(0);
    }

    public Integer getTipoUsuarioByEmail (String email) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select tipo from usuario where email = ?", new String[]{email});
        c.moveToFirst();
        return c.getInt(0);
    }

    public boolean usuarioExistente(String email) {

        SQLiteDatabase myDB = this.getReadableDatabase();

        Cursor c = myDB.rawQuery("select * from usuario where email = ?", new String[]{email});
        if (c.getCount() > 0) {
            return true;
        }
        return false;
    }

    public boolean validarLoginSenha(String email, String senha) {

        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select * from usuario where email = ? AND senha = ?", new String[]{email, senha});

        if (c.getCount() > 0) {
            return true;
        }

        return false;
    }

    public Usuario obterUsuarioPorEmail(String email) {

        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select * from usuario where email = ?", new String[]{email});
        if (c.moveToFirst()) {
            do {
                return new Usuario(c.getString(0),
                        c.getString(1),
                        c.getString(2),
                        c.getInt(3));
            } while (c.moveToNext());
        }

        return null;
    }

    //Funcoes endereco
    public boolean cadastrarEndereco (Integer usuario_id,String cep, String cidade, String bairro, String rua, String numero){
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("usuario_id",usuario_id);
        cv.put("cep",cep);
        cv.put("cidade", cidade);
        cv.put("bairro", bairro);
        cv.put("rua",rua);
        cv.put("numero",numero);

        long result = myDB.insert("endereco", null, cv);

        if (result == -1) {
            return false;
        }
        return true;
    }

    public boolean editarEndereco(int id, String cep, String cidade, String bairro, String rua, String numero) {
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("cep", cep);
        cv.put("cidade", cidade);
        cv.put("bairro", bairro);
        cv.put("rua", rua);
        cv.put("numero", numero);

        return myDB.update("endereco", cv, "id=?", new String[]{id + ""}) > 0;
    }

    public String getCep(int usuario_id) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select cep from endereco where usuario_id = ?", new String[]{usuario_id + ""});
        c.moveToFirst();
        return c.getString(0);
    }

    public String getCidade(int usuario_id) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select cidade from endereco where usuario_id = ?", new String[]{usuario_id + ""});
        c.moveToFirst();
        return c.getString(0);
    }

    public String getBairro(int usuario_id) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select bairro from endereco where usuario_id = ?", new String[]{usuario_id + ""});
        c.moveToFirst();
        return c.getString(0);
    }

    public String getRua(int usuario_id) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select rua from endereco where usuario_id = ?", new String[]{usuario_id + ""});
        c.moveToFirst();
        return c.getString(0);
    }

    public String getNumero(int usuario_id) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select numero from endereco where usuario_id = ?", new String[]{usuario_id + ""});
        c.moveToFirst();
        return c.getString(0);
    }

    public Integer getEnderecoIdByUsuarioId(Integer usuario_id) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        Cursor c = myDB.rawQuery("select id from endereco where usuario_id = ?", new String[]{usuario_id + ""});
        c.moveToFirst();
        return c.getInt(0);
    }

    //Funcoes servicos
    public boolean cadastrarServico (Integer usuario_id,String nome, String descricao, String valor){
        SQLiteDatabase myDB = this.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("usuario_id",usuario_id);
        cv.put("nome",nome);
        cv.put("descricao", descricao);
        cv.put("valor", valor);

        long result = myDB.insert("servico", null, cv);

        if (result == -1) {
            return false;
        }
        return true;
    }

    public List<Servico> getAllServicos() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        List<Servico> servicos = new ArrayList<>();
        Cursor cursor = myDB.rawQuery("SELECT * FROM servico", null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int usuario_id = cursor.getInt(1);
            String nome = cursor.getString(2);
            String descricao = cursor.getString(3);
            String valor = cursor.getString(4);
            servicos.add(new Servico(id,nome,descricao,valor));
        }
        cursor.close();
        return servicos;
    }


    //Funcoes servicos solicitados

    public List<ServicoSolicitado> getAllServicosSolicitados() {
        SQLiteDatabase myDB = this.getReadableDatabase();
        List<ServicoSolicitado> servicoSolicitados = new ArrayList<>();
        Cursor cursor = myDB.rawQuery("SELECT * FROM servico_solicitado",null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int usuario_cliente_id = cursor.getInt(1);
            int usuario_fornecedor_id = cursor.getInt(2);
            String nome_servico = cursor.getString(3);
            String nome_cliente = cursor.getString(4);
            String endereco = cursor.getString(5);
            servicoSolicitados.add(new ServicoSolicitado(id,usuario_cliente_id,usuario_fornecedor_id,nome_servico,nome_cliente,endereco));
        }
        cursor.close();
        return servicoSolicitados;
    }

    public List<ServicoSolicitado> getServicosSolicitadosById(Integer usuario_fornecedor_id) {
        SQLiteDatabase myDB = this.getReadableDatabase();
        List<ServicoSolicitado> servicoSolicitados = new ArrayList<>();
        Cursor cursor = myDB.rawQuery("SELECT * FROM servico_solicitado WHERE usuario_fornecedor_id = ?",new String[]{usuario_fornecedor_id + ""});
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            int usuario_cliente_id = cursor.getInt(1);
            String nome_servico = cursor.getString(3);
            String nome_cliente = cursor.getString(4);
            String endereco = cursor.getString(5);
            servicoSolicitados.add(new ServicoSolicitado(id,usuario_cliente_id,usuario_fornecedor_id,nome_servico,nome_cliente,endereco));
        }
        cursor.close();
        return servicoSolicitados;
    }
}
