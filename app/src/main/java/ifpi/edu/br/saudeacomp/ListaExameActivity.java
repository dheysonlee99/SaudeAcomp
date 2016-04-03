package ifpi.edu.br.saudeacomp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.ExameDAO;
import ifpi.edu.br.saudeacomp.modelo.Exame;

public class ListaExameActivity extends AppCompatActivity {

    private PacienteDAO ass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exame);

        ass = new PacienteDAO(this);
    }


    @Override
    protected void onResume() {
        super.onResume();
        recarregarDados();
    }


    public void recarregarDados(){
        ListView listExames = (ListView)findViewById(R.id.list_exames);
        ExameDAO dao = new ExameDAO(ass);
        List<Exame> exames = dao.listar();

        ArrayAdapter<Exame> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exames);

        listExames.setAdapter(adapter);
    }


}
