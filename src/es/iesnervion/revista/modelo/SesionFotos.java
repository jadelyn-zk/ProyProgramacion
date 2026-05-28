package es.iesnervion.revista.modelo;

import java.time.LocalDate;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Representa una sesión fotográfica para la revista.
 */
public class SesionFotos extends Articulo {

    // El modelo que posa en la sesión
    private Modelo modelo;

    // El fotógrafo encargado
    private Fotografo fotografo;

    // El maquillador que participa
    private Maquillador maquillador;

    // El diseñador de la ropa que se usa
    private Diseñador diseñador;

    // Lugar donde se han hecho las fotos
    private String localizacion;

    // Cuándo fue la sesión
    private LocalDate fecha;

    /**
     * Constructor por defecto de sesión de fotos.
     */
    public SesionFotos() {
        super();
    }

    /**
     * Crea una sesión fotográfica con todos los datos necesarios.
     *
     * @param titulo       Título de la sesión
     * @param modelo       Modelo que posa
     * @param fotografo    Fotógrafo encargado
     * @param maquillador  Maquillador participante
     * @param diseñador    Diseñador de la ropa
     * @param localizacion Lugar de la sesión
     * @param fecha        Fecha de la sesión
     */
    public SesionFotos(String titulo, Modelo modelo, Fotografo fotografo,
            Maquillador maquillador, Diseñador diseñador,
            String localizacion, LocalDate fecha) {
        super(titulo);
        setModelo(modelo);
        setFotografo(fotografo);
        setMaquillador(maquillador);
        setDiseñador(diseñador);
        setLocalizacion(localizacion);
        setFecha(fecha);
    }

    @Override
    public String getTipo() {
        return "SesionFotos";
    }

    // -- Getters y Setters --

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

    public String getLocalizacion() {
        return localizacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setModelo(Modelo modelo) {
        this.modelo = ValidacionDatos.validarNoNulo(modelo, "El modelo de la sesión");
    }

    public void setFotografo(Fotografo fotografo) {
        this.fotografo = ValidacionDatos.validarNoNulo(fotografo, "El fotógrafo de la sesión");
    }

    public void setMaquillador(Maquillador maquillador) {
        this.maquillador = ValidacionDatos.validarNoNulo(maquillador, "El maquillador de la sesión");
    }

    public void setDiseñador(Diseñador diseñador) {
        this.diseñador = ValidacionDatos.validarNoNulo(diseñador, "El diseñador de la sesión");
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = ValidacionDatos.validarTextoObligatorio(localizacion, "La localización");
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = ValidacionDatos.validarFechaNoNula(fecha, "La fecha de la sesión");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (!super.equals(other))
            return false;
        if (!(other instanceof SesionFotos))
            return false;
        SesionFotos otherSesion = (SesionFotos) other;
        return Objects.equals(modelo, otherSesion.modelo) &&
                Objects.equals(fotografo, otherSesion.fotografo) &&
                Objects.equals(localizacion, otherSesion.localizacion) &&
                Objects.equals(fecha, otherSesion.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), modelo, fotografo, localizacion, fecha);
    }

    @Override
    public String toString() {
        return "SesionFotos{" +
                "id=" + getId() +
                ", titulo='" + getTitulo() + '\'' +
                ", pagina=" + getPaginaInicio() +
                ", modelo=" + (modelo != null ? modelo.getNombre() : "s/n") +
                ", fotógrafo=" + (fotografo != null ? fotografo.getNombre() : "s/n") +
                '}';
    }
}
