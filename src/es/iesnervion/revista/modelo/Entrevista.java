package es.iesnervion.revista.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un artículo tipo entrevista.
 */
public class Entrevista extends Articulo {

    // El entrevistador que hace las preguntas
    private Entrevistador entrevistador;

    // La persona a la que entrevistan
    private Persona entrevistado;

    // Lista de las preguntas
    private List<String> preguntas;

    // Lista de las respuestas
    private List<String> respuestas;

    // Lugar donde se hizo
    private String lugar;

    // Fecha de la entrevista
    private LocalDate fecha;

    /**
     * Constructor vacio.
     */
    public Entrevista() {
        super();
        this.preguntas = new ArrayList<>();
        this.respuestas = new ArrayList<>();
    }

    /**
     * Constructor con datos básicos.
     */
    public Entrevista(String titulo, Entrevistador entrevistador, Persona entrevistado,
            String lugar, LocalDate fecha) {
        super(titulo);
        this.entrevistador = entrevistador;
        this.entrevistado = entrevistado;
        this.lugar = lugar;
        this.fecha = fecha;
        this.preguntas = new ArrayList<>();
        this.respuestas = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "Entrevista";
    }

    // Getters y Setters

    public Entrevistador getEntrevistador() {
        return entrevistador;
    }

    public Persona getEntrevistado() {
        return entrevistado;
    }

    public List<String> getPreguntas() {
        return preguntas;
    }

    public List<String> getRespuestas() {
        return respuestas;
    }

    public String getLugar() {
        return lugar;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setEntrevistador(Entrevistador entrevistador) {
        this.entrevistador = entrevistador;
    }

    public void setEntrevistado(Persona entrevistado) {
        this.entrevistado = entrevistado;
    }

    public void setPreguntas(List<String> preguntas) {
        this.preguntas = preguntas;
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = respuestas;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Entrevista))
            return false;
        Entrevista e = (Entrevista) o;
        return Objects.equals(entrevistador, e.entrevistador) &&
                Objects.equals(entrevistado, e.entrevistado) &&
                Objects.equals(lugar, e.lugar) &&
                Objects.equals(fecha, e.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), entrevistador, entrevistado, lugar, fecha);
    }

    @Override
    public String toString() {
        return "Entrevista{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", pagina=" + getPaginaInicio() +
                ", entrevistado=" + (entrevistado != null ? entrevistado.getNombre() : "n/a") +
                '}';
    }
}
