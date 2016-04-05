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
import ifpi.edu.br.saudeacomp.dao.ConsultaDAO;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.PatologiaDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Paciente;
import ifpi.edu.br.saudeacomp.modelo.Patologia;

public class PatologiaActivity extends AppCompatActivity {

    int paciente_id;
    private DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patologia);

        paciente_id = getIntent().getIntExtra("paciente_id", 0);
        Toast.makeText(PatologiaActivity.this, "ID Recebido: " + paciente_id, Toast.LENGTH_SHORT).show();

        db = new DBHelper(this);
    }

    public void patologiaClick(View elementoClicado){


        EditText editNome = (EditText)findViewById(R.id.patologia);

        String nome = editNome.getText().toString();


       Patologia patologia = new Patologia(nome);

        PatologiaDAO dao = new PatologiaDAO(db);
        Paciente paciente = new Paciente();
        paciente.setId(paciente_id);
        dao.inserirPatologia(patologia,paciente);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Patologia adicionada ");
        builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();


    }
}
