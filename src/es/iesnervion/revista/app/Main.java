package es.iesnervion.revista.app;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import es.iesnervion.revista.modelo.*;
import es.iesnervion.revista.utilidades.ValidadorRevista;
import es.iesnervion.revista.excepciones.RevistaException;

/**
 * Punto de arranque de la aplicación de consola con el CRUD de la revista.
 */
public class Main {

    // Copia persistente principal de la revista
    private static final Path ARCHIVO_DATOS = Paths.get("revista.txt");
    private static final Path ARCHIVO_SERIALIZADO = Paths.get("revista.ser");

    /**
     * Punto de entrada de la aplicación.
     *
     * @param args argumentos de línea de comandos, no usados.
     */
    public static void main(String[] args) {
        Revista revista = cargarRevista();
        boolean salir = false;

        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = EntradaRevista.leerEnteroPositivo("Elige una opción:");

            switch (opcion) {
            case 1:
                anadirElemento(revista);
                guardarRevista(revista);
                break;
            case 2:
                editarElemento(revista);
                guardarRevista(revista);
                break;
            case 3:
                borrarElemento(revista);
                guardarRevista(revista);
                break;
            case 4:
                listarRevista(revista);
                break;
            case 5:
                validarYPublicar(revista);
                break;
            case 6:
                guardarRevista(revista);
                salir = true;
                break;
            default:
                System.out.println("Opción no válida.");
            }
        }

        System.out.println("Saliendo...");
    }

    /**
     * Muestra el menú principal.
     */
    private static void mostrarMenuPrincipal() {
        System.out.println("\n--- ADMINISTRADOR REVISTA ---");
        System.out.println("1) Añadir");
        System.out.println("2) Editar");
        System.out.println("3) Borrar");
        System.out.println("4) Ver");
        System.out.println("5) Validar y publicar");
        System.out.println("6) Salir");
    }

    /**
     * Valida la revista antes de publicar y muestra los fallos encontrados.
     * Si la validación es correcta, se guarda la revista como publicada.
     *
     * @param revista Revista a validar.
     */
    private static void validarYPublicar(Revista revista) {
        try {
            ValidadorRevista.validarRevistaListaParaPublicar(revista);
            System.out.println("La revista está lista para publicarse.");
            guardarRevista(revista);
        } catch (RevistaException e) {
            System.out.println("No se puede publicar: " + e.getMessage());
            System.out.println("Resumen de faltantes:\n" + ValidadorRevista.getResumenFaltantes(revista));
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la validación: " + e.getMessage());
        }
    }

    /**
     * Muestra el menú de añadir.
     */
    private static void mostrarMenuAnadir() {
        System.out.println("\n1) Portada");
        System.out.println("2) Carta del editor");
        System.out.println("3) Entrevista");
        System.out.println("4) Reportaje");
        System.out.println("5) Colección de moda");
        System.out.println("6) Sesión de fotos");
        System.out.println("7) Anuncio");
    }

    /**
     * Muestra el menú para editar.
     */
    private static void mostrarMenuEditar() {
        System.out.println("\n1) Portada");
        System.out.println("2) Carta del editor");
        System.out.println("3) Artículo");
        System.out.println("4) Anuncio");
    }

    /**
     * Muestra el menú para borrar.
     */
    private static void mostrarMenuBorrar() {
        System.out.println("\n1) Portada");
        System.out.println("2) Carta del editor");
        System.out.println("3) Artículo");
        System.out.println("4) Anuncio");
    }

    /**
     * Añade un elemento nuevo a la revista.
     *
     * @param revista revista en edición.
     */
    private static void anadirElemento(Revista revista) {
        mostrarMenuAnadir();
        int opcion = EntradaRevista.leerEnteroPositivo("Qué quieres añadir:");

        switch (opcion) {
        case 1:
            revista.setPortada(EntradaRevista.pedirPortada());
            break;
        case 2:
            revista.setCartaEditor(EntradaRevista.pedirCartaEditor());
            break;
        case 3:
            revista.agregarArticulo(EntradaRevista.pedirEntrevista());
            break;
        case 4:
            revista.agregarArticulo(EntradaRevista.pedirReportaje());
            break;
        case 5:
            revista.agregarArticulo(EntradaRevista.pedirColeccionModa());
            break;
        case 6:
            revista.agregarArticulo(EntradaRevista.pedirSesionFotos());
            break;
        case 7:
            revista.agregarAnuncio(EntradaRevista.pedirAnuncio());
            break;
        default:
            System.out.println("Opción no válida.");
        }
    }

    /**
     * Edita un elemento existente.
     *
     * @param revista revista en edición.
     */
    private static void editarElemento(Revista revista) {
        mostrarMenuEditar();
        int opcion = EntradaRevista.leerEnteroPositivo("Qué quieres editar:");

        switch (opcion) {
        case 1:
            if (revista.getPortada() == null) {
                System.out.println("No hay portada.");
            } else {
                revista.setPortada(EntradaRevista.pedirPortada());
            }
            break;
        case 2:
            if (revista.getCartaEditor() == null) {
                System.out.println("No hay carta del editor.");
            } else {
                revista.setCartaEditor(EntradaRevista.pedirCartaEditor());
            }
            break;
        case 3:
            editarArticulo(revista);
            break;
        case 4:
            editarAnuncio(revista);
            break;
        default:
            System.out.println("Opción no válida.");
        }
    }

    /**
     * Borra un elemento existente.
     *
     * @param revista revista en edición.
     */
    private static void borrarElemento(Revista revista) {
        mostrarMenuBorrar();
        int opcion = EntradaRevista.leerEnteroPositivo("Qué quieres borrar:");

        switch (opcion) {
        case 1:
            revista.setPortada(null);
            break;
        case 2:
            revista.setCartaEditor(null);
            break;
        case 3:
            borrarArticulo(revista);
            break;
        case 4:
            borrarAnuncio(revista);
            break;
        default:
            System.out.println("Opción no válida.");
        }
    }

    /**
     * Muestra el contenido actual de la revista.
     *
     * @param revista revista a mostrar.
     */
    private static void listarRevista(Revista revista) {
        System.out.println("\n=== REVISTA ===");
        System.out.println("Portada: " + (revista.getPortada() == null ? "(sin portada)" : revista.getPortada().getTitular()));
        System.out.println("Carta: " + (revista.getCartaEditor() == null ? "(sin carta)" : revista.getCartaEditor()));

        System.out.println("\n-- Artículos --");
        ArrayList<Articulo> articulos = new ArrayList<>(revista.getArticulos());
        if (articulos.isEmpty()) {
            System.out.println("(sin artículos)");
        } else {
            for (int indice = 0; indice < articulos.size(); indice++) {
                System.out.println((indice + 1) + ") " + articulos.get(indice));
            }
        }

        System.out.println("\n-- Anuncios --");
        ArrayList<Anuncio> anuncios = new ArrayList<>(revista.getAnuncios());
        if (anuncios.isEmpty()) {
            System.out.println("(sin anuncios)");
        } else {
            for (int indice = 0; indice < anuncios.size(); indice++) {
                System.out.println((indice + 1) + ") " + anuncios.get(indice));
            }
        }
    }

    /**
     * Edita un artículo buscandolo por posición.
     *
     * @param revista revista en edición.
     */
    private static void editarArticulo(Revista revista) {
        ArrayList<Articulo> articulos = new ArrayList<>(revista.getArticulos());
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos.");
            return;
        }

        EntradaRevista.listarArticulos(articulos);
        int indice = EntradaRevista.leerIndice("Número de artículo a editar:", articulos.size());
        if (indice == -1) {
            return;
        }

        Articulo articuloActual = articulos.get(indice);
        if (articuloActual instanceof es.iesnervion.revista.modelo.Entrevista) {
            revista.getArticulos().set(indice, EntradaRevista.pedirEntrevista());
        } else if (articuloActual instanceof es.iesnervion.revista.modelo.Reportaje) {
            revista.getArticulos().set(indice, EntradaRevista.pedirReportaje());
        } else if (articuloActual instanceof es.iesnervion.revista.modelo.ColeccionModa) {
            revista.getArticulos().set(indice, EntradaRevista.pedirColeccionModa());
        } else if (articuloActual instanceof es.iesnervion.revista.modelo.SesionFotos) {
            revista.getArticulos().set(indice, EntradaRevista.pedirSesionFotos());
        } else {
            System.out.println("Tipo no editable.");
            return;
        }

        revista.recalcularPaginas();
    }

    /**
     * Borra un artículo buscandolo por posición.
     *
     * @param revista revista en edición.
     */
    private static void borrarArticulo(Revista revista) {
        ArrayList<Articulo> articulos = new ArrayList<>(revista.getArticulos());
        if (articulos.isEmpty()) {
            System.out.println("No hay artículos.");
            return;
        }

        EntradaRevista.listarArticulos(articulos);
        int indice = EntradaRevista.leerIndice("Número de artículo a borrar:", articulos.size());
        if (indice != -1) {
            revista.getArticulos().remove(indice);
            revista.recalcularPaginas();
        }
    }

    /**
     * Edita un anuncio buscandolo por posición.
     *
     * @param revista revista en edición.
     */
    private static void editarAnuncio(Revista revista) {
        ArrayList<Anuncio> anuncios = new ArrayList<>(revista.getAnuncios());
        if (anuncios.isEmpty()) {
            System.out.println("No hay anuncios.");
            return;
        }

        EntradaRevista.listarAnuncios(anuncios);
        int indice = EntradaRevista.leerIndice("Número de anuncio a editar:", anuncios.size());
        if (indice != -1) {
            revista.getAnuncios().set(indice, EntradaRevista.pedirAnuncio());
            revista.recalcularPaginas();
        }
    }

    /**
     * Borra un anuncio buscandolo por posición.
     *
     * @param revista revista en edición.
     */
    private static void borrarAnuncio(Revista revista) {
        ArrayList<Anuncio> anuncios = new ArrayList<>(revista.getAnuncios());
        if (anuncios.isEmpty()) {
            System.out.println("No hay anuncios.");
            return;
        }

        EntradaRevista.listarAnuncios(anuncios);
        int indice = EntradaRevista.leerIndice("Número de anuncio a borrar:", anuncios.size());
        if (indice != -1) {
            revista.getAnuncios().remove(indice);
            revista.recalcularPaginas();
        }
    }

    /**
     * Guarda la revista en un archivo txt (revista.txt)
     *
     * @param revista revista que se quiere guardar.
     */
    private static void guardarRevista(Revista revista) {
        guardarRevistaSerializada(revista);
        try (BufferedWriter escritor = Files.newBufferedWriter(ARCHIVO_DATOS)) {
            escritor.write("REVISTA");
            escritor.newLine();
            escritor.write("Portada: " + (revista.getPortada() == null ? "(sin portada)" : revista.getPortada()));
            escritor.newLine();
            escritor.write("Carta: " + (revista.getCartaEditor() == null ? "(sin carta)" : revista.getCartaEditor()));
            escritor.newLine();
            escritor.write("Articulos:");
            escritor.newLine();

            for (Articulo articulo : revista.getArticulos()) {
                escritor.write("- " + articulo);
                escritor.newLine();
            }
            escritor.write("Anuncios:");
            escritor.newLine();
            for (Anuncio anuncio : revista.getAnuncios()) {
                escritor.write("- " + anuncio);
                escritor.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error guardando: " + e.getMessage());
        }
    }

    private static void guardarRevistaSerializada(Revista revista) {
        try (ObjectOutputStream salida = new ObjectOutputStream(
                new BufferedOutputStream(Files.newOutputStream(ARCHIVO_SERIALIZADO)))) {
            salida.writeObject(revista);
        } catch (IOException e) {
            System.out.println("Error guardando copia persistente: " + e.getMessage());
        }
    }

    private static Revista cargarRevista() {
        Revista revista = cargarRevistaSerializada();
        if (revista != null) {
            return revista;
        }

        return new Revista();
    }

    private static Revista cargarRevistaSerializada() {
        if (!Files.exists(ARCHIVO_SERIALIZADO)) {
            return null;
        }

        try (ObjectInputStream entrada = new ObjectInputStream(
                new BufferedInputStream(Files.newInputStream(ARCHIVO_SERIALIZADO)))) {
            Revista revista = (Revista) entrada.readObject();
            ajustarContadores(revista);
            return revista;
            } catch (java.io.InvalidClassException e) {
            try {
                Files.deleteIfExists(ARCHIVO_SERIALIZADO);
            } catch (IOException ignored) {
                // Si no se puede borrar, se sobrescribirá al guardar.
            }
            System.out.println("La copia persistente anterior no es compatible y se ha descartado.");
            return null;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error cargando copia persistente: " + e.getMessage());
            return null;
        }
    }

    private static void ajustarContadores(Revista revista) {
        int maxIdArticulo = 0;
        for (Articulo articulo : revista.getArticulos()) {
            if (articulo.getId() > maxIdArticulo) {
                maxIdArticulo = articulo.getId();
            }
        }
        Articulo.ajustarContadorId(maxIdArticulo + 1);

        int maxIdAnuncio = 0;
        for (Anuncio anuncio : revista.getAnuncios()) {
            if (anuncio.getId() > maxIdAnuncio) {
                maxIdAnuncio = anuncio.getId();
            }
        }
        Anuncio.ajustarContadorId(maxIdAnuncio + 1);
    }
}