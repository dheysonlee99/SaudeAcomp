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
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Paciente;

public class ConsultaStatusActivity extends AppCompatActivity {

    int consulta_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_status);

        consulta_id = getIntent().getIntExtra("consulta_id", 0);
        Toast.makeText(ConsultaStatusActivity.this, "ID Recebido: " + consulta_id, Toast.LENGTH_SHORT).show();
    }


    public void statusClick(View elementoClicado){



        Spinner editStatus = (Spinner)findViewById(R.id.muda_status);


        String status = editStatus.getSelectedItem().toString();


        Consulta consulta = new Consulta();
        consulta.setStatus(status);
        consulta.setId(consulta_id);

        DBHelper db = new DBHelper(this);

        ConsultaDAO dao = new ConsultaDAO(db);

        dao.atualizarStatus(consulta);



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
