package ifpi.edu.br.saudeacomp.enums;

/**
 * Created by programador on 05/04/16.
 */

// nao deu tempo de mudar status para enum
public enum Status {
    Realizada("Realizada"),Nao_Realizada("Nao Realizada");

    private String s;

    private Status(String s){
        this.s = s;
    }

    @Override
    public String toString() {
        return this.s;
    }
}
