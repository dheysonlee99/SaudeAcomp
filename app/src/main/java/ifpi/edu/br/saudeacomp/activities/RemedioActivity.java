package ifpi.edu.br.saudeacomp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.RemedioDAO;
import ifpi.edu.br.saudeacomp.modelo.Paciente;
import ifpi.edu.br.saudeacomp.modelo.Remedio;

public class RemedioActivity extends AppCompatActivity {

    int paciente_id;
    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio);

        paciente_id = getIntent().getIntExtra("paciente_id", 0);
        Toast.makeText(RemedioActivity.this, "ID Recebido: " + paciente_id, Toast.LENGTH_SHORT).show();

        db = new DBHelper(this);
    }

    public void remedioClick(View elementoClicado){

        EditText editNome = (EditText)findViewById(R.id.nome_remedio);
        EditText editModoUso = (EditText)findViewById(R.id.modo_uso);

        String nome = editNome.getText().toString();
        String modoUso = editModoUso.getText().toString();

        Paciente paciente = new Paciente();
        paciente.setId(paciente_id);

        Remedio remedio = new Remedio(nome,modoUso);

        RemedioDAO dao = new RemedioDAO(db);
        dao.inserirRemedio(remedio, paciente);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Remedio Adicionado ");
        builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();
    }
}
