package ifpi.edu.br.saudeacomp.activities;

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

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
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
                Intent verPatologias = new Intent(SaudeActivity.this,ListaPatologiaActivity.class);
                verPatologias.putExtra("paciente_id",paciente.getId());
                startActivity(verPatologias);

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
        DBHelper db = new DBHelper(this);
        PacienteDAO dao = new PacienteDAO(db);
        List<Paciente> pacientes = dao.lista();

        ArrayAdapter<Paciente> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, pacientes);

        listPacientes.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        MenuItem addPatologia = menu.add("Adicionar Patologia");
        MenuItem agendarConsulta = menu.add("Agendar Consulta");
        //colocando a responsabilidade
        //MenuItem agendarExame = menu.add("Agendar Exame");
        // colocando a responsabilidade de adicionar remedio para consulta realizada
        //MenuItem addRemedio = menu.add("Adicionar Remedio");
        MenuItem verconsultas = menu.add("Ver Consultas");
        MenuItem verExames = menu.add("Ver Exames");
        MenuItem verRemedios = menu.add("Ver Remédios");
        MenuItem apagarPaciente = menu.add("Apagar Paciente");

        addPatologia.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou no adicionar patologia:", Toast.LENGTH_SHORT).show();
                Intent addPatologia = new Intent(SaudeActivity.this, PatologiaActivity.class);
                addPatologia.putExtra("paciente_id", paciente.getId());
                startActivity(addPatologia);
                return false;
            }
        });
        agendarConsulta.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou no agendar consulta:", Toast.LENGTH_SHORT).show();
                Intent irParaConsult = new Intent(SaudeActivity.this, ConsultaActivity.class);
                irParaConsult.putExtra("paciente_id", paciente.getId());
                startActivity(irParaConsult);
                return false;
            }
        });

        /*agendarExame.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou no agendar exame:", Toast.LENGTH_SHORT).show();
                Intent irParaExame = new Intent(SaudeActivity.this, ExameActivity.class);
                irParaExame.putExtra("paciente_id",paciente.getId());
                startActivity(irParaExame);
                return false;
            }
        });*/

        /*addRemedio.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou em adicionar remedio:", Toast.LENGTH_SHORT).show();
                Intent irParaRemedio = new Intent(SaudeActivity.this, RemedioActivity.class);
                irParaRemedio.putExtra("paciente_id", paciente.getId());
                startActivity(irParaRemedio);
                return false;
            }
        });*/

        verconsultas.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent irListaConsultas = new Intent(SaudeActivity.this, ListaConsultaActivity.class);
                irListaConsultas.putExtra("paciente_id", paciente.getId());
                startActivity(irListaConsultas);
                return false;
            }
        });

        verExames.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent irListaExame = new Intent(SaudeActivity.this, ListaExameActivity.class);
                irListaExame.putExtra("paciente_id",paciente.getId());
                startActivity(irListaExame);
                return false;
            }
        });

        verRemedios.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Toast.makeText(SaudeActivity.this, "Vc clicou na listar Remedios", Toast.LENGTH_SHORT).show();
                Intent irListaRemedios = new Intent(SaudeActivity.this, ListaRemedioActivity.class);
                irListaRemedios.putExtra("paciente_id",paciente.getId());
                startActivity(irListaRemedios);
                return false;
            }
        });




        apagarPaciente.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {

            @Override
            public boolean onMenuItemClick(MenuItem item) {

                AlertDialog.Builder b = new AlertDialog.Builder(SaudeActivity.this);
                b.setMessage("Deseja apagar o paciente?");
                b.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        DBHelper db = new DBHelper(SaudeActivity.this);
                        PacienteDAO dao = new PacienteDAO(db);
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
