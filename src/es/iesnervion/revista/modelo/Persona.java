package es.iesnervion.revista.modelo;

import java.io.Serializable;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Clase base para representar a cualquier persona en el sistema.
 * Ahora `Persona` solo contiene `nombre` y `edad`.
 */
public abstract class Persona implements Serializable {

    private static final long serialVersionUID = 1L;

    // Su nombre completo
    private String nombre;

    // Su edad
    private int edad;

    /**
     * Constructor por defecto.
     */
    public Persona() {
    }

    /**
     * Constructor mínimo de `Persona`.
     *
     * @param nombre El nombre completo
     * @param edad   Edad (entero positivo)
     */
    public Persona(String nombre, int edad) {
        setNombre(nombre);
        setEdad(edad);
    }

    // -- Getters --

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    // -- Setters --

    public void setNombre(String nombre) {
        this.nombre = ValidacionDatos.validarTextoObligatorio(nombre, "El nombre");
    }

    public void setEdad(int edad) {
        this.edad = ValidacionDatos.validarEnteroPositivo(edad, "La edad");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Persona otherPersona = (Persona) other;
        return edad == otherPersona.edad && Objects.equals(nombre, otherPersona.nombre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, edad);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                '}';
    }
}
