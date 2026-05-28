package es.iesnervion.revista.excepciones;

/**
 * Se lanza cuando no se encuentra un anuncio que coincida con los criterios indicados.
 */
public class AnuncioNoEncontradoException extends RevistaException {
    /**
     * Crea la excepción con el detalle del anuncio que no se ha encontrado.
     *
     * @param message Identificador, marca o descripción del anuncio no hallado
     */
    public AnuncioNoEncontradoException(String message) {
        super(message);
    }
}
