package es.iesnervion.revista.excepciones;

/**
 * Excepción lanzada cuando la revista excede el límite total de páginas
 * permitido.
 */
public class LimitePaginasException extends RevistaException {
    /**
     * @param message Detalle del exceso de páginas.
     */
    public LimitePaginasException(String message) {
        super(message);
    }
}
