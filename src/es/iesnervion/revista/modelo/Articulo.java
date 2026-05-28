package es.iesnervion.revista.modelo;

import java.io.Serializable;
import java.util.Objects;
import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Clase que representa un artículo de la revista.
 */
public abstract class Articulo implements Serializable {

    private static final long serialVersionUID = 1L;

    // Contador para generar los IDs
    private static int contadorId = 1;

    // id única
    private int id;

    // título de la sección o artículo
    private String titulo;

    // Página donde comienza (se calcula automáticamente)
    private int paginaInicio;

    /**
     * Constructor por defecto de un artículo que asigna la id
     */
    public Articulo() {
        this.id = contadorId++;
    }

    /**
     * Crea un artículo con su título.
     *
     * @param titulo Título del artículo (texto obligatorio)
     */
    public Articulo(String titulo) {
        this.id = contadorId++;
        setTitulo(titulo);
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

    // Cada artículo ocupa solo 1 página
    public int getPaginasQueOcupa() {
        return 1;
    }

    public static void ajustarContadorId(int siguienteId) {
        contadorId = Math.max(1, siguienteId);
    }

    // Setters

    public void setTitulo(String titulo) {
        this.titulo = ValidacionDatos.validarTextoObligatorio(titulo, "El título");
    }

    public void setPaginaInicio(int paginaInicio) {
        this.paginaInicio = ValidacionDatos.validarEnteroPositivo(paginaInicio, "La página de inicio");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Articulo otherArticulo = (Articulo) other;
        return id == otherArticulo.id;
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
