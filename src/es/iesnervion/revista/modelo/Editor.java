package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Representa a un editor de la revista.
 */
public class Editor extends Persona {

    // El cargo o puesto que tiene en la revista
    private String cargo;

    /**
     * Constructor vacio.
     */
    public Editor() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Editor(String dni, String nombre, String tlf, String email, int edad,
            String cargo) {
        super(dni, nombre, tlf, email, edad);
        this.cargo = cargo;
    }

    // Getters

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Editor))
            return false;
        Editor e = (Editor) o;
        return Objects.equals(cargo, e.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo);
    }

    @Override
    public String toString() {
        return "Editor{" +
                "dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
