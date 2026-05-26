package es.iesnervion.revista.excepciones;

/**
 * Excepción base para todos los errores específicos del dominio de la revista.
 */
public class RevistaException extends Exception {
    /**
     * Constructor con mensaje descriptivo.
     * 
     * @param message El motivo del error.
     */
    public RevistaException(String message) {
        super(message);
    }
}
