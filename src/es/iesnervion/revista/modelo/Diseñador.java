package es.iesnervion.revista.modelo;

import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa a un diseñador de moda.
 */
public class Diseñador extends Persona {

    // Estilo con el que suele diseñar
    private String estilo;

    // Casa actual para la que trabaja
    private String casaActual;

    /**
     * Constructor vacio.
     */
    public Diseñador() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Diseñador(String nombre, int edad, String estilo, String casaActual) {
        super(nombre, edad);
        setEstilo(estilo);
        setCasaActual(casaActual);
    }

    public void setEstilo(String estilo) {
        this.estilo = ValidacionDatos.validarTextoObligatorio(estilo, "El estilo");
    }

    public void setCasaActual(String casaActual) {
        this.casaActual = ValidacionDatos.validarTextoObligatorio(casaActual, "La casa actual");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Diseñador))
            return false;
        Diseñador otherDiseñador = (Diseñador) other;
        return Objects.equals(estilo, otherDiseñador.estilo) &&
            Objects.equals(casaActual, otherDiseñador.casaActual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), estilo, casaActual);
    }

    @Override
    public String toString() {
        return "Diseñador{" +
                "nombre='" + getNombre() + '\'' +
                ", casaActual='" + casaActual + '\'' +
                '}';
    }
}
