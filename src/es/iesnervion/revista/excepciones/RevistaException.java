package es.iesnervion.revista.excepciones;

/**
 * Excepción base para errores del dominio "Revista".
 * <p>
 * Todas las excepciones específicas del proyecto extienden de esta clase,
 * lo que permite agrupar y gestionar los errores del dominio de forma coherente.
 * </p>
 */
public class RevistaException extends Exception {
    /**
     * Crea una excepción de dominio con un mensaje descriptivo.
     *
     * @param message Mensaje que describe el motivo del error
     */
    public RevistaException(String message) {
        super(message);
    }
}
