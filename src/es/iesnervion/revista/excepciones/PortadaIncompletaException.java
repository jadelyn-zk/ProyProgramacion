package es.iesnervion.revista.excepciones;

/**
 * Excepción lanzada cuando la portada no contiene los elementos obligatorios
 * para ser considerada válida.
 */
public class PortadaIncompletaException extends RevistaException {
    /**
     * @param message Descripción de los elementos faltantes en la portada.
     */
    public PortadaIncompletaException(String message) {
        super(message);
    }
}
