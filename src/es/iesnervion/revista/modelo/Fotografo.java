package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Representa a un fotógrafo.
 */
public class Fotografo extends Persona {

    // El estilo que suele usar para las fotos
    private String estilo;

    /**
     * Constructor vacio.
     */
    public Fotografo() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Fotografo(String dni, String nombre, String tlf, String email, int edad,
            String estilo) {
        super(dni, nombre, tlf, email, edad);
        this.estilo = estilo;
    }

    // Getters

    public String getEstilo() {
        return estilo;
    }

    public void setEstilo(String estilo) {
        this.estilo = estilo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Fotografo))
            return false;
        Fotografo f = (Fotografo) o;
        return Objects.equals(estilo, f.estilo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), estilo);
    }

    @Override
    public String toString() {
        return "Fotografo{" +
                "dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", estilo='" + estilo + '\'' +
                '}';
    }
}
