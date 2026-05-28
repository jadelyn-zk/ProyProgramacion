package es.iesnervion.revista.excepciones;

/**
 * Indica que la revista no alcanza los requisitos mínimos de contenido.
 */
public class MinimosNoCumplidosException extends Exception {
    /**
     * Excepcion que indica que no se cumple los elementos minimos para una seccion
     *
     * @param message señala que no se cumplen los mínimos elementos para una seccion
     */
    public MinimosNoCumplidosException(String message) {
        super(message);
    }
}
