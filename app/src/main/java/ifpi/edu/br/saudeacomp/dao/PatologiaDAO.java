package ifpi.edu.br.saudeacomp.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Paciente;
import ifpi.edu.br.saudeacomp.modelo.Patologia;


/**
 * Created by programador on 05/04/16.
 */
public class PatologiaDAO {

    private DBHelper helper;

    public PatologiaDAO(DBHelper helper){
        this.helper = helper;
    }

    public void inserirPatologia(Patologia patologia, Paciente paciente){
        ContentValues cv = new ContentValues();
        cv.put("nome", patologia.getNome());
        cv.put("paciente_id",paciente.getId());

        this.helper.getWritableDatabase().insert("Patologia", null, cv);
    }

    public List<Patologia> patologiasPorPaciente(Paciente p) {
        List<Patologia> patologias = new ArrayList<>();
        String[] args = {String.valueOf(p.getId())};
        String sql = "SELECT * FROM Patologia WHERE paciente_id = ?;";
        Cursor c = this.helper.getReadableDatabase().rawQuery(sql, args);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            int paciente_id = c.getInt(c.getColumnIndex("paciente_id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            Patologia patologia = new Patologia(nome);
            patologia.setId(id);
            patologias.add(patologia);
        }

        return patologias;
    }

    public List<Patologia> listar() {
        List<Patologia> patologias = new ArrayList<>();
        String sql = "SELECT * FROM Patologia;";
        Cursor c = this.helper.getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            int pid = c.getInt((c.getColumnIndex("paciente_id")));
            String nome = c.getString(c.getColumnIndex("nome"));
            Patologia patologia = new Patologia(nome);
            patologia.setId(id);
            //patologia.setPacienteid(pid);

            patologias.add(patologia);
        }

        return patologias;
    }


}
