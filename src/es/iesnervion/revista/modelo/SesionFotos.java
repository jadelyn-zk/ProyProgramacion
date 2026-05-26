package es.iesnervion.revista.modelo;

import java.time.LocalDate;
import java.util.Objects;

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
     * Constructor por defecto.
     */
    public SesionFotos() {
        super();
    }

    /**
     * Constructor con todos los datos.
     */
    public SesionFotos(String titulo, Modelo modelo, Fotografo fotografo,
            Maquillador maquillador, Diseñador diseñador,
            String localizacion, LocalDate fecha) {
        super(titulo);
        this.modelo = modelo;
        this.fotografo = fotografo;
        this.maquillador = maquillador;
        this.diseñador = diseñador;
        this.localizacion = localizacion;
        this.fecha = fecha;
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

    public void setLocalizacion(String l) {
        this.localizacion = l;
    }

    public void setFecha(LocalDate fe) {
        this.fecha = fe;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!super.equals(o))
            return false;
        if (!(o instanceof SesionFotos))
            return false;
        SesionFotos s = (SesionFotos) o;
        return Objects.equals(modelo, s.modelo) &&
                Objects.equals(fotografo, s.fotografo) &&
                Objects.equals(localizacion, s.localizacion) &&
                Objects.equals(fecha, s.fecha);
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
