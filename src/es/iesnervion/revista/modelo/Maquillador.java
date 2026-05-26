package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Representa a un maquillador.
 */
public class Maquillador extends Persona {

    // En qué tipo de maquillaje se especializa
    private String especialidad;

    /**
     * Constructor vacio.
     */
    public Maquillador() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Maquillador(String dni, String nombre, String tlf, String email, int edad,
            String especialidad) {
        super(dni, nombre, tlf, email, edad);
        this.especialidad = especialidad;
    }

    // Getters y Setters

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Maquillador))
            return false;
        Maquillador m = (Maquillador) o;
        return Objects.equals(especialidad, m.especialidad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), especialidad);
    }

    @Override
    public String toString() {
        return "Maquillador{" +
                "dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }
}
