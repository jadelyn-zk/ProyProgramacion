package es.iesnervion.revista.modelo;

import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa a un modelo.
 */
public class Modelo extends Persona {

    // Agencia a la que pertenece
    private String agencia;

    // Altura del modelo
    private double altura;

    // Talla de ropa
    private String talla;

    /**
     * Constructor por defecto.
     */
    public Modelo() {
        super();
    }

    /**
     * Constructor completo de `Modelo`.
     *
     * @param nombre Nombre completo
     * @param edad   Edad
     * @param agencia Agencia a la que pertenece
     * @param altura Altura en metros
     * @param talla  Talla de ropa
     */
    public Modelo(String nombre, int edad,
            String agencia, double altura, String talla) {
        super(nombre, edad);
        setAgencia(agencia);
        setAltura(altura);
        setTalla(talla);
    }

    public String getTalla() {
        return talla;
    }

    public String getAgencia() {
        return agencia;
    }

    public double getAltura() {
        return altura;
    }

    public void setAgencia(String agencia) {
        this.agencia = ValidacionDatos.validarTextoObligatorio(agencia, "La agencia");
    }

    public void setAltura(double altura) {
        this.altura = ValidacionDatos.validarDecimalPositivo(altura, "La altura");
    }

    public void setTalla(String talla) {
        this.talla = ValidacionDatos.validarTextoObligatorio(talla, "La talla");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof Modelo))
            return false;
        Modelo otherModelo = (Modelo) other;
        return Double.compare(otherModelo.altura, altura) == 0 &&
            Objects.equals(agencia, otherModelo.agencia) &&
            Objects.equals(talla, otherModelo.talla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), agencia, altura, talla);
    }

    @Override
    public String toString() {
        return "Modelo{" +
            "nombre='" + getNombre() + '\'' +
            ", agencia='" + agencia + '\'' +
            '}';
    }
}
