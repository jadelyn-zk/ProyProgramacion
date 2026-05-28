package es.iesnervion.revista.excepciones;

/**
 * Indica que una sección de la revista ha superado el máximo permitido.
 */
public class MaximosSuperadosException extends RevistaException {
    /**
     * Crea la excepción con la descripción del exceso.
     *
     * @param message Descripción de la sección que ha superado el máximo
     */
    public MaximosSuperadosException(String message) {
        super(message);
    }
}
