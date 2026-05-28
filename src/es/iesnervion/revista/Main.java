package es.iesnervion.revista;


import java.util.ArrayList;
import java.util.List;

import es.iesnervion.revista.app.EntradaRevista;
import es.iesnervion.revista.excepciones.LimitePaginasException;
import es.iesnervion.revista.excepciones.MaximosSuperadosException;
import es.iesnervion.revista.excepciones.MinimosNoCumplidosException;
import es.iesnervion.revista.modelo.*;
import es.iesnervion.revista.utilidades.ValidadorRevista;
import es.iesnervion.revista.utilidades.GestorArchivos;

public class Main {

    /**
     * Punto de entrada de la aplicación.
     */
    public static void main(String[] args) {
        Revista revista = GestorArchivos.cargarRevistaSimple();
        boolean salir = false;

        // bucle principal del programa, se repite hasta que el usuario elija salir
        while (!salir) {
            mostrarMenuPrincipal();
            int opcion = EntradaRevista.leerEnteroPositivo("Elige una opción:");

            switch (opcion) {
            case 1:
                anadirElemento(revista);
                GestorArchivos.guardarRevistaSimple(revista);
                break;
            case 2:
                editarElemento(revista);
                GestorArchivos.guardarRevistaSimple(revista);
                break;
            case 3:
                borrarElemento(revista);
                GestorArchivos.guardarRevistaSimple(revista);
                break;
            case 4:
                listarRevista(revista);
                break;
            case 5:
                validarYPublicar(revista);
                break;
            case 6:
                GestorArchivos.guardarRevistaSimple(revista);
                salir = true;
                break;
            case 7:
                borrarTodo(revista);
                break;
            default:
                System.out.println("Opción no válida.");
            }
        }

        System.out.println("Saliendo...");
    }

//--------------------------- MENÚS ----------------------------------------

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
        System.out.println("7) Borrar todo");
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

//---------------------------  SUB MENÚS ----------------------------------------

    /**
     * Añade un elemento nuevo a la revista.
     * Muestra el menú de opciones (Portada, Carta, 5 tipos de artículos, Anuncio)
     * y pide los datos  según la opción, 0 para cancelar.
     *
     * @param revista revista en edición.
     */
    private static void anadirElemento(Revista revista) {
        mostrarMenuAnadir();
        int opcion = EntradaRevista.leerOpcionMenuConCancelacion("Qué quieres añadir", 7);
        if (opcion == 0) return;

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
     * Edita un elemento existente en la revista.
     * Muestra el menú de opciones (Portada, Carta, Artículo, Anuncio)
     * y reemplaza el elemento seleccionado con nuevos datos, 0 para cancelar.
     *
     * @param revista revista en edición.
     */
    private static void editarElemento(Revista revista) {
        mostrarMenuEditar();
        int opcion = EntradaRevista.leerOpcionMenuConCancelacion("Qué quieres editar", 4);
        if (opcion == 0) return;

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
     * Borra un elemento existente de la revista.
     * Muestra el menú de opciones (Portada, Carta, Artículo, Anuncio)
     * y elimina el elemento seleccionado, 0 para cancelar. 
     *
     * @param revista revista en edición.
     */
    private static void borrarElemento(Revista revista) {
        mostrarMenuBorrar();
        int opcion = EntradaRevista.leerOpcionMenuConCancelacion("Qué quieres borrar", 4);
        if (opcion == 0) return;

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
     * Muestra en consola el contenido de la revista.
     * Muestra Portada, Carta del Editor, lista de artículos y lista de anuncios.
     *
     * @param revista revista a mostrar.
     */
    private static void listarRevista(Revista revista) {
        System.out.println("\n=== REVISTA ===");
        System.out.println("Portada: " + (revista.getPortada() == null ? "(sin portada)" : revista.getPortada().getTitular()));
        System.out.println("Carta: " + (revista.getCartaEditor() == null ? "(sin carta)" : revista.getCartaEditor()));

        System.out.println("\n-- Artículos --");
        List<Articulo> articulos = revista.getArticulos();
        if (articulos.isEmpty()) {
            System.out.println("(sin artículos)");
        } else {
            EntradaRevista.listarArticulos(articulos);
        }

        System.out.println("\n-- Anuncios --");
        List<Anuncio> anuncios = revista.getAnuncios();
        if (anuncios.isEmpty()) {
            System.out.println("(sin anuncios)");
        } else {
            EntradaRevista.listarAnuncios(anuncios);
        }
    }

//--------------------------- FIN DE TODOS LOS MENÚS ----------------------------------------

// -------------------------- METODOS PARA EL CRUD ---------------------------------------

    /**
     * Valida la revista antes de publicar usando ValidadorRevista.
     * Si la validación es correcta, guarda la revista como publicada,
     * y recalcula las páginas.
     *
     * @param revista Revista a validar.
     */
    private static void validarYPublicar(Revista revista) {
        try {
            ValidadorRevista.validarRevistaListaParaPublicar(revista);
            System.out.println("La revista está lista para publicarse.");
            GestorArchivos.guardarRevistaSimple(revista);
        } catch (MinimosNoCumplidosException | MaximosSuperadosException | LimitePaginasException e) {
            System.out.println("No se puede publicar: " + e.getMessage());
            System.out.println("Resumen de faltantes:\n" + ValidadorRevista.getResumenFaltantes(revista));
        } catch (IllegalArgumentException e) {
            System.out.println("Error en la validación: " + e.getMessage());
        }
    }

    /**
     * Edita un artículo seleccionado por su posición.
     * Detecta el tipo de artículo (Entrevista, Reportaje, etc) pide los datos, 
     * y recalcula las páginas.
     *
     * @param revista revista en edición.
     */
    private static void editarArticulo(Revista revista) {
        List<Articulo> articulos = revista.getArticulos();
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
     * Borra un artículo seleccionado por su posición.
     * Muestra la lista de artículos y pide al usuario que seleccione cuál borrar 
     * y recalcula las páginas.
     *
     * @param revista revista en edición.
     */
    private static void borrarArticulo(Revista revista) {
        List<Articulo> articulos = revista.getArticulos();
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
     * Edita un anuncio seleccionado por su posición.
     * Muestra la lista de anuncios, pide los nuevos datos, 
     * y recalcula las páginas.
     *
     * @param revista revista en edición.
     */
    private static void editarAnuncio(Revista revista) {
        List<Anuncio> anuncios = revista.getAnuncios();
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
     * Borra un anuncio seleccionado por posición.
     * Muestra la lista de anuncios y pide al usuario que seleccione cuál borrar.
     * y recalcula las páginas.
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
     * Borra todo el contenido de la revista.
     * Requiere confirmación del usuario por si acaso.
     * 
     * @param revista revista a vaciar.
     */
    private static void borrarTodo(Revista revista) {
        String confirm = EntradaRevista.pedirLinea("¿Seguro que quieres BORRAR TODO el contenido? Escribe SI para confirmar:");
        if (!"SI".equalsIgnoreCase(confirm.trim())) {
            System.out.println("Operación cancelada.");
            return;
        }
        revista.setPortada(null);
        revista.setCartaEditor(null);
        revista.getArticulos().clear();
        revista.getAnuncios().clear();
        revista.recalcularPaginas();
        GestorArchivos.guardarRevistaSimple(revista);
        System.out.println("Contenido borrado y guardado.");
    }
}