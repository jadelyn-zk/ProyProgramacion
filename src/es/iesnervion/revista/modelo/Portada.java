package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Representa la portada de la revista.
 */
public class Portada {

    // El modelo principal
    private Modelo modelo;

    // El fotógrafo de portada
    private Fotografo fotografo;

    // El maquillador
    private Maquillador maquillador;

    // El diseñador de la ropa
    private Diseñador diseñador;

    // El titular o mensaje central
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
        this.modelo = modelo;
        this.fotografo = fotografo;
        this.maquillador = maquillador;
        this.diseñador = diseñador;
        this.titular = titular;
        this.imagenUrl = imagenUrl;
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
    public void setModelo(Modelo m) {
        this.modelo = m;
    }

    public void setFotografo(Fotografo f) {
        this.fotografo = f;
    }

    public void setMaquillador(Maquillador mq) {
        this.maquillador = mq;
    }

    public void setDiseñador(Diseñador d) {
        this.diseñador = d;
    }

    public void setTitular(String t) {
        this.titular = t;
    }

    public void setImagenUrl(String img) {
        this.imagenUrl = img;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Portada portada = (Portada) o;
        return Objects.equals(titular, portada.titular) &&
                Objects.equals(imagenUrl, portada.imagenUrl);
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
