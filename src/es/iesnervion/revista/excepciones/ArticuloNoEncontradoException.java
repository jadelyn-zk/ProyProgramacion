package es.iesnervion.revista.excepciones;

/**
 * Se lanza cuando no se encuentra un artículo que coincida con los criterios indicados.
 */
public class ArticuloNoEncontradoException extends RevistaException {
    /**
     * Crea la excepción con el detalle del artículo que no se ha hallado.
     *
     * @param message Identificador, título o descripción del artículo no hallado
     */
    public ArticuloNoEncontradoException(String message) {
        super(message);
    }
}
