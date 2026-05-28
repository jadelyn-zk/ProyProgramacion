package es.iesnervion.revista.excepciones;

/**
 * Señala que la revista no cumple con el rango permitido de páginas.
 */
public class LimitePaginasException extends Exception {
    /**
     * Excepcion para indicar que sobrepasa el num de paginas
     *
     * @param message señala que sobrepasa el num de paginas 
     */
    public LimitePaginasException(String message) {
        super(message);
    }
}
