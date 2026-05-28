package es.iesnervion.revista.utilidades;

import java.time.LocalDate;
import java.util.List;

/**
 * Utilidad de validación para entradas de usuario.
 * <p>
 * Proporciona métodos auxiliares para validar textos obligatorios, números
 * positivos, fechas y listas. En caso de fallo lanza {@link IllegalArgumentException}
 * con un mensaje claro y apropiado para mostrarse al usuario.
 * </p>
 */
public final class ValidacionDatos {

    private ValidacionDatos() {
    }

    /**
     * Valida que un texto no sea nulo ni vacío.
     *
     * @param valor Texto a validar
     * @param campo Nombre del campo (usado en el mensaje de error)
     * @return el mismo texto validado
     * @throws IllegalArgumentException si el texto es nulo o vacío
     */
    public static String validarTextoObligatorio(String valor, String campo) {
        if (valor == null || valor.isBlank()) {
            throw new IllegalArgumentException(campo + " no puede estar vacío.");
        }
        return valor;
    }

    /**
     * Valida que un entero sea mayor que cero.
     *
     * @param valor Valor a validar
     * @param campo Nombre del campo (usado en el mensaje de error)
     * @return el mismo entero validado
     * @throws IllegalArgumentException si el valor es menor o igual a cero
     */
    public static int validarEnteroPositivo(int valor, String campo) {
        if (valor <= 0) {
            throw new IllegalArgumentException(campo + " debe ser mayor que 0.");
        }
        return valor;
    }

    /**
     * Valida que un número decimal no sea negativo.
     *
     * @param valor Valor a validar
     * @param campo Nombre del campo (usado en el mensaje de error)
     * @return el mismo valor validado
     * @throws IllegalArgumentException si el valor es negativo
     */
    public static double validarDecimalPositivo(double valor, String campo) {
        if (valor < 0) {
            throw new IllegalArgumentException(campo + " no puede ser negativo.");
        }
        return valor;
    }

    /**
     * Valida que una fecha no sea nula.
     *
     * @param valor Fecha a validar
     * @param campo Nombre del campo (usado en el mensaje de error)
     * @return la fecha validada
     * @throws IllegalArgumentException si la fecha es nula
     */
    public static LocalDate validarFechaNoNula(LocalDate valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException(campo + " no puede ser nula.");
        }
        return valor;
    }

    /**
     * Valida que un objeto no sea nulo.
     *
     * @param <T>   Tipo del objeto
     * @param valor Objeto a validar
     * @param campo Nombre del campo (usado en el mensaje de error)
     * @return el mismo objeto si no es nulo
     * @throws IllegalArgumentException si el objeto es nulo
     */
    public static <T> T validarNoNulo(T valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException(campo + " no puede ser nulo.");
        }
        return valor;
    }

    /**
     * Valida que una lista no sea nula.
     *
     * @param <T>   Tipo de elementos
     * @param valor Lista a validar
     * @param campo Nombre del campo (usado en el mensaje de error)
     * @return la misma lista si no es nula
     * @throws IllegalArgumentException si la lista es nula
     */
    public static <T> List<T> validarListaNoNula(List<T> valor, String campo) {
        if (valor == null) {
            throw new IllegalArgumentException(campo + " no puede ser nula.");
        }
        return valor;
    }
}