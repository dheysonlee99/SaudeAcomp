package ifpi.edu.br.saudeacomp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import ifpi.edu.br.saudeacomp.modelo.Paciente;

import ifpi.edu.br.saudeacomp.dao.PacienteDAO;

public class SaudeActivity extends AppCompatActivity {

    private Paciente paciente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saude);


        final ListView listPacientes = (ListView) findViewById(R.id.list_pacientes);
        registerForContextMenu(listPacientes);

        listPacientes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(SaudeActivity.this, "Clicou no item " + position, Toast.LENGTH_SHORT).show();

                paciente = (Paciente) parent.getItemAtPosition(position);

            }
        });

        listPacientes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(SaudeActivity.this, "Clico longo em " + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
                paciente = (Paciente) parent.getItemAtPosition(position);
                return false;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_lista, menu);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.cadastre_se) {
            //Toast.makeText(SaudeActivity.this, "Novo Paciente", Toast.LENGTH_SHORT).show();
            Intent irParaForm = new Intent(this, CadastroActivity.class);
            startActivity(irParaForm);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();

        recarregarDados();

    }


    private void recarregarDados() {
        ListView listPacientes = (ListView) findViewById(R.id.list_pacientes);
        PacienteDAO dao = new PacienteDAO(this);
        List<Paciente> pacientes = dao.lista();

        ArrayAdapter<Paciente> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pacientes);

        listPacientes.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem item = menu.add("Agendar Consulta");
        MenuItem item2 = menu.add("Agendar Exame");
        MenuItem item3 = menu.add("Adicionar Remedio");
        MenuItem item4 = menu.add("Ver Consultas");
        MenuItem item5 = menu.add("Ver Exames");
        MenuItem item6 = menu.add("Ver Remédios");
        MenuItem item7 = menu.add("Apagar Paciente");


        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou no agendar consulta:", Toast.LENGTH_SHORT).show();
                Intent irParaConsult = new Intent(SaudeActivity.this, ConsultaActivity.class);
                startActivity(irParaConsult);
                return false;
            }
        });

        item2.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou no agendar exame:", Toast.LENGTH_SHORT).show();
                Intent irParaExame = new Intent(SaudeActivity.this, ExameActivity.class);
                startActivity(irParaExame);
                return false;
            }
        });

        item3.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou em adicionar remedio:", Toast.LENGTH_SHORT).show();
                Intent irParaRemedio = new Intent(SaudeActivity.this, RemedioActivity.class);
                startActivity(irParaRemedio);
                return false;
            }
        });

        item4.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent irListaConsultas = new Intent(SaudeActivity.this, ListaConsultaActivity.class);
                startActivity(irListaConsultas);
                return false;
            }
        });

        item5.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent irListaExame = new Intent(SaudeActivity.this, ListaExameActivity.class);
                startActivity(irListaExame);
                return false;
            }
        });

        item6.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou na listar Remedios", Toast.LENGTH_SHORT).show();
                Intent irListaRemedios = new Intent(SaudeActivity.this, ListaRemedioActivity.class);
                startActivity(irListaRemedios);
                return false;
            }
        });




        item7.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlertDialog.Builder b = new AlertDialog.Builder(SaudeActivity.this);
                b.setMessage("Deseja apagar o paciente?");
                b.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PacienteDAO dao = new PacienteDAO(SaudeActivity.this);
                        dao.remover(paciente);
                        recarregarDados();
                        Toast.makeText(SaudeActivity.this, "Paciente removido com sucesso", Toast.LENGTH_SHORT).show();
                    }
                });
                b.setNegativeButton("NÃO", null);
                b.show();

                return false;
            }


        });
    }




}
