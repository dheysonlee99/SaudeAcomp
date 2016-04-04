package ifpi.edu.br.saudeacomp.modelo;

/**
 * Created by programador on 30/03/16.
 */
public class Consulta {

    private int id;
    private int paciente_id;
    private String nome;
    private String data;
    private String especialidade;
    private String status;
    private String resultado;

    public Consulta(){}

    public Consulta(String nome, String data, String especialidade,String status){
        this.nome = nome;
        this.data = data;
        this.especialidade = especialidade;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaciente_id() {
        return paciente_id;
    }

    public void setPaciente_id(int paciente_id) {
        this.paciente_id = paciente_id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return "Local: "+ this.nome + "\n"+
                "Especialidade: " + this.especialidade + "\n"+
                "Data: " + this.data +"\n"+ this.status;
    }
}
