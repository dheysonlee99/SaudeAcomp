package ifpi.edu.br.saudeacomp.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Calendar;

import ifpi.edu.br.saudeacomp.R;
import ifpi.edu.br.saudeacomp.dao.DBHelper;
import ifpi.edu.br.saudeacomp.dao.ExameDAO;
import ifpi.edu.br.saudeacomp.dao.PacienteDAO;
import ifpi.edu.br.saudeacomp.modelo.Consulta;
import ifpi.edu.br.saudeacomp.modelo.Exame;
import ifpi.edu.br.saudeacomp.modelo.Paciente;

public class ExameActivity extends AppCompatActivity {


    int paciente_id;
    private DBHelper db;
    private EditText  data;
    private int ano, mes, dia;
    private String dataFormat = "0/0/0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exame);

        data = (EditText)findViewById(R.id.data_exames);
        paciente_id = getIntent().getIntExtra("paciente_id", 0);
        Toast.makeText(ExameActivity.this, "ID Recebido: " + paciente_id, Toast.LENGTH_SHORT).show();

        db = new DBHelper(this);

    }




    @Override
    protected void onResume() {
        super.onResume();
        data.setInputType(InputType.TYPE_NULL);
        data.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    datePicker(v);
                }
            }
        });
    }
    public void datePicker(View v){
        hideKeyboard(v);
        DatePickerDialog.OnDateSetListener odsl = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                ano = year;
                mes = monthOfYear + 1;
                dia = dayOfMonth;
                dataFormat = String.format("%02d/%02d/%04d", dia, mes, ano);
                data.setText(dataFormat);
                data.setSelection(dataFormat.length());
            }
        };
        final Calendar c = Calendar.getInstance();
        DatePickerDialog dlg = new DatePickerDialog(ExameActivity.this, odsl, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
        if (dataFormat != "0/0/0") {
            dlg.updateDate(ano, mes - 1, dia);
        }
        dlg.show();
    }

    public void hideKeyboard(View view) {
        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
        Paciente paciente = new Paciente();

        paciente.setId(paciente_id);
        ExameDAO dao = new ExameDAO(db);
        dao.inserirExame(exame, paciente);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Exame agendado ");
        builder.setPositiveButton("Ok!", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.show();


    }
}
