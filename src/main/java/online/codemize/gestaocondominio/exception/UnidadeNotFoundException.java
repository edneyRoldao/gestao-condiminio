package online.codemize.gestaocondominio.exception;

public class UnidadeNotFoundException extends RuntimeException {

    public UnidadeNotFoundException() {
        super("a unidade não foi encontrada");
    }

}
