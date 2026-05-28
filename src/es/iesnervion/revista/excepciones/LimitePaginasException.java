package es.iesnervion.revista.excepciones;

/**
 * Señala que la revista no cumple con el rango permitido de páginas.
 */
public class LimitePaginasException extends RevistaException {
    /**
     * Crea la excepción con el detalle del desajuste de páginas.
     *
     * @param message Descripción concreta del problema de paginación
     */
    public LimitePaginasException(String message) {
        super(message);
    }
}
