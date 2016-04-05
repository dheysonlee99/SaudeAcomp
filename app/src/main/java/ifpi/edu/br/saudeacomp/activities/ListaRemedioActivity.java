package ifpi.edu.br.saudeacomp.activities;

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
import android.widget.Toast;

import java.util.List;

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.RemedioDAO;
import ifpi.edu.br.saudeacomp.modelo.Paciente;
import ifpi.edu.br.saudeacomp.modelo.Remedio;

public class ListaRemedioActivity extends AppCompatActivity {

    private Remedio remedio;
    private DBHelper db;
    int paciente_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_remedio);

        db = new DBHelper(this);

        paciente_id = getIntent().getIntExtra("paciente_id", 0);
        Toast.makeText(ListaRemedioActivity.this, "ID Recebido: " + paciente_id, Toast.LENGTH_SHORT).show();

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
        RemedioDAO dao = new RemedioDAO(db);

        Paciente paciente = new Paciente();
        paciente.setId(paciente_id);
        //List<Remedio> remedios = dao.listar();

        List<Remedio> remedios = dao.remediosPorPaciente(paciente);
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
                db = new DBHelper(ListaRemedioActivity.this);
                c.setMessage("Deseja Apagar este remedio?");
                c.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RemedioDAO dao = new RemedioDAO(db);
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
