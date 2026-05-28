package es.iesnervion.revista.modelo;

/**
 * Representa los meses del año en formato legible.
 */
public enum Mes {
    ENERO("Enero"), FEBRERO("Febrero"), MARZO("Marzo"), ABRIL("Abril"),
    MAYO("Mayo"), JUNIO("Junio"), JULIO("Julio"), AGOSTO("Agosto"),
    SEPTIEMBRE("Septiembre"), OCTUBRE("Octubre"), NOVIEMBRE("Noviembre"), DICIEMBRE("Diciembre");

    private final String nombre;

    Mes(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return nombre;
    }
}
