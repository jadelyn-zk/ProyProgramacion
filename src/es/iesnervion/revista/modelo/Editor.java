package es.iesnervion.revista.modelo;

import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa a un editor de la revista.
 */
public class Editor extends Persona {

    // Cargo o puesto dentro de la revista
    private String cargo;

    /**
     * Constructor por defecto.
     */
    public Editor() {
        super();
    }

    /**
     * Constructor completo de `Editor`.
     *
     * @param nombre Nombre
     * @param edad   Edad
     * @param cargo  Cargo o puesto en la revista
     */
    public Editor(String nombre, int edad, String cargo) {
        super(nombre, edad);
        setCargo(cargo);
    }

    public void setCargo(String cargo) {
        this.cargo = ValidacionDatos.validarTextoObligatorio(cargo, "El cargo");
    }

    public String getCargo() {
        return cargo;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Editor))
            return false;
        Editor otherEditor = (Editor) other;
        return Objects.equals(cargo, otherEditor.cargo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargo);
    }

    @Override
    public String toString() {
        return "Editor{" +
                "nombre='" + getNombre() + '\'' +
                ", cargo='" + cargo + '\'' +
                '}';
    }
}
