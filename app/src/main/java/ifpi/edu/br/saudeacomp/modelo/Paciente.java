package ifpi.edu.br.saudeacomp.modelo;

/**
 * Created by programador on 29/03/16.
 */
public class Paciente {
    private int id;
    private String nome;
    private String susNumero;
    private String idade;
    private String sexo;

    public Paciente(){
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

    @Override
    public String toString() {
        return "Nome do Paciente: " +"\n"+
                this.nome +"\n"+
                "Cartao do Sus: "+
                this.susNumero+"\n"+
                "Sexo: "+ this.sexo + "  Idade: "+ this.idade + " id: " + this.id;
    }
}
