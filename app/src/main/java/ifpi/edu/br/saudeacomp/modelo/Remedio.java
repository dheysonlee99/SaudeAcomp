package ifpi.edu.br.saudeacomp.modelo;

/**
 * Created by programador on 30/03/16.
 */
public class Remedio {

    private int id;
    private String nome;
    private String modoUso;

    public Remedio(){
    }

    public Remedio(String nome, String modoUso){
        this.nome = nome;
        this.modoUso = modoUso;
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

    public String getModoUso() {
        return modoUso;
    }

    public void setModoUso(String modoUso) {
        this.modoUso = modoUso;
    }

    @Override
    public String toString() {
        return "Nome: "+ this.nome +"\n" + " Modo de Uso: "+this.modoUso;
    }
}
