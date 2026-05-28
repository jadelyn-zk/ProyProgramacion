package es.iesnervion.revista.excepciones;

/**
 * Indica que la revista no alcanza los requisitos mínimos de contenido.
 */
public class MinimosNoCumplidosException extends RevistaException {
    /**
     * Crea la excepción con la explicación de los elementos faltantes.
     *
     * @param message Descripción de los mínimos que no se han cumplido
     */
    public MinimosNoCumplidosException(String message) {
        super(message);
    }
}
