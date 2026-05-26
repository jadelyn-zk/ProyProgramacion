package es.iesnervion.revista.excepciones;

/**
 * Excepción lanzada cuando se intenta acceder o borrar un artículo que no
 * existe.
 */
public class ArticuloNoEncontradoException extends RevistaException {
    /**
     * @param message Identificador o título del artículo no hallado.
     */
    public ArticuloNoEncontradoException(String message) {
        super(message);
    }
}
