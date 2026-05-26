package es.iesnervion.revista.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un reportaje escrito.
 */
public class Reportaje extends Articulo {

    // Temática sobre la que trata
    private String tematica;

    // Personas que han salido en el reportaje
    private List<Persona> personasQueParticipan;

    // Si tiene alguna colección de moda asociada
    private ColeccionModa coleccionRelacionada;

    /**
     * Constructor por defecto.
     */
    public Reportaje() {
        super();
        this.personasQueParticipan = new ArrayList<>();
    }

    /**
     * Constructor con título y tema.
     */
    public Reportaje(String titulo, String tematica) {
        super(titulo);
        this.tematica = tematica;
        this.personasQueParticipan = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Reportaje";
    }

    // -- Getters y Setters --

    public String getTematica() {
        return tematica;
    }

    public List<Persona> getPersonasQueParticipan() {
        return personasQueParticipan;
    }

    public ColeccionModa getColeccionRelacionada() {
        return coleccionRelacionada;
    }

    public void setTematica(String tematica) {
        this.tematica = tematica;
    }

    public void setPersonasQueParticipan(List<Persona> p) {
        this.personasQueParticipan = p;
    }

    public void setColeccionRelacionada(ColeccionModa c) {
        this.coleccionRelacionada = c;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Reportaje))
            return false;
        Reportaje r = (Reportaje) o;
        return Objects.equals(tematica, r.tematica);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), tematica);
    }

    @Override
    public String toString() {
        return "Reportaje{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", pagina=" + getPaginaInicio() +
                ", tema='" + tematica + '\'' +
                '}';
    }
}
