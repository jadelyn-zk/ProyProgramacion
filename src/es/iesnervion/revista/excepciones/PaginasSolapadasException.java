package es.iesnervion.revista.excepciones;

/**
 * Excepción lanzada cuando hay un error en la coherencia de las páginas
 * (aunque el nuevo modelo secuencial lo previene automáticamente).
 */
public class PaginasSolapadasException extends RevistaException {
    /**
     * @param message Detalle del conflicto de páginas.
     */
    public PaginasSolapadasException(String message) {
        super(message);
    }
}
