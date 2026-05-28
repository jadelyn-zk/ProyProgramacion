package es.iesnervion.revista.utilidades;

import es.iesnervion.revista.excepciones.*;
import es.iesnervion.revista.modelo.*;

/**
 * Método para validar que una revista cumple con los requisitos para publicarse
 * Cada método comprueba una cosa concreta y cuando falla lanza su excepcion correspondiente
 */
public final class ValidadorRevista {

    // Máximo y mínimo de páginas permitido para una revista completa.
    private static final int PAGINAS_MINIMAS = 10;
    private static final int PAGINAS_MAXIMAS = 50;

    // Máximo y mínimo de anuncios que debe incluir la revista.
    private static final int ANUNCIOS_MIN = 2;
    private static final int ANUNCIOS_MAX = 10;

    // Máximo y mínimo de articulos que debe incluir la revista.
    private static final int ARTICULOS_MIN = 1;
    private static final int ARTICULOS_MAX = 20;

    /**
     * Verifica que el total de páginas de la revista esté dentro del rango
     *
     * @param revista Revista a comprobar.
     * @return si el número total de páginas es válido devuelve true
     * @throws IllegalArgumentException si la revista no existe lanza excepcion
     * @throws LimitePaginasException si el total de páginas no está entre los límites configurados lanza excepcion
     */
    public static boolean validarTotalPaginas(Revista revista) throws LimitePaginasException {
        validarRevistaNoNula(revista);

        int total = calcularTotalPaginas(revista);
        if (total < PAGINAS_MINIMAS || total > PAGINAS_MAXIMAS) {
            throw new LimitePaginasException(
                    "La revista tiene " + total + " páginas y debe estar entre "
                            + PAGINAS_MINIMAS + " y " + PAGINAS_MAXIMAS + ".");
        }
        return true;
    }

    /**
     * Comprueba que la revista cumpla los mínimos por secciones y que no exceda los máximos
     *
     * @param revista Revista a comprobar.
     * @return si la distribución de contenido es válida devuelve true
     * @throws IllegalArgumentException si la revista no existe lanza excepcion
     * @throws MinimosNoCumplidosException si faltan entrevistas o anuncios mínimos lanza excepcion
     * @throws MaximosSuperadosException si se supera el número máximo de artículos o anuncios lanza excepcion
     */
    public static boolean validarMinimosPorSeccion(Revista revista)
            throws MinimosNoCumplidosException, MaximosSuperadosException {
        validarRevistaNoNula(revista);

        int entrevistas = contarEntrevistas(revista);
        int totalArticulos = revista.getArticulos().size();
        int totalAnuncios = revista.getAnuncios().size();

        if (totalArticulos > ARTICULOS_MAX || totalAnuncios > ANUNCIOS_MAX) {
            throw new MaximosSuperadosException(
                    "La revista supera los máximos permitidos: artículos=" + totalArticulos
                            + " (máximo " + ARTICULOS_MAX + ") y anuncios=" + totalAnuncios
                            + " (máximo " + ANUNCIOS_MAX + ").");
        }

        if (entrevistas < ARTICULOS_MIN || totalAnuncios < ANUNCIOS_MIN) {
            throw new MinimosNoCumplidosException(
                    "La revista no cumple los mínimos: entrevistas=" + entrevistas
                            + " (mínimo " + ARTICULOS_MIN + ") y anuncios=" + totalAnuncios
                            + " (mínimo " + ANUNCIOS_MIN + ").");
        }
        return true;
    }

    /**
     * Verifica que la portada tenga todos los elementos obligatorios
     *
     * @param portada Portada a comprobar
     * @return  si la portada está completa devuelve true
     * @throws PortadaIncompletaException si falta cualquier dato obligatorio de
     *         la portada
     */
    public static boolean validarPortadaCompleta(Portada portada)
            throws PortadaIncompletaException {
        if (portada == null
                || portada.getModelo() == null
                || portada.getFotografo() == null
                || portada.getMaquillador() == null
                || portada.getDiseñador() == null
                || !tieneTexto(portada.getTitular())
                || !tieneTexto(portada.getImagenUrl())) {
            throw new PortadaIncompletaException(
                "La portada debe incluir modelo, fotógrafo, maquillador, diseñador, titular e imagen."
            );
        }
        return true;
    }

    /**
     * Verifica que la carta del editor esté completa.
     *
     * @param carta Carta del editor a comprobar
     * @return si la carta contiene editor, mensaje y fecha devuelve true
     * @throws MinimosNoCumplidosException si la carta del editor está incompleta
     */
    public static boolean validarCartaEditorCompleta(CartaEditor carta)
            throws MinimosNoCumplidosException {
        if (carta == null
                || carta.getEditor() == null
                || !tieneTexto(carta.getMensaje())
                || carta.getFecha() == null) {
            throw new MinimosNoCumplidosException(
                    "La carta del editor debe incluir editor, mensaje y fecha."
            );
        }
        return true;
    }

    /**
     * Comprueba si la revista está lista para publicarse.
     * Esta validación agrupa portada, carta del editor, límites de secciones y
     * límites totales de páginas.
     *
     * @param revista Revista a comprobar.
     * @return {@code true} si la revista puede publicarse.
     * @throws IllegalArgumentException si la revista es {@code null}.
     * @throws RevistaException si alguna de las validaciones específicas falla.
     */
    public static boolean validarRevistaListaParaPublicar(Revista revista)
            throws RevistaException {
        validarRevistaNoNula(revista);

        validarPortadaCompleta(revista.getPortada());
        validarCartaEditorCompleta(revista.getCartaEditor());
        validarMinimosPorSeccion(revista);
        validarTotalPaginas(revista);

        return true;
    }

    /**
     * Calcula el total de páginas de la revista sumando portada y contenido.
     *
     * @param revista Revista a evaluar.
     * @return Número total de páginas.
     * @throws IllegalArgumentException si la revista es {@code null}.
     */
    public static int calcularTotalPaginas(Revista revista) {
        validarRevistaNoNula(revista);

        int total = 1;
        for (Articulo articulo : revista.getArticulos()) {
            total += articulo.getPaginasQueOcupa();
        }
        for (Anuncio anuncio : revista.getAnuncios()) {
            total += anuncio.getPaginasQueOcupa();
        }
        return total;
    }

    /**
     * Devuelve un resumen con los problemas que impiden publicar la revista.
     *
     * @param revista Revista a revisar.
     * @return Texto con los fallos encontrados o un mensaje de éxito si todo está correcto
     */
    public static String getResumenFaltantes(Revista revista) {
        if (revista == null) {
            return "La revista no puede ser nula.";
        }

        String resumen = "";

        if (revista.getPortada() == null) {
            resumen += "- Falta la portada\n";
        } else {
            if (revista.getPortada().getModelo() == null) {
                resumen += "- Falta el modelo de la portada\n";
            }
            if (revista.getPortada().getFotografo() == null) {
                resumen += "- Falta el fotógrafo de la portada\n";
            }
            if (revista.getPortada().getMaquillador() == null) {
                resumen += "- Falta el maquillador de la portada\n";
            }
            if (revista.getPortada().getDiseñador() == null) {
                resumen += "- Falta el diseñador de la portada\n";
            }
            if (!tieneTexto(revista.getPortada().getTitular())) {
                resumen += "- Falta el titular de la portada\n";
            }
            if (!tieneTexto(revista.getPortada().getImagenUrl())) {
                resumen += "- Falta la imagen de la portada\n";
            }
        }

        if (revista.getCartaEditor() == null) {
            resumen += "- Falta la carta del editor\n";
        } else {
            if (revista.getCartaEditor().getEditor() == null) {
                resumen += "- Falta el editor de la carta\n";
            }
            if (!tieneTexto(revista.getCartaEditor().getMensaje())) {
                resumen += "- Falta el mensaje de la carta del editor\n";
            }
            if (revista.getCartaEditor().getFecha() == null) {
                resumen += "- Falta la fecha de la carta del editor\n";
            }
        }

        int entrevistas = contarEntrevistas(revista);

        if (entrevistas < ARTICULOS_MIN) {
            resumen += "- Falta al menos una entrevista\n";
        }

        if (revista.getArticulos().size() > ARTICULOS_MAX) {
            resumen += "- Te has pasado del límite de artículos (máximo "
                + ARTICULOS_MAX + ")\n";
        }

        if (revista.getAnuncios().size() < ANUNCIOS_MIN) {
            resumen += "- Faltan anuncios\n";
        }

        if (revista.getAnuncios().size() > ANUNCIOS_MAX) {
            resumen += "- Demasiados anuncios (máximo " + ANUNCIOS_MAX + ")\n";
        }

        try {
            int totalPaginas = calcularTotalPaginas(revista);
            if (totalPaginas < PAGINAS_MINIMAS || totalPaginas > PAGINAS_MAXIMAS) {
                resumen += "- La revista tiene " + totalPaginas
                    + " páginas y debe estar entre " + PAGINAS_MINIMAS + " y "
                    + PAGINAS_MAXIMAS + "\n";
            }
        } catch (IllegalArgumentException ignored) {
            return "La revista no puede ser nula.";
        }

        return resumen.isEmpty() ? "Está todo listo." : resumen;
    }

    /**
     * Comprueba que la referencia a la revista no sea {@code null}.
     *
     * @param revista Revista a revisar.
     * @throws IllegalArgumentException si la revista es {@code null}.
     */
    private static void validarRevistaNoNula(Revista revista) {
        if (revista == null) {
            throw new IllegalArgumentException("La revista no puede ser nula.");
        }
    }

    /**
     * Cuenta cuántas entrevistas hay dentro de la revista.
     *
     * @param revista Revista a revisar.
     * @return Número de artículos de tipo entrevista.
     */
    private static int contarEntrevistas(Revista revista) {
        int entrevistas = 0;
        for (Articulo articulo : revista.getArticulos()) {
            if (articulo instanceof Entrevista) {
                entrevistas++;
            }
        }
        return entrevistas;
    }

    /**
     * Indica si un texto contiene contenido visible.
     *
     * @param texto Texto a evaluar.
     * @return {@code true} si el texto no es {@code null} ni está vacío.
     */
    private static boolean tieneTexto(String texto) {
        return texto != null && !texto.isBlank();
    }
}
