package es.iesnervion.revista.modelo;

import java.io.Serializable;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa la portada de la revista.
 */
public class Portada implements Serializable {

    private static final long serialVersionUID = 1L;

    // El modelo principal
    private Modelo modelo;

    // El fotógrafo de portada
    private Fotografo fotografo;

    // El maquillador
    private Maquillador maquillador;

    // El diseñador de la ropa
    private Diseñador diseñador;

    // Titular principal de la portada
    private String titular;

    // Ruta a la imagen de portada
    private String imagenUrl;

    /**
     * Constructor por defecto.
     */
    public Portada() {
    }

    /**
     * Constructor con todos los campos.
     * 
     * @param modelo      Modelo.
     * @param fotografo   Fotógrafo.
     * @param maquillador Maquillador.
     * @param diseñador   Diseñador.
     * @param titular     Titular.
     * @param imagenUrl   URL de la imagen.
     */
    public Portada(Modelo modelo, Fotografo fotografo, Maquillador maquillador,
            Diseñador diseñador, String titular, String imagenUrl) {
        setModelo(modelo);
        setFotografo(fotografo);
        setMaquillador(maquillador);
        setDiseñador(diseñador);
        setTitular(titular);
        setImagenUrl(imagenUrl);
    }

    /**
     * Constructor simplificado solo con titular e imagen (para cargar desde archivo).
     * 
     * @param titular   Titular de la portada.
     * @param imagenUrl URL de la imagen.
     */
    public Portada(String titular, String imagenUrl) {
        this.modelo = null;
        this.fotografo = null;
        this.maquillador = null;
        this.diseñador = null;
        setTitular(titular);
        setImagenUrl(imagenUrl);
    }

    // -- Getters --
    public Modelo getModelo() {
        return modelo;
    }

    public Fotografo getFotografo() {
        return fotografo;
    }

    public Maquillador getMaquillador() {
        return maquillador;
    }

    public Diseñador getDiseñador() {
        return diseñador;
    }

    public String getTitular() {
        return titular;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    // -- Setters --
    public void setModelo(Modelo modelo) {
        this.modelo = ValidacionDatos.validarNoNulo(modelo, "El modelo de portada");
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = ValidacionDatos.validarNoNulo(fotografo, "El fotógrafo de portada");
    }

    public void setMaquillador(Maquillador maquillador) {
        this.maquillador = ValidacionDatos.validarNoNulo(maquillador, "El maquillador de portada");
    }

    public void setDiseñador(Diseñador diseñador) {
        this.diseñador = ValidacionDatos.validarNoNulo(diseñador, "El diseñador de portada");
    }

    public void setTitular(String titular) {
        this.titular = ValidacionDatos.validarTextoObligatorio(titular, "El titular de portada");
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = ValidacionDatos.validarTextoObligatorio(imagenUrl, "La imagen de portada");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Portada otherPortada = (Portada) other;
        return Objects.equals(titular, otherPortada.titular) &&
                Objects.equals(imagenUrl, otherPortada.imagenUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(titular, imagenUrl);
    }

    @Override
    public String toString() {
        return "Portada{" +
                "titular='" + titular + '\'' +
                ", modelo=" + (modelo != null ? modelo.getNombre() : "no asignado") +
                '}';
    }
}
