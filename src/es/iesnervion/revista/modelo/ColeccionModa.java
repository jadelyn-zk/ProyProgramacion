package es.iesnervion.revista.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * Constructor vacio.
     */
    public ColeccionModa() {
        super();
        this.modelos = new ArrayList<>();
    }

    /**
     * Constructor con datos básicos.
     */
    public ColeccionModa(String titulo, String temporada, Diseñador diseñador,
            String casaModa, String ciudadDesfile) {
        super(titulo);
        this.temporada = temporada;
        this.diseñador = diseñador;
        this.casaModa = casaModa;
        this.ciudadDesfile = ciudadDesfile;
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

    public void setTemporada(String t) {
        this.temporada = t;
    }

    public void setDiseñador(Diseñador d) {
        this.diseñador = d;
    }

    public void setCasaModa(String c) {
        this.casaModa = c;
    }

    public void setCiudadDesfile(String ci) {
        this.ciudadDesfile = ci;
    }

    public void setModelos(List<Modelo> m) {
        this.modelos = m;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof ColeccionModa))
            return false;
        ColeccionModa c = (ColeccionModa) o;
        return Objects.equals(temporada, c.temporada) &&
                Objects.equals(casaModa, c.casaModa);
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
