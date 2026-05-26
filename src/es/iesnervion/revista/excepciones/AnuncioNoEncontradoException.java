package es.iesnervion.revista.excepciones;

/**
 * Excepción lanzada cuando se intenta acceder o borrar un anuncio que no
 * existe.
 */
public class AnuncioNoEncontradoException extends RevistaException {
    /**
     * @param message Identificador o marca del anuncio no hallado.
     */
    public AnuncioNoEncontradoException(String message) {
        super(message);
    }
}
