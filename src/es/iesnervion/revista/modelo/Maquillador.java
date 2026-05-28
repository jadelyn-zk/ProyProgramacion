package es.iesnervion.revista.modelo;

import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa a un maquillador.
 */
public class Maquillador extends Persona {

    // Tipo de maquillaje en el que se especializa
    private String especialidad;

    /**
     * Constructor por defecto.
     */
    public Maquillador() {
        super();
    }

    /**
     * Constructor completo de `Maquillador`.
     *
     * @param nombre      Nombre
     * @param edad        Edad
     * @param especialidad Especialidad en maquillaje
     */
    public Maquillador(String nombre, int edad,
            String especialidad) {
        super(nombre, edad);
        setEspecialidad(especialidad);
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = ValidacionDatos.validarTextoObligatorio(especialidad, "La especialidad");
    }

    public String getEspecialidad() {
        return especialidad;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Maquillador))
            return false;
        Maquillador otherMaquillador = (Maquillador) other;
        return Objects.equals(especialidad, otherMaquillador.especialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidad);
    }

    @Override
    public String toString() {
        return "Maquillador{" +
                "nombre='" + getNombre() + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
