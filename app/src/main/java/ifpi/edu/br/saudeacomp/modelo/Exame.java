package ifpi.edu.br.saudeacomp.modelo;

/**
 * Created by programador on 31/03/16.
 */
public class Exame {

    private int id;
    private int pacienteid;
    private String nomeLocal;
    private String data;
    private String tipo;
    private String status;
    private String resultado;

    public Exame(){
    }

    public Exame(String nomeLocal, String data, String tipo, String status){
        this.nomeLocal = nomeLocal;
        this.data = data;
        this.tipo = tipo;
        this.status = status;
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

    public String getNomeLocal() {
        return nomeLocal;
    }

    public void setNomeLocal(String nomeLocal) {
        this.nomeLocal = nomeLocal;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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
        String retorno = null;
        if (this.getStatus().equals("Nao Realizado")){
            retorno = "Local: " + this.nomeLocal + "\n"+
                    "Tipo: "+ this.tipo +"\n"+
                    "Data: " + this.data +"\n"+ this.status;
        }
        else if (this.getStatus().equals("Realizado")){
            retorno = "Local: " + this.nomeLocal + "\n"+
                    "Tipo: "+ this.tipo +"\n"+
                    "Data: " + this.data +"\n"+"Resultado:"+ "\n"+"- "+this.getResultado() ;
        }
        return retorno;
    }
}

