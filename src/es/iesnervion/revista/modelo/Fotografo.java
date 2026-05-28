package es.iesnervion.revista.modelo;

import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa a un fotógrafo.
 */
public class Fotografo extends Persona {

    // Estilo que suele usar en sus fotos
    private String estilo;

    /**
     * Constructor por defecto.
     */
    public Fotografo() {
        super();
    }

    /**
     * Constructor completo de `Fotografo`.
     *
     * @param nombre Nombre
     * @param edad   Edad
     * @param estilo Estilo fotográfico
     */
    public Fotografo(String nombre, int edad,
            String estilo) {
        super(nombre, edad);
        setEstilo(estilo);
    }

    public void setEstilo(String estilo) {
        this.estilo = ValidacionDatos.validarTextoObligatorio(estilo, "El estilo");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Fotografo))
            return false;
        Fotografo otherFotografo = (Fotografo) other;
        return Objects.equals(estilo, otherFotografo.estilo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), estilo);
    }

    @Override
    public String toString() {
        return "Fotografo{" +
                "nombre='" + getNombre() + '\'' +
                ", estilo='" + estilo + '\'' +
                '}';
    }
}
