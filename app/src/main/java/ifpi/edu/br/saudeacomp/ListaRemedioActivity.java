package ifpi.edu.br.saudeacomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.RemedioDAO;
import ifpi.edu.br.saudeacomp.modelo.Remedio;

public class ListaRemedioActivity extends AppCompatActivity {

    private PacienteDAO ass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_remedio);

        ass = new PacienteDAO(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        recarregarDados();
    }

    public void recarregarDados(){
        ListView listRemedios = (ListView)findViewById(R.id.list_remedios);
        RemedioDAO dao = new RemedioDAO(ass);
        List<Remedio> remedios = dao.listar();

        ArrayAdapter<Remedio> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, remedios);

        listRemedios.setAdapter(adapter);
    }

}
