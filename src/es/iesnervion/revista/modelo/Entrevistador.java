package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Representa a un entrevistador.
 */
public class Entrevistador extends Persona {

    // El medio de comunicación del que viene
    private String medio;

    /**
     * Constructor por defecto.
     */
    public Entrevistador() {
        super();
    }

    /**
     * Constructor completo.
     */
    public Entrevistador(String dni, String nombre, String tlf, String email, int edad,
            String medio) {
        super(dni, nombre, tlf, email, edad);
        this.medio = medio;
    }

    // -- Métodos de acceso --

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Entrevistador))
            return false;
        Entrevistador e = (Entrevistador) o;
        return Objects.equals(medio, e.medio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), medio);
    }

    @Override
    public String toString() {
        return "Entrevistador{" +
                "dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", medio='" + medio + '\'' +
                '}';
    }
}
