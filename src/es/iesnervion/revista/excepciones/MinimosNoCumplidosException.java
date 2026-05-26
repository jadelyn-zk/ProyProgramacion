package es.iesnervion.revista.excepciones;

/**
 * Excepción lanzada cuando no se cumplen los requisitos mínimos de contenido
 * para la publicación de la revista.
 */
public class MinimosNoCumplidosException extends RevistaException {
    /**
     * @param message Descripción de los mínimos faltantes.
     */
    public MinimosNoCumplidosException(String message) {
        super(message);
    }
}
