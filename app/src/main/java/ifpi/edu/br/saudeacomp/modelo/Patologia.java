package ifpi.edu.br.saudeacomp.modelo;

/**
 * Created by programador on 05/04/16.
 */
public class Patologia {

    private int id;
    private int pacienteid;
    private String nome;

    public Patologia(){
    }

    public Patologia(String nome){
        this.nome = nome;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPacienteid() {
        return pacienteid;
    }

    public void setPacienteid(int pacienteid) {
        this.pacienteid = pacienteid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.nome;
    }
}
