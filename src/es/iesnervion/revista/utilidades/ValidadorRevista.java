package es.iesnervion.revista.utilidades;

import es.iesnervion.revista.modelo.*;

/**
 * Clase para validar que la revista cumpla los mínimos antes de publicar.
 */
public class ValidadorRevista {

    // Mínimos y máximos para las validaciones
    private static final int PAGINAS_MINIMAS = 10;
    private static final int PAGINAS_MAXIMAS = 50;
    private static final int ANUNCIOS_MIN = 2;
    private static final int ANUNCIOS_MAX = 10;
    private static final int ENTREVISTAS_MIN = 1;
    private static final int ARTICULOS_MAX = 20;

    /**
     * Constructor privado.
     */
    private ValidadorRevista() {
    }

    /**
     * Mira si el total de páginas está dentro del rango.
     */
    public static boolean validarTotalPaginas(Revista revista) {
        int total = calcularTotalPaginas(revista);
        return total >= PAGINAS_MINIMAS && total <= PAGINAS_MAXIMAS;
    }

    /**
     * Mira si hay suficientes anuncios y entrevistas, y que no nos pasemos de
     * artículos.
     */
    public static boolean validarMinimosPorSeccion(Revista revista) {
        int entrevistas = 0;
        for (Articulo a : revista.getArticulos()) {
            if (a instanceof Entrevista)
                entrevistas++;
        }

        boolean cumpleMinimos = entrevistas >= ENTREVISTAS_MIN
                && revista.getAnuncios().size() >= ANUNCIOS_MIN;

        boolean cumpleMaximoArticulos = revista.getArticulos().size() <= ARTICULOS_MAX;

        return cumpleMinimos && cumpleMaximoArticulos;
    }

    /**
     * Comprueba si la portada tiene el titular puesto.
     */
    public static boolean validarPortadaCompleta(Portada portada) {
        if (portada == null)
            return false;
        return portada.getTitular() != null && !portada.getTitular().isBlank();
    }

    /**
     * Comprueba si la carta del editor tiene texto.
     */
    public static boolean validarCartaEditorCompleta(CartaEditor carta) {
        if (carta == null)
            return false;
        return carta.getMensaje() != null && !carta.getMensaje().isBlank();
    }

    /**
     * Validación completa para ver si se puede publicar.
     */
    public static boolean validarRevistaListaParaPublicar(Revista revista) {
        return validarPortadaCompleta(revista.getPortada())
                && validarCartaEditorCompleta(revista.getCartaEditor())
                && validarMinimosPorSeccion(revista);
    }

    /**
     * Calcula cuántas páginas tiene la revista en total.
     */
    public static int calcularTotalPaginas(Revista revista) {
        return 1 + revista.getArticulos().size() + revista.getAnuncios().size();
    }

    /**
     * Devuelve una lista de los fallos encontrados.
     */
    public static String getResumenFaltantes(Revista revista) {
        StringBuilder sb = new StringBuilder();
        if (revista.getPortada() == null)
            sb.append("- Falta la portada\n");
        if (revista.getCartaEditor() == null)
            sb.append("- Falta la carta del editor\n");

        int entrevistas = 0;
        for (Articulo a : revista.getArticulos()) {
            if (a instanceof Entrevista)
                entrevistas++;
        }

        if (entrevistas < ENTREVISTAS_MIN)
            sb.append("- Falta al menos una entrevista\n");

        if (revista.getArticulos().size() > ARTICULOS_MAX)
            sb.append("- Te has pasado del límite de artículos (máximo " + ARTICULOS_MAX + ")\n");

        if (revista.getAnuncios().size() < ANUNCIOS_MIN)
            sb.append("- Faltan anuncios\n");

        if (revista.getAnuncios().size() > ANUNCIOS_MAX)
            sb.append("- Demasiados anuncios (máximo " + ANUNCIOS_MAX + ")\n");

        return sb.length() == 0 ? "Está todo listo." : sb.toString();
    }
}
