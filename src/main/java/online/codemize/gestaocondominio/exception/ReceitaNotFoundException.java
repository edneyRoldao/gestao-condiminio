package online.codemize.gestaocondominio.exception;

public class ReceitaNotFoundException extends RuntimeException {

    public ReceitaNotFoundException() {
        super("receita não foi encontrada");
    }

}
