package es.iesnervion.revista.excepciones;

/**
 * Se lanza cuando la portada no contiene los elementos obligatorios.
 */
public class PortadaIncompletaException extends RevistaException {
    /**
     * Crea la excepción con la descripción de lo que falta en la portada.
     *
     * @param message Descripción de los elementos faltantes en la portada
     */
    public PortadaIncompletaException(String message) {
        super(message);
    }
}
