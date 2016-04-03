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

import ifpi.edu.br.saudeacomp.dao.ExameDAO;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.RemedioDAO;
import ifpi.edu.br.saudeacomp.modelo.Exame;
import ifpi.edu.br.saudeacomp.modelo.Remedio;

public class ListaRemedioActivity extends AppCompatActivity {

    private Remedio remedio;
    private PacienteDAO ass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_remedio);

        ass = new PacienteDAO(this);

        final ListView listRemedio = (ListView) findViewById(R.id.list_remedios);
        registerForContextMenu(listRemedio);

        listRemedio.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                remedio = (Remedio) parent.getItemAtPosition(position);
                return false;
            }
        });
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem item1 = menu.add("Remover Exame");

        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AlertDialog.Builder c = new AlertDialog.Builder(ListaRemedioActivity.this);
                ass = new PacienteDAO(ListaRemedioActivity.this);
                c.setMessage("Deseja Apagar este remedio?");
                c.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RemedioDAO dao = new RemedioDAO(ass);
                        dao.remover(remedio);
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
