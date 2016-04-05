package ifpi.edu.br.saudeacomp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.ConsultaDAO;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.PatologiaDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Paciente;
import ifpi.edu.br.saudeacomp.modelo.Patologia;

public class ListaPatologiaActivity extends AppCompatActivity {

    private Patologia patologia;
    private DBHelper db;
    int paciente_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_patologia);


        paciente_id = getIntent().getIntExtra("paciente_id", 0);
        Toast.makeText(ListaPatologiaActivity.this, "ID Recebido: " + paciente_id, Toast.LENGTH_SHORT).show();

        db = new DBHelper(this);

        final ListView listConsulta = (ListView) findViewById(R.id.list_consulta);
    }


    @Override
    protected void onResume() {
        super.onResume();

        recarregarDados();

    }

    private void recarregarDados() {
        ListView listPatologias = (ListView) findViewById(R.id.list_consulta);

        Toast.makeText(ListaPatologiaActivity.this, "foi", Toast.LENGTH_SHORT).show();

        PatologiaDAO dao = new PatologiaDAO(db);

        //List<Patologia> patologias = dao.listar();
        Paciente paciente = new Paciente();
        paciente.setId(paciente_id);

        //List<Patologia> patologias = dao.patologiasPorPaciente(paciente); //dao.listar();

        //ArrayAdapter<Patologia> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, patologias);

        //listPatologias.setAdapter(adapter);
    }
}
