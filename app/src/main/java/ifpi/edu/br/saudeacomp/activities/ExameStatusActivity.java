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
import ifpi.edu.br.saudeacomp.dao.ExameDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Exame;

public class ExameStatusActivity extends AppCompatActivity {

    private int exame_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame_status);

        exame_id = getIntent().getIntExtra("exame_id",0);
        Toast.makeText(ExameStatusActivity.this, "ID Recebido: " + exame_id, Toast.LENGTH_SHORT).show();

    }

    public void exameRealizadoClick(View elementoClicado){



        Spinner editStatus = (Spinner)findViewById(R.id.exame_realizado);
        EditText editResultado = (EditText)findViewById(R.id.exame_resultado);


        String status = editStatus.getSelectedItem().toString();
        String resultado = editResultado.getText().toString();


        Exame exame = new Exame();
        exame.setStatus(status);
        exame.setResultado(resultado);
        exame.setId(exame_id);

        DBHelper db = new DBHelper(this);

        ExameDAO dao = new ExameDAO(db);

        dao.atualizarStatus(exame);



        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Status modificado  com sucesso:");
        builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();


    }
}
