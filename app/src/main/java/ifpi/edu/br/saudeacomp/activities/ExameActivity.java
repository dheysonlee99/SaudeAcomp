package ifpi.edu.br.saudeacomp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.ExameDAO;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.modelo.Exame;

public class ExameActivity extends AppCompatActivity {

    private PacienteDAO ass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame);

        ass = new PacienteDAO(this);
    }



    public void exameClick(View elementoClicado){

        EditText editNome = (EditText)findViewById(R.id.nome_exames);
        EditText editData = (EditText)findViewById(R.id.data_exames);
        Spinner editTipo = (Spinner)findViewById(R.id.tipo_exames);
        Spinner editStatus = (Spinner)findViewById(R.id.status_exames);

        String nome = editNome.getText().toString();
        String data = editData.getText().toString();
        String tipo = editTipo.getSelectedItem().toString();
        String status = editStatus.getSelectedItem().toString();

        Exame exame = new Exame(nome,data,tipo,status);

        ExameDAO dao = new ExameDAO(ass);
        dao.inserirExame(exame);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exame agendada ");
        builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();


    }
}
