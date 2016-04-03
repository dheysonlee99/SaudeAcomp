package ifpi.edu.br.saudeacomp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import ifpi.edu.br.saudeacomp.modelo.Paciente;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }



    public void cadastraClick(View elementoClicado){

        EditText editNome = (EditText)findViewById(R.id.nome_paciente);
        EditText editSus = (EditText)findViewById(R.id.numeros_sus);
        EditText editIdade = (EditText)findViewById(R.id.idade);
        Spinner editSexo = (Spinner)findViewById(R.id.tipo_sexo);

        String nome = editNome.getText().toString();
        String sus = editSus.getText().toString();
        String idade = editIdade.getText().toString();
        String sexo = editSexo.getSelectedItem().toString();

        Paciente paciente = new Paciente(nome, sus, idade, sexo);

        PacienteDAO dao = new PacienteDAO(this);

        dao.inserir(paciente);

        //Toast.makeText(this, "Veiculo adicionado ;)", Toast.LENGTH_SHORT).show();

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Paciente cadastrado com sucesso:");
        builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();


    }
}
