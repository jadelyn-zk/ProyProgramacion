package es.iesnervion.revista.ui;

import es.iesnervion.revista.modelo.*;
import es.iesnervion.revista.utilidades.ValidadorRevista;
import java.util.Scanner;
import java.time.LocalDate;

/**
 * Clase principal de la aplicación para gestionar la revista.
 */
public class Main {
    // Escáner para leer por teclado
    private static Scanner scanner = new Scanner(System.in);

    // El objeto revista con el que trabajamos
    private static Revista revista;

    /**
     * Método main donde empieza todo.
     */
    public static void main(String[] args) {
        inicializarRevista();

        int opcion;
        do {
            mostrarMenuPrincipal();
            opcion = leerEntero("Seleccione una opción: ");

            switch (opcion) {
                case 1:
                    gestionarArticulos();
                    break;
                case 2:
                    gestionarAnuncios();
                    break;
                case 3:
                    configurarPortada();
                    break;
                case 4:
                    configurarCartaEditor();
                    break;
                case 5:
                    mostrarResumenRevista();
                    break;
                case 6:
                    validarRevista();
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 0);
    }

    /**
     * Crea la revista pidiendo los datos básicos.
     */
    private static void inicializarRevista() {
        System.out.println("=== BIENVENIDO AL GESTOR DE REVISTAS ===");
        String titulo = leerCadena("Introduce el título de la revista: ");
        int anio = leerEntero("Introduce el año: ");
        revista = new Revista(1, Mes.MAYO, anio, titulo);
    }

    /**
     * Muestra el menú de opciones.
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- GESTIÓN DE REVISTA [" + revista.getTitulo().toUpperCase() + "] ---");
        System.out.println("1. Gestionar Artículos");
        System.out.println("2. Gestionar Anuncios");
        System.out.println("3. Editar Portada");
        System.out.println("4. Editar Carta del Editor");
        System.out.println("5. Ver estado actual");
        System.out.println("6. Validar si se puede publicar");
        System.out.println("0. Salir");
    }

    /**
     * Submenú para crear, borrar o listar artículos.
     */
    private static void gestionarArticulos() {
        System.out.println("\n1. Listar artículos, 2. Añadir artículo, 3. Borrar artículo");
        int op = leerEntero("Opción: ");
        if (op == 1) {
            if (revista.getArticulos().isEmpty())
                System.out.println("No hay ningún artículo.");
            for (Articulo a : revista.getArticulos())
                System.out.println(a);
        } else if (op == 2) {
            System.out.println("\n--- AÑADIR NUEVO ARTÍCULO ---");
            String titulo = leerCadena("Título del artículo: ");
            System.out.println("Tipo: 1. Entrevista, 2. Reportaje, 3. Colección, 4. Sesión");
            int tipo = leerEntero("Seleccione tipo: ");

            Articulo nuevo = null;

            switch (tipo) {
                case 1: // Entrevista
                    System.out.println("> Datos del Entrevistador:");
                    Entrevistador ev = new Entrevistador();
                    ev.setNombre(leerCadena("  Nombre: "));
                    ev.setDni(leerCadena("  DNI: "));

                    System.out.println("> Datos del Entrevistado:");
                    Modelo ed = new Modelo(); // Usamos modelo como ejemplo de persona
                    ed.setNombre(leerCadena("  Nombre: "));
                    ed.setDni(leerCadena("  DNI: "));

                    String lugarE = leerCadena("Lugar de la entrevista: ");
                    nuevo = new Entrevista(titulo, ev, ed, lugarE, LocalDate.now());
                    break;

                case 2: // Reportaje
                    String tema = leerCadena("Temática del reportaje: ");
                    nuevo = new Reportaje(titulo, tema);
                    break;

                case 3: // Colección de Moda
                    String temp = leerCadena("Temporada: ");
                    System.out.println("> Datos del Diseñador:");
                    Diseñador dis = new Diseñador();
                    dis.setNombre(leerCadena("  Nombre: "));
                    dis.setDni(leerCadena("  DNI: "));

                    String casa = leerCadena("Casa de moda: ");
                    String ciudad = leerCadena("Ciudad del desfile: ");
                    nuevo = new ColeccionModa(titulo, temp, dis, casa, ciudad);
                    break;

                case 4: // Sesión de Fotos
                    System.out.println("> Datos del equipo:");
                    Modelo mod = new Modelo();
                    mod.setNombre(leerCadena("  Nombre del Modelo: "));

                    Fotografo fot = new Fotografo();
                    fot.setNombre(leerCadena("  Nombre del Fotógrafo: "));

                    Maquillador maq = new Maquillador();
                    maq.setNombre(leerCadena("  Nombre del Maquillador: "));

                    String loc = leerCadena("Localización de la sesión: ");
                    nuevo = new SesionFotos(titulo, mod, fot, maq, null, loc, LocalDate.now());
                    break;

                default:
                    System.out.println("Tipo no válido.");
            }

            if (nuevo != null) {
                revista.agregarArticulo(nuevo);
                System.out.println("¡Artículo guardado! Se ha asignado a la página " + nuevo.getPaginaInicio());
            }
        } else if (op == 3) {
            int id = leerEntero("Introduce el ID del artículo que quieres borrar: ");
            revista.eliminarArticulo(id);
            System.out.println("Artículo eliminado correctamente.");
        }
    }

    /**
     * Submenú para gestionar los anuncios.
     */
    private static void gestionarAnuncios() {
        System.out.println("\n1. Listar anuncios, 2. Añadir anuncio, 3. Borrar anuncio");
        int op = leerEntero("Opción: ");
        if (op == 1) {
            if (revista.getAnuncios().isEmpty())
                System.out.println("No hay anuncios.");
            for (Anuncio a : revista.getAnuncios())
                System.out.println(a);
        } else if (op == 2) {
            String marca = leerCadena("Dime la marca: ");
            Anuncio a = new Anuncio(marca, 100.0, "img/anuncio.png");
            revista.agregarAnuncio(a);
            System.out.println("Anuncio guardado en la página " + a.getPagina());
        } else if (op == 3) {
            revista.eliminarAnuncio(leerEntero("Dime el ID del anuncio: "));
            System.out.println("Anuncio borrado.");
        }
    }

    /**
     * Cambiar el titular de la portada.
     */
    private static void configurarPortada() {
        String titular = leerCadena("Nuevo titular: ");
        revista.setPortada(new Portada(null, null, null, null, titular, "portada.png"));
        System.out.println("Portada cambiada.");
    }

    /**
     * Cambiar la carta del editor.
     */
    private static void configurarCartaEditor() {
        String msj = leerCadena("Contenido de la carta: ");
        revista.setCartaEditor(new CartaEditor(null, msj, LocalDate.now()));
        System.out.println("Carta guardada.");
    }

    /**
     * Muestra cuántas páginas hay y qué falta.
     */
    private static void mostrarResumenRevista() {
        System.out.println("\n--- RESUMEN ACTUAL ---");
        System.out.println("Revista: " + revista.getTitulo());
        System.out.println("Páginas en total: " + ValidadorRevista.calcularTotalPaginas(revista));
        System.out.println("Estado:");
        System.out.println(" - Portada: " + (revista.getPortada() != null ? "SI" : "NO"));
        System.out.println(" - Carta editor: " + (revista.getCartaEditor() != null ? "SI" : "NO"));
        System.out.println(" - Artículos: " + revista.getArticulos().size());
        System.out.println(" - Anuncios: " + revista.getAnuncios().size());
    }

    /**
     * Llama al validador para ver si todo está bien.
     */
    private static void validarRevista() {
        System.out.println("\n--- VALIDACIÓN ---");
        if (ValidadorRevista.validarRevistaListaParaPublicar(revista)) {
            System.out.println("Todo perfecto, la puedes publicar.");
        } else {
            System.out.println("Todavía faltan cosas:");
            System.out.println(ValidadorRevista.getResumenFaltantes(revista));
        }
    }

    // Funciones auxiliares para leer de teclado más fácil
    private static String leerCadena(String m) {
        System.out.print(m + "> ");
        return scanner.nextLine();
    }

    private static int leerEntero(String m) {
        System.out.print(m + "> ");
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Dime un número válido.");
            return 0;
        }
    }
}
