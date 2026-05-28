package es.iesnervion.revista.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

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
     * Constructor por defecto de `Reportaje`.
     */
    public Reportaje() {
        super();
        this.personasQueParticipan = new ArrayList<>();
    }

    /**
     * Crea un reportaje con título y temática.
     *
     * @param titulo   Título del reportaje
     * @param tematica Tema o temática principal
     */
    public Reportaje(String titulo, String tematica) {
        super(titulo);
        setTematica(tematica);
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
        this.tematica = ValidacionDatos.validarTextoObligatorio(tematica, "La temática");
    }

    public void setPersonasQueParticipan(List<Persona> personasParticipantes) {
        this.personasQueParticipan = ValidacionDatos.validarListaNoNula(personasParticipantes, "La lista de personas participantes");
    }

    public void setColeccionRelacionada(ColeccionModa coleccionRelacionada) {
        this.coleccionRelacionada = coleccionRelacionada;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Reportaje))
            return false;
        Reportaje otherReportaje = (Reportaje) other;
        return Objects.equals(tematica, otherReportaje.tematica);
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
