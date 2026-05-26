package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Representa a un diseñador de moda.
 */
public class Diseñador extends Persona {

    // Su estilo al diseñar
    private String estilo;

    // Empresa para la que trabaja
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
    public Diseñador(String dni, String nombre, String tlf, String email, int edad,
            String estilo, String casaActual) {
        super(dni, nombre, tlf, email, edad);
        this.estilo = estilo;
        this.casaActual = casaActual;
    }

    // Getters

    public String getEstilo() {
        return estilo;
    }

    public String getCasaActual() {
        return casaActual;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    public void setCasaActual(String casaActual) {
        this.casaActual = casaActual;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Diseñador))
            return false;
        Diseñador d = (Diseñador) o;
        return Objects.equals(estilo, d.estilo) &&
                Objects.equals(casaActual, d.casaActual);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), estilo, casaActual);
    }

    @Override
    public String toString() {
        return "Diseñador{" +
                "dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", casaActual='" + casaActual + '\'' +
                '}';
    }
}
