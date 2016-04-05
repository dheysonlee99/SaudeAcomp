package ifpi.edu.br.saudeacomp.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Exame;
import ifpi.edu.br.saudeacomp.modelo.Paciente;

/**
 * Created by programador on 03/04/16.
 */
public class ExameDAO {

    private DBHelper helper;

    public ExameDAO(DBHelper helper) {
        this.helper = helper;
    }

    public void inserirExame(Exame exame, Paciente paciente){
        ContentValues cv = new ContentValues();
        cv.put("nome", exame.getNomeLocal());
        cv.put("data", exame.getData());
        cv.put("paciente_id", paciente.getId());
        cv.put("tipo",exame.getTipo());
        cv.put("Status",exame.getStatus());

        this.helper.getWritableDatabase().insert("Exame", null, cv);
    }

    public List<Exame> listar(){
        List<Exame> exames = new ArrayList<>();
        String sql = "SELECT * FROM Exame;";
        Cursor c = this.helper.getWritableDatabase().rawQuery(sql,null);
        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            int pid = c.getInt((c.getColumnIndex("paciente_id")));
            String nome = c.getString(c.getColumnIndex("nome"));
            String data = c.getString(c.getColumnIndex("data"));
            String tipo = c.getString(c.getColumnIndex("tipo"));
            String status = c.getString(c.getColumnIndex("status"));
            Exame exame = new Exame(nome, data,tipo, status);
            exame.setId(id);
            exame.setPacienteid(pid);
            exames.add(exame);
        }

        return exames;
    }

    public void remover(Exame exame){
        String[] args = {String.valueOf(exame.getId())};
        this.helper.getWritableDatabase().delete("Exame", "id = ?", args);
    }

    /*
    public void atualizar(Exame exame){
        String[] args = {String.valueOf(exame.getId())};
        ContentValues cv = new ContentValues();
        cv.put("status",exame.getResultado());
        cv.put("resultado",exame.getStatus());
        ass.getWritableDatabase().update("Exame",cv, "id = ?", args);
    }*/

}
