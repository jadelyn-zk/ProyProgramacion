package es.iesnervion.revista.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Clase principal que gestiona todo el contenido de una revista.
 */
public class Revista {

    // El número de esta edición
    private int numero;

    // Mes en el que se publica
    private Mes mes;

    // Año de la edición
    private int anio;

    // El nombre o título de la revista
    private String titulo;

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
     * Constructor con los datos de publicación.
     */
    public Revista(int numero, Mes mes, int anio, String titulo) {
        this.numero = numero;
        this.mes = mes;
        this.anio = anio;
        this.titulo = titulo;
        this.articulos = new ArrayList<>();
        this.anuncios = new ArrayList<>();
    }

    /**
     * Calcula las páginas de forma automática.
     * La portada y carta van al principio.
     * Los artículos y anuncios van después consecutivamente.
     */
    public void recalcularPaginas() {
        int paginaActual = 2; // El contenido "real" empieza en la 2

        for (Articulo a : articulos) {
            a.setPaginaInicio(paginaActual++);
        }

        for (Anuncio a : anuncios) {
            a.setPagina(paginaActual++);
        }
    }

    /**
     * Agrega un artículo y actualiza la numeración.
     * 
     * @param articulo El artículo a meter.
     */
    public void agregarArticulo(Articulo articulo) {
        if (articulo != null) {
            articulos.add(articulo);
            recalcularPaginas();
        }
    }

    /**
     * Borra un artículo por su ID.
     * 
     * @param id El ID del artículo.
     */
    public void eliminarArticulo(int id) {
        articulos.removeIf(a -> a.getId() == id);
        recalcularPaginas();
    }

    /**
     * Agrega un anuncio.
     * 
     * @param anuncio El anuncio a meter.
     */
    public void agregarAnuncio(Anuncio anuncio) {
        if (anuncio != null) {
            anuncios.add(anuncio);
            recalcularPaginas();
        }
    }

    /**
     * Borra un anuncio por su ID.
     * 
     * @param id El ID del anuncio.
     */
    public void eliminarAnuncio(int id) {
        anuncios.removeIf(a -> a.getId() == id);
        recalcularPaginas();
    }

    // -- Getters --

    public int getNumero() {
        return numero;
    }

    public Mes getMes() {
        return mes;
    }

    public int getAnio() {
        return anio;
    }

    public String getTitulo() {
        return titulo;
    }

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

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setMes(Mes mes) {
        this.mes = mes;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setPortada(Portada p) {
        this.portada = p;
        recalcularPaginas();
    }

    public void setCartaEditor(CartaEditor c) {
        this.cartaEditor = c;
        recalcularPaginas();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Revista revista = (Revista) o;
        return numero == revista.numero && anio == revista.anio && mes == revista.mes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numero, mes, anio);
    }

    @Override
    public String toString() {
        return "Revista{" +
                "titulo='" + titulo + '\'' +
                ", numero=" + numero +
                ", mes=" + mes +
                ", anio=" + anio +
                ", totalPags=" + (1 + articulos.size() + anuncios.size()) +
                '}';
    }
}
