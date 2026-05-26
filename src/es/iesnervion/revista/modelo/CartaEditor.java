package es.iesnervion.revista.modelo;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Representa la carta que escribe el editor al inicio.
 */
public class CartaEditor {

    // El editor responsable de la carta
    private Editor editor;

    // El texto de la carta
    private String mensaje;

    // Fecha en la que se escribió
    private LocalDate fecha;

    /**
     * Constructor vacio.
     */
    public CartaEditor() {
    }

    /**
     * Constructor con los datos principales.
     * 
     * @param editor  El editor autor.
     * @param mensaje El contenido.
     * @param fecha   La fecha.
     */
    public CartaEditor(Editor editor, String mensaje, LocalDate fecha) {
        this.editor = editor;
        this.mensaje = mensaje;
        this.fecha = fecha;
    }

    // Getters

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
        this.editor = editor;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CartaEditor that = (CartaEditor) o;
        return Objects.equals(editor, that.editor) &&
                Objects.equals(mensaje, that.mensaje) &&
                Objects.equals(fecha, that.fecha);
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
