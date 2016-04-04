package ifpi.edu.br.saudeacomp.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Paciente;

/**
 * Created by programador on 30/03/16.
 */

public class ConsultaDAO  {

    private DBHelper helper;

    public ConsultaDAO(DBHelper helper) {
        this.helper = helper;
    }

    public void inserirConsulta(Consulta consulta, Paciente paciente){
        ContentValues cv = new ContentValues();
        cv.put("nome", consulta.getNome());
        cv.put("data", consulta.getData());
        cv.put("paciente_id",paciente.getId());
        cv.put("especialidade", consulta.getEspecialidade());
        cv.put("status", consulta.getStatus());

        this.helper.getWritableDatabase().insert("Consulta", null, cv);
    }

    public List<Consulta> listar() {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta;";
        Cursor c = this.helper.getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            int pid = c.getInt((c.getColumnIndex("paciente_id")));
            String nome = c.getString(c.getColumnIndex("nome"));
            String data = c.getString(c.getColumnIndex("data"));
            String especialidade = c.getString(c.getColumnIndex("especialidade"));
            String status = c.getString(c.getColumnIndex("status"));
            Consulta consulta = new Consulta(nome, data,especialidade, status);
            consulta.setId(id);
            consulta.setPacienteid(pid);

            consultas.add(consulta);
        }

        return consultas;
    }

    public void remover(Consulta consulta){
        String[] args = {String.valueOf(consulta.getId())};
        this.helper.getWritableDatabase().delete("Consulta", "id = ?", args);
    }

    public List<Consulta> consultasPorPaciente(Paciente p) {
        List<Consulta> consultas = new ArrayList<>();
        String sql = "SELECT * FROM Consulta WHERE paciente_id = ?;";
        String[] args = {String.valueOf(p.getId())};
        Cursor c = this.helper.getReadableDatabase().rawQuery(sql, args);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String data = c.getString(c.getColumnIndex("data"));
            String especialidade = c.getString(c.getColumnIndex("especialidade"));
            String status = c.getString(c.getColumnIndex("status"));
            Consulta consulta = new Consulta(nome, data,especialidade, status);
            consulta.setId(id);
            consultas.add(consulta);
        }

        return consultas;
    }

    /*public void atualizarStatus(Consulta consulta){
        ContentValues cv = new ContentValues();
        cv.put("status",consulta.getStatus());
        String[] args = {String.valueOf(consulta.getId())};
        ass.getWritableDatabase().update("Consulta",cv, "id = ?", args);
    }*/

}
