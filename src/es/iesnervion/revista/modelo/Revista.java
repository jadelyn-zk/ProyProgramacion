package es.iesnervion.revista.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Modelo principal de la revista.
 * Guarda la portada, la carta del editor y el contenido que se va añadiendo.
 */
public class Revista implements Serializable {

    private static final long serialVersionUID = 1L;

    // (Se elimina mes y año: la revista es única y usa el titular de la portada como identificador)

    // Los datos de la portada
    private Portada portada;

    // El texto de la carta del editor
    private CartaEditor cartaEditor;

    // Colección de todos los artículos
    private List<Articulo> articulos;

    // Colección de los anuncios publicitarios
    private List<Anuncio> anuncios;

    /**
     * Constructor por defecto que inicializa las listas.
     */
    public Revista() {
        this.articulos = new ArrayList<>();
        this.anuncios = new ArrayList<>();
    }

    /**
     * Reparte las páginas del contenido.
     * La portada ocupa la primera y a partir de ahí se asigna el resto.
     */
    public void recalcularPaginas() {
        // Reservamos página 1 para la portada y página 2 para la carta del editor.
        // El contenido (artículos y anuncios) empieza en la página 3 y se
        // asigna secuencialmente respetando las páginas que ocupe cada elemento.
        int paginaActual = 3;

        for (Articulo a : articulos) {
            a.setPaginaInicio(paginaActual);
            paginaActual += a.getPaginasQueOcupa();
        }

        for (Anuncio a : anuncios) {
            a.setPagina(paginaActual);
            paginaActual += a.getPaginasQueOcupa();
        }
    }

    /**
     * Agrega un artículo y actualiza la numeración.
     * 
     * @param articulo El artículo a meter.
     */
    public void agregarArticulo(Articulo articulo) {
        articulos.add(ValidacionDatos.validarNoNulo(articulo, "El artículo"));
        recalcularPaginas();
    }

    /**
     * Borra un artículo por su ID.
     * 
     * @param id El ID del artículo.
     */
    public void eliminarArticulo(int id) {
        ValidacionDatos.validarEnteroPositivo(id, "El ID del artículo");
        articulos.removeIf(a -> a.getId() == id);
        recalcularPaginas();
    }

    /**
     * Agrega un anuncio.
     * 
     * @param anuncio El anuncio a meter.
     */
    public void agregarAnuncio(Anuncio anuncio) {
        anuncios.add(ValidacionDatos.validarNoNulo(anuncio, "El anuncio"));
        recalcularPaginas();
    }

    /**
     * Borra un anuncio por su ID.
     * 
     * @param id El ID del anuncio.
     */
    public void eliminarAnuncio(int id) {
        ValidacionDatos.validarEnteroPositivo(id, "El ID del anuncio");
        anuncios.removeIf(a -> a.getId() == id);
        recalcularPaginas();
    }

    // -- Getters --

    // No hay getters para mes/anio: atributos eliminados

    public Portada getPortada() {
        return portada;
    }

    public CartaEditor getCartaEditor() {
        return cartaEditor;
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Anuncio> getAnuncios() {
        return anuncios;
    }

    // -- Setters --

    // setMes/setAnio eliminados (no aplican)

    /**
     * Establece la portada de la revista y recalcula las páginas.
     *
     * @param portada Portada válida (no nula)
     */
    public void setPortada(Portada portada) {
        if (portada == null) {
            this.portada = null;
            return;
        }
        this.portada = ValidacionDatos.validarNoNulo(portada, "La portada");
        recalcularPaginas();
    }

    /**
     * Establece la carta del editor y recalcula las páginas.
     *
     * @param cartaEditor Carta válida (no nula)
     */
    public void setCartaEditor(CartaEditor cartaEditor) {
        if (cartaEditor == null) {
            this.cartaEditor = null;
            return;
        }
        this.cartaEditor = ValidacionDatos.validarNoNulo(cartaEditor, "La carta del editor");
        recalcularPaginas();
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Revista otherRevista = (Revista) other;
        return Objects.equals(portada, otherRevista.portada)
                && Objects.equals(cartaEditor, otherRevista.cartaEditor)
                && Objects.equals(articulos, otherRevista.articulos)
                && Objects.equals(anuncios, otherRevista.anuncios);
    }

    @Override
    public int hashCode() {
        return Objects.hash(portada, cartaEditor, articulos, anuncios);
    }

    @Override
    public String toString() {
        return "Revista{" +
                "portada=" + (portada != null ? portada.getTitular() : "(sin portada)") +
                ", totalPags=" + (1 + articulos.size() + anuncios.size()) +
                '}';
    }
}
