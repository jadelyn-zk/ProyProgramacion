package es.iesnervion.revista.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa una colección de moda dentro de la revista.
 */
public class ColeccionModa extends Articulo {

    // Temporada de la colección (Verano, Invierno, etc)
    private String temporada;

    // Diseñador de las prendas
    private Diseñador diseñador;

    // La marca o casa de moda
    private String casaModa;

    // En qué ciudad se presentó
    private String ciudadDesfile;

    // Modelos que han participado
    private List<Modelo> modelos;

    /**
     * Constructor por defecto de `ColeccionModa`.
     */
    public ColeccionModa() {
        super();
        this.modelos = new ArrayList<>();
    }

    /**
     * Crea una colección de moda con datos básicos.
     *
     * @param titulo        Título del artículo
     * @param temporada     Temporada (Verano, Invierno...)
     * @param diseñador     Diseñador responsable
     * @param casaModa      Casa o marca de moda
     * @param ciudadDesfile Ciudad donde se presentó
     */
    public ColeccionModa(String titulo, String temporada, Diseñador diseñador,
            String casaModa, String ciudadDesfile) {
        super(titulo);
        setTemporada(temporada);
        setDiseñador(diseñador);
        setCasaModa(casaModa);
        setCiudadDesfile(ciudadDesfile);
        this.modelos = new ArrayList<>();
    }

    @Override
    public String getTipo() {
        return "ColeccionModa";
    }

    // Getters y Setters

    public String getTemporada() {
        return temporada;
    }

    public Diseñador getDiseñador() {
        return diseñador;
    }

    public String getCasaModa() {
        return casaModa;
    }

    public String getCiudadDesfile() {
        return ciudadDesfile;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setTemporada(String temporada) {
        this.temporada = ValidacionDatos.validarTextoObligatorio(temporada, "La temporada");
    }

    public void setDiseñador(Diseñador diseñador) {
        this.diseñador = ValidacionDatos.validarNoNulo(diseñador, "El diseñador de la colección");
    }

    public void setCasaModa(String casaModa) {
        this.casaModa = ValidacionDatos.validarTextoObligatorio(casaModa, "La casa de moda");
    }

    public void setCiudadDesfile(String ciudadDesfile) {
        this.ciudadDesfile = ValidacionDatos.validarTextoObligatorio(ciudadDesfile, "La ciudad del desfile");
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = ValidacionDatos.validarListaNoNula(modelos, "La lista de modelos");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof ColeccionModa))
            return false;
        ColeccionModa otherColeccion = (ColeccionModa) other;
        return Objects.equals(temporada, otherColeccion.temporada) &&
                Objects.equals(casaModa, otherColeccion.casaModa);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), temporada, casaModa);
    }

    @Override
    public String toString() {
        return "ColeccionModa{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", pagina=" + getPaginaInicio() +
                ", temporada='" + temporada + '\'' +
                '}';
    }
}
