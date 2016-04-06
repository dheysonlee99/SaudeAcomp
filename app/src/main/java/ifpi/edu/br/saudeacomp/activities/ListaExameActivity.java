package ifpi.edu.br.saudeacomp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
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
import ifpi.edu.br.saudeacomp.dao.ExameDAO;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.modelo.Exame;
import ifpi.edu.br.saudeacomp.modelo.Paciente;

public class ListaExameActivity extends AppCompatActivity {

    private Exame exame;
    private DBHelper db;
    int paciente_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exame);

        db = new DBHelper(this);

        paciente_id = getIntent().getIntExtra("paciente_id", 0);
        Toast.makeText(ListaExameActivity.this, "ID Recebido: " + paciente_id, Toast.LENGTH_SHORT).show();

        final ListView listExame = (ListView) findViewById(R.id.list_exames);
        registerForContextMenu(listExame);

        listExame.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                exame = (Exame) parent.getItemAtPosition(position);
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
        ListView listExames = (ListView)findViewById(R.id.list_exames);


        ExameDAO dao = new ExameDAO(db);

        Paciente paciente = new Paciente();
        paciente.setId(paciente_id);
        //List<Exame> exames  = dao.listar();
        List<Exame> exames = dao.examesPorPaciente(paciente);
        ArrayAdapter<Exame> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exames);

        listExames.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(exame.getStatus().equals("Nao Realizado")) {
            MenuItem mudarStatus = menu.add("Marcar como Realizado e dar resultado");

            mudarStatus.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    Intent mudarStatus = new Intent(ListaExameActivity.this, ExameStatusActivity.class);
                    mudarStatus.putExtra("exame_id", exame.getId());
                    startActivity(mudarStatus);
                    return false;
                }
            });
        }

        MenuItem removerExame = menu.add("Remover Exame");


        removerExame.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AlertDialog.Builder c = new AlertDialog.Builder(ListaExameActivity.this);
                db = new DBHelper(ListaExameActivity.this);
                c.setMessage("Deseja Apagar este exame?");
                c.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExameDAO dao = new ExameDAO(db);
                        dao.remover(exame);
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
