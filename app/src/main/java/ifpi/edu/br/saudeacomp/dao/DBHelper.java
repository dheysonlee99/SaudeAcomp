package ifpi.edu.br.saudeacomp.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by programador on 04/04/16.
 */
public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context ctx){
        super(ctx,"Pacientes.bd", null, 6);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE Paciente " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome VARCHAR (50), " +
                "numeroSus VARCHAR (50), " +
                "sexo VARCHAR (50)," +
                "idade INTEGER );";
        db.execSQL(sql);

        sql = "CREATE TABLE Patologia " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "paciente_id INTEGER, " +
                "nome VARCHAR (50));";

        db.execSQL(sql);

        sql = "CREATE TABLE Consulta " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "paciente_id INTEGER, " +
                "nome VARCHAR (50), " +
                "data VARCHAR (50), " +
                "especialidade VARCHAR (50)," +
                "status VARCHAR (50)," +
                "resultado VARCHAR (250));";
        db.execSQL(sql);

        sql = "CREATE TABLE Exame " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "paciente_id INTEGER, " +
                "nome VARCHAR (50), " +
                "data VARCHAR (50), " +
                "tipo VARCHAR (50)," +
                "status VARCHAR (50)," +
                "resultado VARCHAR (250));";

        db.execSQL(sql);

        sql = "CREATE TABLE Remedio " +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "paciente_id INTEGER, " +
                "nome VARCHAR (50), " +
                "modoUso VARCHAR (250));";
        db.execSQL(sql);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS Paciente;";
        db.execSQL(sql);
        onCreate(db);

    }
}
