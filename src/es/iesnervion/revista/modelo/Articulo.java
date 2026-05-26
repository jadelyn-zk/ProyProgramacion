package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Clase que representa un artículo de la revista.
 */
public abstract class Articulo {

    // Contador para generar los IDs
    private static int contadorId = 1;

    // El identificador único
    private int id;

    // El título de la sección o artículo
    private String titulo;

    // Página donde comienza (se calcula automáticamente)
    private int paginaInicio;

    /**
     * Constructor por defecto.
     */
    public Articulo() {
        this.id = contadorId++;
    }

    /**
     * Constructor con el título del artículo.
     * 
     * @param titulo El nombre o titular.
     */
    public Articulo(String titulo) {
        this.id = contadorId++;
        this.titulo = titulo;
    }

    /**
     * Devuelve el tipo de artículo que es (Entrevista, Reportaje, etc).
     * 
     * @return El nombre del tipo de contenido.
     */
    public abstract String getTipo();

    // Getters

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getPaginaInicio() {
        return paginaInicio;
    }

    // En este modelo cada artículo ocupa solo 1 página
    public int getPaginasQueOcupa() {
        return 1;
    }

    // Setters

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPaginaInicio(int paginaInicio) {
        this.paginaInicio = paginaInicio;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Articulo articulo = (Articulo) o;
        return id == articulo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Articulo{" +
                "id=" + id +
                ", tipo='" + getTipo() + '\'' +
                ", titulo='" + titulo + '\'' +
                ", pagina=" + paginaInicio +
                '}';
    }
}
