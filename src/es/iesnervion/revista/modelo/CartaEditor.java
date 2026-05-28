package es.iesnervion.revista.modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import es.iesnervion.revista.utilidades.ValidacionDatos;

/**
 * Carta que escribe el editor al inicio.
 */
public class CartaEditor implements Serializable {

    private static final long serialVersionUID = 1L;

    // Editor responsable de la carta
    private Editor editor;

    // Texto de la carta
    private String mensaje;

    // Fecha de la carta
    private LocalDate fecha;

    /**
     * Constructor vacio.
     */
    public CartaEditor() {
    }

    /**
     * Constructor con los datos de la carta.
     *
     * @param editor Editor autor
     * @param mensaje Contenido de la carta
     * @param fecha   Fecha de escritura
     */
    public CartaEditor(Editor editor, String mensaje, LocalDate fecha) {
        setEditor(editor);
        setMensaje(mensaje);
        setFecha(fecha);
    }

    /**
     * Constructor simplificado solo con mensaje y fecha (para cargar desde archivo).
     * 
     * @param mensaje Contenido de la carta
     * @param fecha   Fecha de escritura
     */
    public CartaEditor(String mensaje, LocalDate fecha) {
        this.editor = null;
        setMensaje(mensaje);
        setFecha(fecha);
    }

    public Editor getEditor() {
        return editor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setEditor(Editor editor) {
        this.editor = ValidacionDatos.validarNoNulo(editor, "El editor de la carta");
    }

    public void setMensaje(String mensaje) {
        this.mensaje = ValidacionDatos.validarTextoObligatorio(mensaje, "El mensaje de la carta");
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = ValidacionDatos.validarFechaNoNula(fecha, "La fecha de la carta");
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        CartaEditor otherCarta = (CartaEditor) other;
        return Objects.equals(editor, otherCarta.editor) &&
                Objects.equals(mensaje, otherCarta.mensaje) &&
                Objects.equals(fecha, otherCarta.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(editor, mensaje, fecha);
    }

    @Override
    public String toString() {
        return "CartaEditor{" +
                "editor=" + (editor != null ? editor.getNombre() : "desconocido") +
                ", mensaje='" + (mensaje != null && mensaje.length() > 20 ? mensaje.substring(0, 20) + "..." : mensaje)
                + '\'' +
                '}';
    }
}
