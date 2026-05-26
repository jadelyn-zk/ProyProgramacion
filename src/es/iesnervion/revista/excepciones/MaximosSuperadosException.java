package es.iesnervion.revista.excepciones;

/**
 * Excepción lanzada cuando se intenta superar el número máximo de elementos
 * permitidos en una sección (ej: demasiados anuncios).
 */
public class MaximosSuperadosException extends RevistaException {
    /**
     * @param message Detalle de la sección que ha superado el máximo.
     */
    public MaximosSuperadosException(String message) {
        super(message);
    }
}
