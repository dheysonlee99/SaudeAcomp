package ifpi.edu.br.saudeacomp.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by programador on 29/03/16.
 */
public class Paciente {


    private int id;
    private String nome;
    private String susNumero;
    private String idade;
    private String sexo;

    private List<Consulta> consultas;

    public Paciente(){
        this.consultas = new ArrayList<>();
    }
    public Paciente(String nome,String susNumero,String idade, String sexo){
        this.nome = nome;
        this.susNumero = susNumero;
        this.idade = idade;
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSusNumero() {
        return susNumero;
    }

    public void setSusNumero(String susNumero) {
        this.susNumero = susNumero;
    }

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public void addConsulta(Consulta consulta){
        this.consultas.add(consulta);
    }

    @Override
    public String toString() {
        return "Nome do Paciente: " +"\n"+
                this.nome +"\n"+
                "Cartao do Sus: "+
                this.susNumero+"\n"+
                "Sexo: "+ this.sexo + "  Idade: "+ this.idade;
    }
}
