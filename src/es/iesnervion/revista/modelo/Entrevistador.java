package es.iesnervion.revista.modelo;

import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa a un entrevistador.
 */
public class Entrevistador extends Persona {

    // Medio de comunicación del que procede
    private String medio;

    /**
     * Constructor por defecto.
     */
    public Entrevistador() {
        super();
    }

    /**
     * Constructor completo de `Entrevistador`.
     *
     * @param nombre Nombre
     * @param edad   Edad
     * @param medio  Medio de procedencia
     */
    public Entrevistador(String nombre, int edad, String medio) {
        super(nombre, edad);
        setMedio(medio);
    }

    public void setMedio(String medio) {
        this.medio = ValidacionDatos.validarTextoObligatorio(medio, "El medio");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Entrevistador))
            return false;
        Entrevistador otherEntrevistador = (Entrevistador) other;
        return Objects.equals(medio, otherEntrevistador.medio);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), medio);
    }

    @Override
    public String toString() {
        return "Entrevistador{" +
                "nombre='" + getNombre() + '\'' +
                ", medio='" + medio + '\'' +
                '}';
    }
}
