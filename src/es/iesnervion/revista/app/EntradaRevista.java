package es.iesnervion.revista.app;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import es.iesnervion.revista.modelo.*;

/**
 * Lecturas de consola y validaciones básicas para la entrada de datos.
 */
public final class EntradaRevista {

    private static final Scanner LECTOR = new Scanner(System.in);

    private EntradaRevista() {
    }

    /**
     * Pide una línea de texto.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return texto introducido.
     */
    public static String pedirLinea(String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = LECTOR.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Este campo no puede estar vacío.");
        }
    }

    /**
     * Lee un entero positivo.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return entero válido.
     */
    public static int leerEnteroPositivo(String mensaje) {
        while (true) {
            try {
                int valor = Integer.parseInt(pedirLinea(mensaje));
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Debe ser mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número entero válido.");
            }
        }
    }

    /**
     * Lee un índice de lista.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @param maximo tamaño máximo válido.
     * @return índice basado en cero o -1 si se cancela.
     */
    public static int leerIndice(String mensaje, int maximo) {
        while (true) {
            String texto = pedirLinea(mensaje);
            if (texto.equalsIgnoreCase("s")) {
                return -1;
            }
            try {
                int valor = Integer.parseInt(texto);
                if (valor >= 1 && valor <= maximo) {
                    return valor - 1;
                }
            } catch (NumberFormatException e) {
                // se informa abajo
            }
            System.out.println("Número no válido.");
        }
    }

    /**
     * Lee un decimal positivo.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return decimal válido.
     */
    public static double leerDecimalPositivo(String mensaje) {
        while (true) {
            try {
                double valor = Double.parseDouble(pedirLinea(mensaje).replace(',', '.'));
                if (valor > 0) {
                    return valor;
                }
                System.out.println("Debe ser mayor que cero.");
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número decimal válido.");
            }
        }
    }

    /**
     * Lee una fecha con formato YYYY-MM-DD.
     *
     * @param mensaje mensaje mostrado al usuario.
     * @return fecha válida.
     */
    public static LocalDate leerFecha(String mensaje) {
        while (true) {
            try {
                return LocalDate.parse(pedirLinea(mensaje));
            } catch (DateTimeParseException e) {
                System.out.println("Formato inválido. Usa YYYY-MM-DD.");
            }
        }
    }

    /**
     * Pide una portada.
     *
     * @return portada creada.
     */
    public static Portada pedirPortada() {
        Modelo modelo = pedirModelo();
        Fotografo fotografo = pedirFotografo();
        Maquillador maquillador = pedirMaquillador();
        Diseñador diseñador = pedirDiseñador();
        String titular = pedirLinea("Titular de la portada:");
        String imagen = pedirLinea("Imagen de portada:");
        return new Portada(modelo, fotografo, maquillador, diseñador, titular, imagen);
    }

    /**
     * Pide una carta del editor.
     *
     * @return carta creada.
     */
    public static CartaEditor pedirCartaEditor() {
        Editor editor = pedirEditor();
        String mensaje = pedirLinea("Mensaje de la carta:");
        LocalDate fecha = leerFecha("Fecha de la carta (YYYY-MM-DD):");
        return new CartaEditor(editor, mensaje, fecha);
    }

    /**
     * Pide una entrevista.
     *
     * @return entrevista creada.
     */
    public static Entrevista pedirEntrevista() {
        String titulo = pedirLinea("Título de la entrevista:");
        Entrevistador entrevistador = pedirEntrevistador();
        Modelo entrevistado = pedirModelo();
        String lugar = pedirLinea("Lugar:");
        LocalDate fecha = leerFecha("Fecha (YYYY-MM-DD):");
        return new Entrevista(titulo, entrevistador, entrevistado, lugar, fecha);
    }

    /**
     * Pide un reportaje.
     *
     * @return reportaje creado.
     */
    public static Reportaje pedirReportaje() {
        String titulo = pedirLinea("Título del reportaje:");
        String tematica = pedirLinea("Temática:");
        return new Reportaje(titulo, tematica);
    }

    /**
     * Pide una colección de moda.
     *
     * @return colección creada.
     */
    public static ColeccionModa pedirColeccionModa() {
        String titulo = pedirLinea("Título de la colección:");
        String temporada = pedirLinea("Temporada:");
        Diseñador diseñador = pedirDiseñador();
        String casaModa = pedirLinea("Casa de moda:");
        String ciudadDesfile = pedirLinea("Ciudad del desfile:");
        return new ColeccionModa(titulo, temporada, diseñador, casaModa, ciudadDesfile);
    }

    /**
     * Pide una sesión de fotos.
     *
     * @return sesión creada.
     */
    public static SesionFotos pedirSesionFotos() {
        String titulo = pedirLinea("Título de la sesión:");
        Modelo modelo = pedirModelo();
        Fotografo fotografo = pedirFotografo();
        Maquillador maquillador = pedirMaquillador();
        Diseñador diseñador = pedirDiseñador();
        String localizacion = pedirLinea("Localización:");
        LocalDate fecha = leerFecha("Fecha (YYYY-MM-DD):");
        return new SesionFotos(titulo, modelo, fotografo, maquillador, diseñador, localizacion, fecha);
    }

    /**
     * Pide un anuncio.
     *
     * @return anuncio creado.
     */
    public static Anuncio pedirAnuncio() {
        String marca = pedirLinea("Marca:");
        double precio = leerDecimalPositivo("Precio:");
        return new Anuncio(marca, precio);
    }

    /**
     * Pide los datos de un modelo.
     *
     * @return modelo creado.
     */
    public static Modelo pedirModelo() {
        String nombre = pedirLinea("Nombre del modelo:");
        int edad = leerEnteroPositivo("Edad del modelo:");
        String agencia = pedirLinea("Agencia:");
        double altura = leerDecimalPositivo("Altura:");
        String talla = pedirLinea("Talla:");
        return new Modelo(nombre, edad, agencia, altura, talla);
    }

    /**
     * Pide los datos de un fotógrafo.
     *
     * @return fotógrafo creado.
     */
    public static Fotografo pedirFotografo() {
        String nombre = pedirLinea("Nombre del fotógrafo:");
        int edad = leerEnteroPositivo("Edad del fotógrafo:");
        String estilo = pedirLinea("Estilo:");
        return new Fotografo(nombre, edad, estilo);
    }

    /**
     * Pide los datos de un maquillador.
     *
     * @return maquillador creado.
     */
    public static Maquillador pedirMaquillador() {
        String nombre = pedirLinea("Nombre del maquillador:");
        int edad = leerEnteroPositivo("Edad del maquillador:");
        String especialidad = pedirLinea("Especialidad:");
        return new Maquillador(nombre, edad, especialidad);
    }

    /**
     * Pide los datos de un diseñador.
     *
     * @return diseñador creado.
     */
    public static Diseñador pedirDiseñador() {
        String nombre = pedirLinea("Nombre del diseñador:");
        int edad = leerEnteroPositivo("Edad del diseñador:");
        String estilo = pedirLinea("Estilo:");
        String casaActual = pedirLinea("Casa actual:");
        return new Diseñador(nombre, edad, estilo, casaActual);
    }

    /**
     * Pide los datos de un editor.
     *
     * @return editor creado.
     */
    public static Editor pedirEditor() {
        String nombre = pedirLinea("Nombre del editor:");
        int edad = leerEnteroPositivo("Edad del editor:");
        String cargo = pedirLinea("Cargo:");
        return new Editor(nombre, edad, cargo);
    }

    /**
     * Pide los datos de un entrevistador.
     *
     * @return entrevistador creado.
     */
    public static Entrevistador pedirEntrevistador() {
        String nombre = pedirLinea("Nombre del entrevistador:");
        int edad = leerEnteroPositivo("Edad del entrevistador:");
        String medio = pedirLinea("Medio:");
        return new Entrevistador(nombre, edad, medio);
    }

    /**
     * Lista los artículos.
     *
     * @param articulos lista de artículos.
     */
    public static void listarArticulos(ArrayList<Articulo> articulos) {
        for (int indice = 0; indice < articulos.size(); indice++) {
            System.out.println((indice + 1) + ") " + articulos.get(indice));
        }
    }

    /**
     * Lista los anuncios.
     *
     * @param anuncios lista de anuncios.
     */
    public static void listarAnuncios(ArrayList<Anuncio> anuncios) {
        for (int indice = 0; indice < anuncios.size(); indice++) {
            System.out.println((indice + 1) + ") " + anuncios.get(indice));
        }
    }
}
