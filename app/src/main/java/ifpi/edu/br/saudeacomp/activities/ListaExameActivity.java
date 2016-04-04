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

import java.util.List;


import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.ExameDAO;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.modelo.Exame;

public class ListaExameActivity extends AppCompatActivity {

    private Exame exame;
    private PacienteDAO ass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_exame);

        ass = new PacienteDAO(this);

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
        ExameDAO dao = new ExameDAO(ass);
        List<Exame> exames  = dao.listar();
        ArrayAdapter<Exame> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, exames);

        listExames.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem item1 = menu.add("Dar resultado");
        MenuItem item2 = menu.add("Remover Exame");

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {


                AlertDialog.Builder c = new AlertDialog.Builder(ListaExameActivity.this);
                ass = new PacienteDAO(ListaExameActivity.this);
                c.setMessage("Deseja Apagar este exame?");
                c.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ExameDAO dao = new ExameDAO(ass);
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
