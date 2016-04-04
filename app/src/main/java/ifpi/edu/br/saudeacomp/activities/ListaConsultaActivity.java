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

import java.util.ArrayList;
import java.util.List;

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.ConsultaDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Paciente;


public class ListaConsultaActivity extends AppCompatActivity {

    private Consulta consulta;
    private DBHelper db;
    int paciente_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_consulta);

        paciente_id = getIntent().getIntExtra("paciente_id", 0);

        db = new DBHelper(this);

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

        Toast.makeText(ListaConsultaActivity.this, "foi", Toast.LENGTH_SHORT).show();

        ConsultaDAO dao = new ConsultaDAO(db);

        //List<Consulta> consultas = dao.listar();
        Paciente paciente = new Paciente();
        paciente.setId(paciente_id);

        List<Consulta> consultas = dao.consultasPorPaciente(paciente); //dao.listar();

        ArrayAdapter<Consulta> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, consultas);

        listConsultas.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem item1 = menu.add("Mudar status");
        MenuItem item2 = menu.add("Remover Consulta");


        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AlertDialog.Builder c = new AlertDialog.Builder(ListaConsultaActivity.this);
                db = new DBHelper(ListaConsultaActivity.this);
                c.setMessage("Deseja Apagar esta consulta?");
                c.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ConsultaDAO dao = new ConsultaDAO(db);
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
