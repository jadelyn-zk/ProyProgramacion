package es.iesnervion.revista.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

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
     * Constructor por defecto.
     */
    public Entrevista() {
        super();
        this.preguntas = new ArrayList<>();
        this.respuestas = new ArrayList<>();
    }

    /**
     * Constructor básico de `Entrevista`.
     *
     * @param titulo       Título del artículo
     * @param entrevistador Entrevistador responsable
     * @param entrevistado Persona entrevistada
     * @param lugar        Lugar de la entrevista
     * @param fecha        Fecha de realización
     */
    public Entrevista(String titulo, Entrevistador entrevistador, Persona entrevistado,
            String lugar, LocalDate fecha) {
        super(titulo);
        setEntrevistador(entrevistador);
        setEntrevistado(entrevistado);
        setLugar(lugar);
        setFecha(fecha);
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
        this.entrevistador = ValidacionDatos.validarNoNulo(entrevistador, "El entrevistador");
    }

    public void setEntrevistado(Persona entrevistado) {
        this.entrevistado = ValidacionDatos.validarNoNulo(entrevistado, "El entrevistado");
    }

    public void setPreguntas(List<String> preguntas) {
        this.preguntas = ValidacionDatos.validarListaNoNula(preguntas, "La lista de preguntas");
    }

    public void setRespuestas(List<String> respuestas) {
        this.respuestas = ValidacionDatos.validarListaNoNula(respuestas, "La lista de respuestas");
    }

    public void setLugar(String lugar) {
        this.lugar = ValidacionDatos.validarTextoObligatorio(lugar, "El lugar de la entrevista");
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = ValidacionDatos.validarFechaNoNula(fecha, "La fecha de la entrevista");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Entrevista))
            return false;
        Entrevista otherEntrevista = (Entrevista) other;
        return Objects.equals(entrevistador, otherEntrevista.entrevistador) &&
                Objects.equals(entrevistado, otherEntrevista.entrevistado) &&
                Objects.equals(lugar, otherEntrevista.lugar) &&
                Objects.equals(fecha, otherEntrevista.fecha);
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
