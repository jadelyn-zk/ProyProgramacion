package es.iesnervion.revista.modelo;

import java.io.Serializable;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Un anuncio publicitario en la revista.
 */
public class Anuncio implements Serializable {

    private static final long serialVersionUID = 1L;

    // Contador interno para asignar IDs
    private static int contadorId = 1;

    // ID del anuncio
    private int id;

    // Marca que se anuncia
    private String marca;

    // Página donde se publica
    private int pagina;

    // Precio del anuncio
    private double precio;

    /**
     * Constructor por defecto para un anuncio (asigna ID automático).
     */
    public Anuncio() {
        this.id = contadorId++;
    }

    /**
     * Construye un anuncio con los datos básicos.
     *
     * @param marca  Marca anunciada
     * @param precio Precio del anuncio (decimal no negativo)
     */
    public Anuncio(String marca, double precio) {
        this.id = contadorId++;
        setMarca(marca);
        setPrecio(precio);
    }

    public int getId() {
        return id;
    }

    public static void ajustarContadorId(int siguienteId) {
        contadorId = Math.max(1, siguienteId);
    }

    public int getPaginasQueOcupa() {
        return 1;
    }

    public void setMarca(String marca) {
        this.marca = ValidacionDatos.validarTextoObligatorio(marca, "La marca");
    }

    public void setPagina(int pagina) {
        this.pagina = ValidacionDatos.validarEnteroPositivo(pagina, "La página del anuncio");
    }

    public void setPrecio(double precio) {
        this.precio = ValidacionDatos.validarDecimalPositivo(precio, "El precio");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Anuncio otherAnuncio = (Anuncio) other;
        return id == otherAnuncio.id;
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
