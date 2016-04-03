package ifpi.edu.br.saudeacomp.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import ifpi.edu.br.saudeacomp.modelo.Remedio;

/**
 * Created by programador on 31/03/16.
 */
public class RemedioDAO {

    private PacienteDAO ass;

    public RemedioDAO(PacienteDAO ass) {
        this.ass = ass;
    }

    public void inserirRemedio(Remedio remedio){
        ContentValues cv = new ContentValues();
        cv.put("nome", remedio.getNome());
        cv.put("modoUso", remedio.getModoUso());

        ass.getWritableDatabase().insert("Remedio", null, cv);
    }

    public List<Remedio> listar() {
        List<Remedio> remedios = new ArrayList<>();
        String sql = "SELECT * FROM Remedio;";
        Cursor c = ass.getReadableDatabase().rawQuery(sql, null);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String modoUso = c.getString(c.getColumnIndex("modoUso"));
            Remedio r = new Remedio(nome,modoUso);
            r.setId(id);
            remedios.add(r);
        }

        return remedios;
    }



    public void remover(Remedio remedio){

        String[] args = {String.valueOf(remedio.getId())};
        ass.getWritableDatabase().delete("Remedio", "id = ?", args);
    }



}
