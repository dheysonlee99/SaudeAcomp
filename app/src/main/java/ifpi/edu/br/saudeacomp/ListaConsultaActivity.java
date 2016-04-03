package ifpi.edu.br.saudeacomp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.ConsultaDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;


public class ListaConsultaActivity extends AppCompatActivity {

    private Consulta consulta;
    private PacienteDAO ass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_consulta);

        ass = new PacienteDAO(this);

        final ListView listConsulta = (ListView) findViewById(R.id.list_consulta);
        registerForContextMenu(listConsulta);

        listConsulta.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SaudeActivity.this, "Clico longo em " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                consulta = (Consulta) parent.getItemAtPosition(position);
                return false;
            }
        });
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem item1 = menu.add("Remover Consulta");

        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AlertDialog.Builder c = new AlertDialog.Builder(ListaConsultaActivity.this);
                ass = new PacienteDAO(ListaConsultaActivity.this);
                c.setMessage("Deseja Apagar esta consulta?");
                c.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConsultaDAO dao = new ConsultaDAO(ass);
                        dao.remover(consulta);
                        recarregarDados();
                    }
                });
                c.setNegativeButton("NÃO", null);
                c.show();
                return false;
            }
        });
    }
}
