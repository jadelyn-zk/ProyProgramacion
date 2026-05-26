package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Un anuncio publicitario en la revista.
 */
public class Anuncio {

    // Para asignar IDs de forma ordenada
    private static int contadorId = 1;

    // El número de ID del anuncio
    private int id;

    // Nombre de la marca que se anuncia
    private String marca;

    // Página donde se publica
    private int pagina;

    // Lo que cuesta el anuncio
    private double precio;

    // Enlace a la imagen publicitaria
    private String imagenUrl;

    /**
     * Constructor vacio.
     */
    public Anuncio() {
        this.id = contadorId++;
    }

    /**
     * Constructor con los datos principales del anuncio.
     * 
     * @param marca     Marca.
     * @param precio    Precio.
     * @param imagenUrl URL de la imagen.
     */
    public Anuncio(String marca, double precio, String imagenUrl) {
        this.id = contadorId++;
        this.marca = marca;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getMarca() {
        return marca;
    }

    public int getPagina() {
        return pagina;
    }

    public double getPrecio() {
        return precio;
    }

    public int getPaginasQueOcupa() {
        return 1;
    }

    public String getImagenUrl() {
        return imagenUrl;
    }

    // Setters
    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setPagina(int pagina) {
        this.pagina = pagina;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Anuncio anuncio = (Anuncio) o;
        return id == anuncio.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Anuncio{" +
                "id=" + id +
                ", marca='" + marca + '\'' +
                ", pagina=" + pagina +
                ", precio=" + precio +
                '}';
    }
}
