package es.iesnervion.revista.excepciones;

/**
 * Indica que una sección de la revista ha superado el máximo permitido.
 */
public class MaximosSuperadosException extends Exception {
    /**
     * Excepcion para indicar que se supera el maximo de elementos para esa seccion
     *
     * @param message señala que se supera el maximo de elementos para esa seccion
     */
    public MaximosSuperadosException(String message) {
        super(message);
    }
}
