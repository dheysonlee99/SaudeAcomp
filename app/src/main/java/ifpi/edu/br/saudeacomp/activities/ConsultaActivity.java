package ifpi.edu.br.saudeacomp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.dao.ConsultaDAO;
import ifpi.edu.br.saudeacomp.modelo.Paciente;

public class ConsultaActivity extends AppCompatActivity {


    int paciente_id;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);

        paciente_id = getIntent().getIntExtra("paciente_id", 0);
        Toast.makeText(ConsultaActivity.this, "ID Recebido: " + paciente_id, Toast.LENGTH_SHORT).show();

        db = new DBHelper(this);
    }


    public void agendaClick(View elementoClicado){


        EditText editNome = (EditText)findViewById(R.id.nome_clinica);

        EditText editData = (EditText)findViewById(R.id.data_consulta);
        Spinner editEspecialidade = (Spinner)findViewById(R.id.especialidade);
        Spinner editStatus = (Spinner)findViewById(R.id.status_consulta);

        String nome = editNome.getText().toString();
        String data = editData.getText().toString();
        String especialidade = editEspecialidade.getSelectedItem().toString();
        String status = editStatus.getSelectedItem().toString();

        Consulta consulta = new Consulta(nome,data,especialidade,status);

        ConsultaDAO dao = new ConsultaDAO(db);
        Paciente paciente = new Paciente();
        paciente.setId(paciente_id);
        dao.inserirConsulta(consulta,paciente);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Consulta agendada ");
        builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();


    }
}
