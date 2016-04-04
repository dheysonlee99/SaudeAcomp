package ifpi.edu.br.saudeacomp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import ifpi.edu.br.saudeacomp.modelo.Paciente;

/**
 * Created by programador on 29/03/16.
 */
public class PacienteDAO {

    private DBHelper helper;

    public PacienteDAO(DBHelper helper) {
        this.helper = helper;
    }


    public void inserir(Paciente paciente) {
        ContentValues cv = new ContentValues();
        cv.put("nome", paciente.getNome());
        cv.put("numeroSus", paciente.getSusNumero());
        cv.put("idade", paciente.getIdade());
        cv.put("sexo", paciente.getSexo());

        this.helper.getWritableDatabase().insert("Paciente", null, cv);

    }

    public List<Paciente> lista() {
        List<Paciente> pacientes = new ArrayList<>();
        String sql = "SELECT * FROM Paciente;";
        Cursor c = this.helper.getReadableDatabase().rawQuery(sql, null);
        ConsultaDAO consultaDAO = new ConsultaDAO(this.helper);

        while (c.moveToNext()) {
            int id = c.getInt(c.getColumnIndex("id"));
            String nome = c.getString(c.getColumnIndex("nome"));
            String sus = c.getString(c.getColumnIndex("numeroSus"));
            String idade = c.getString(c.getColumnIndex("idade"));
            String sexo = c.getString(c.getColumnIndex("sexo"));
            Paciente p = new Paciente(nome, sus,idade, sexo);
            p.setId(id);

            //Pegar as consultas deste paciente
            p.setConsultas(consultaDAO.consultasPorPaciente(p));


            pacientes.add(p);
        }

        return pacientes;
    }

    public void remover(Paciente paciente) {

        String[] args = {String.valueOf(paciente.getId())};
        this.helper.getWritableDatabase().delete("Paciente", "id = ?", args);
    }
}