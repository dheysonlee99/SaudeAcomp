package ifpi.edu.br.saudeacomp.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ifpi.edu.br.saudeacomp.modelo.Paciente;
import ifpi.edu.br.saudeacomp.modelo.Remedio;

/**
 * Created by programador on 31/03/16.
 */
public class RemedioDAO {

    private DBHelper helper;

    public RemedioDAO(DBHelper helper) {
        this.helper = helper;
    }
    public void inserirRemedio(Remedio remedio, Paciente paciente){
        ContentValues cv = new ContentValues();
        cv.put("nome", remedio.getNome());
        cv.put("paciente_id",paciente.getId());
        cv.put("modoUso", remedio.getModoUso());

        this.helper.getWritableDatabase().insert("Remedio", null, cv);
    }

    public List<Remedio> listar() {
        List<Remedio> remedios = new ArrayList<>();
        String sql = "SELECT * FROM Remedio;";
        Cursor c = this.helper.getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            int pid = c.getInt(c.getColumnIndex("paciente_id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String modoUso = c.getString(c.getColumnIndex("modoUso"));
            Remedio remedio = new Remedio(nome,modoUso);
            remedio.setId(id);
            remedio.setPacienteid(pid);
            remedios.add(remedio);
        }

        return remedios;
    }

    public List<Remedio> remediosPorPaciente(Paciente p) {
        List<Remedio> remedios = new ArrayList<>();
        String[] args = {String.valueOf(p.getId())};
        String sql = "SELECT * FROM Remedio WHERE paciente_id = ?;";
        Cursor c = this.helper.getReadableDatabase().rawQuery(sql, args);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            int pid = c.getInt(c.getColumnIndex("paciente_id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String modoUso = c.getString(c.getColumnIndex("modoUso"));
            Remedio remedio = new Remedio(nome,modoUso);
            remedio.setId(id);
            remedio.setPacienteid(pid);
            remedios.add(remedio);
        }

        return remedios;
    }

    public void remover(Remedio remedio){

        String[] args = {String.valueOf(remedio.getId())};
        this.helper.getWritableDatabase().delete("Remedio", "id = ?", args);
    }



}
