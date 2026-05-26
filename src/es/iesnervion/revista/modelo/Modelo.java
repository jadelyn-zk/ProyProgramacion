package es.iesnervion.revista.modelo;

import java.util.Objects;

/**
 * Representa a un modelo.
 */
public class Modelo extends Persona {

    // La agencia a la cual pertenece
    private String agencia;

    // Lo que mide el modelo
    private double altura;

    // Su talla de ropa
    private String talla;

    /**
     * Constructor por defecto.
     */
    public Modelo() {
        super();
    }

    /**
     * Constructor con todos los campos.
     */
    public Modelo(String dni, String nombre, String tlf, String email, int edad,
            String agencia, double altura, String talla) {
        super(dni, nombre, tlf, email, edad);
        this.agencia = agencia;
        this.altura = altura;
        this.talla = talla;
    }

    // -- Métodos de acceso --

    public String getAgencia() {
        return agencia;
    }

    public double getAltura() {
        return altura;
    }

    public String getTalla() {
        return talla;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public void setAltura(double altura) {
        this.altura = altura;
    }

    public void setTalla(String talla) {
        this.talla = talla;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof Modelo))
            return false;
        Modelo modelo = (Modelo) o;
        return Double.compare(modelo.altura, altura) == 0 &&
                Objects.equals(agencia, modelo.agencia) &&
                Objects.equals(talla, modelo.talla);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), agencia, altura, talla);
    }

    @Override
    public String toString() {
        return "Modelo{" +
                "dni='" + getDni() + '\'' +
                ", nombre='" + getNombre() + '\'' +
                ", agencia='" + agencia + '\'' +
                '}';
    }
}
