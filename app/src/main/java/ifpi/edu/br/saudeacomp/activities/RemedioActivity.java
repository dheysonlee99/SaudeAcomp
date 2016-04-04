package ifpi.edu.br.saudeacomp.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.dao.RemedioDAO;
import ifpi.edu.br.saudeacomp.modelo.Remedio;

public class RemedioActivity extends AppCompatActivity {

    private DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remedio);

        db = new DBHelper(this);
    }

    public void remedioClick(View elementoClicado){

        EditText editNome = (EditText)findViewById(R.id.nome_remedio);
        EditText editModoUso = (EditText)findViewById(R.id.modo_uso);

        String nome = editNome.getText().toString();
        String modoUso = editModoUso.getText().toString();

        Remedio remedio = new Remedio(nome,modoUso);

        RemedioDAO dao = new RemedioDAO(db);
        dao.inserirRemedio(remedio);

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
