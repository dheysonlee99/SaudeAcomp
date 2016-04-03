package ifpi.edu.br.saudeacomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.ConsultaDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;

public class ListaConsultaActivity extends AppCompatActivity {


    private PacienteDAO ass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_consulta);

        ass = new PacienteDAO(this);
    }


    @Override
    protected void onResume() {
        super.onResume();

        recarregarDados();

    }

    private void recarregarDados() {
        ListView listConsultas = (ListView) findViewById(R.id.list_consulta);
        ConsultaDAO dao = new ConsultaDAO(ass);
        List<Consulta> consultas = dao.listar();

        ArrayAdapter<Consulta> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, consultas);

        listConsultas.setAdapter(adapter);
    }

}
