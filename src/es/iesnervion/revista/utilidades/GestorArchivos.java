package es.iesnervion.revista.utilidades;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import es.iesnervion.revista.modelo.Anuncio;
import es.iesnervion.revista.modelo.Articulo;
import es.iesnervion.revista.modelo.CartaEditor;
import es.iesnervion.revista.modelo.Portada;
import es.iesnervion.revista.modelo.Reportaje;
import es.iesnervion.revista.modelo.Revista;

/**
 * Utilidad para cargar y guardar la revista en disco usando el archivo
 * {@code revista.txt}.
 */
public class GestorArchivos {

    private static final Path REVISTA_PATH = Paths.get("revista.txt");

    /**
     * Carga la revista desde revista.txt convirtiendo los datos a texto.
     *
     * @return la revista cargada, o una revista vacía si el archivo no existe.
     */
    public static Revista cargarRevistaSimple() {
        Revista revista = new Revista();
        if (!Files.exists(REVISTA_PATH)) {
            return revista;
        }

        try (BufferedReader lector = Files.newBufferedReader(REVISTA_PATH, StandardCharsets.UTF_8)) {
            String linea;
            while ((linea = lector.readLine()) != null) {
                if (linea.isBlank()) {
                    continue;
                }

                // La primera columna indica qué clase de objeto hay que reconstruir,
                // así se decide si la línea representa una portada, una carta, un artículo o un anuncio.
                int posicionPrimerSeparador = linea.indexOf('|');
                String tipo;
                if (posicionPrimerSeparador == -1) {
                    tipo = linea;
                } else {
                    tipo = linea.substring(0, posicionPrimerSeparador);
                }

                if ("PORTADA".equals(tipo)) {
                    // Se guardan solo los datos básicos de la portada.
                    int posicionInicioTitular = posicionPrimerSeparador + 1;
                    int posicionSegundoSeparador = linea.indexOf('|', posicionInicioTitular);
                    String titular = posicionSegundoSeparador == -1 ? linea.substring(posicionInicioTitular) : linea.substring(posicionInicioTitular, posicionSegundoSeparador);
                    String imagenUrl = posicionSegundoSeparador == -1 ? "" : (posicionSegundoSeparador + 1 >= linea.length() ? "" : linea.substring(posicionSegundoSeparador + 1));
                    revista.setPortada(new Portada(titular, imagenUrl));

                } else if ("CARTA".equals(tipo)) {
                    // Se guardan solo los datos básicos de la carta.
                    int posicionInicioMensaje = posicionPrimerSeparador + 1;
                    int posicionSegundoSeparador = linea.indexOf('|', posicionInicioMensaje);
                    String mensaje = posicionSegundoSeparador == -1 ? linea.substring(posicionInicioMensaje) : linea.substring(posicionInicioMensaje, posicionSegundoSeparador);
                    String fechaStr = posicionSegundoSeparador == -1 ? "" : (posicionSegundoSeparador + 1 >= linea.length() ? "" : linea.substring(posicionSegundoSeparador + 1));
                    LocalDate fecha = fechaStr.isEmpty() ? LocalDate.now() : LocalDate.parse(fechaStr);
                    revista.setCartaEditor(new CartaEditor(mensaje, fecha));

                } else if ("ARTICULO".equals(tipo)) {
                    // Se guardan solo los datos basicos del artículo, 
                    // (aunque aqui se asumen que son reportajes vacios para carga mas facil)
                    int posicionInicioTipoArticulo = posicionPrimerSeparador + 1;
                    posicionInicioTipoArticulo = linea.indexOf('|', posicionInicioTipoArticulo);
                    if (posicionInicioTipoArticulo == -1) continue;
                    posicionInicioTipoArticulo = posicionInicioTipoArticulo + 1;
                    posicionInicioTipoArticulo = linea.indexOf('|', posicionInicioTipoArticulo);
                    if (posicionInicioTipoArticulo == -1) continue;
                    posicionInicioTipoArticulo = posicionInicioTipoArticulo + 1;
                    String titulo = posicionInicioTipoArticulo >= linea.length() ? "(sin título)" : linea.substring(posicionInicioTipoArticulo);
                    revista.agregarArticulo(new Reportaje(titulo, "(sin temática)"));

                } else if ("ANUNCIO".equals(tipo)) {
                    // Se guardan solo los datos básicos del anuncio, el id es automatico.
                    int posicionInicioMarca = posicionPrimerSeparador + 1;
                    posicionInicioMarca = linea.indexOf('|', posicionInicioMarca);
                    if (posicionInicioMarca == -1) continue;
                    posicionInicioMarca = posicionInicioMarca + 1;
                    int posicionSeparadorPrecio = linea.indexOf('|', posicionInicioMarca);
                    String marca = posicionSeparadorPrecio == -1 ? linea.substring(posicionInicioMarca) : linea.substring(posicionInicioMarca, posicionSeparadorPrecio);
                    String precioTok = posicionSeparadorPrecio == -1 ? "0" : (posicionSeparadorPrecio + 1 >= linea.length() ? "0" : linea.substring(posicionSeparadorPrecio + 1));
                    double precio = parsearPrecio(precioTok);
                    revista.agregarAnuncio(new Anuncio(marca, precio));
                }
            }
        } catch (IOException e) {
            System.out.println("Error leyendo revista.txt: " + e.getMessage());
        }

        return revista;
    }

    /**
     * Guarda la revista en revista.txt con:
     * una sección legible para el usuario
     * una sección de datos para su carga automática.
     *
     * @param revista revista a guardar.
     */
    public static void guardarRevistaSimple(Revista revista) {
        try (BufferedWriter w = Files.newBufferedWriter(REVISTA_PATH, StandardCharsets.UTF_8)) {
            
//---------------------------------SECCION LEGIBLE -----------------------------------
            w.write("=== REVISTA ===");
            w.newLine();

            if (revista.getPortada() == null) {
                w.write("Portada: (sin portada)");
                w.newLine();
            } else {
                Portada portadita = revista.getPortada();
                w.write("Portada:");
                w.newLine();
                w.write("  Modelo: " + (portadita.getModelo() == null ? "(sin modelo)" : portadita.getModelo().getNombre()));
                w.newLine();
                w.write("  Fotógrafo: " + (portadita.getFotografo() == null ? "(sin fotógrafo)" : portadita.getFotografo().getNombre()));
                w.newLine();
                w.write("  Maquillador: " + (portadita.getMaquillador() == null ? "(sin maquillador)" : portadita.getMaquillador().getNombre()));
                w.newLine();
                w.write("  Diseñador: " + (portadita.getDiseñador() == null ? "(sin diseñador)" : portadita.getDiseñador().getNombre()));
                w.newLine();
                w.write("  Titular: " + portadita.getTitular());
                w.newLine();
                w.write("  Imagen: " + portadita.getImagenUrl());
                w.newLine();
            }

            w.newLine();

            if (revista.getCartaEditor() == null) {
                w.write("Carta: (sin carta)");
                w.newLine();
            } else {
                CartaEditor carta = revista.getCartaEditor();
                w.write("Carta:");
                w.newLine();
                w.write("  Editor: " + (carta.getEditor() == null ? "(sin editor)" : carta.getEditor().getNombre()));
                w.newLine();
                w.write("  Mensaje: " + carta.getMensaje());
                w.newLine();
                w.write("  Fecha: " + carta.getFecha());
                w.newLine();
            }

            // Se escribe primero la lista de artículos en formato legible para inspección manual.
            w.newLine();
            w.write("-- Artículos --");
            w.newLine();
            int numeroElemento = 1;
            for (Articulo articulito : revista.getArticulos()) {
                w.write(numeroElemento++ + ") " + articulito.toString());
                w.newLine();
            }
            if (revista.getArticulos().isEmpty()) {
                w.write("(sin artículos)");
                w.newLine();
            }

            // Después se escriben los anuncios con el mismo objetivo: que el archivo se pueda revisar a ojo.
            w.newLine();
            w.write("-- Anuncios --");
            w.newLine();
            numeroElemento = 1;
            for (Anuncio anuncito : revista.getAnuncios()) {
                w.write(numeroElemento++ + ") " + anuncito.toString());
                w.newLine();
            }
            if (revista.getAnuncios().isEmpty()) {
                w.write("(sin anuncios)");
                w.newLine();
            }

//---------------------------------SECCION DE DATOS -----------------------------------
            w.newLine();
            w.write("===DATOS===");
            w.newLine();

            if (revista.getPortada() != null) {
                // Guarda la portada en una sola línea con separadores simples.
                w.write(String.join("|", "PORTADA", revista.getPortada().getTitular(), revista.getPortada().getImagenUrl()));
                w.newLine();
            }

            if (revista.getCartaEditor() != null) {
                // Guarda la carta con el mensaje y la fecha en un formato fácil de volver a parsear.
                w.write(String.join("|", "CARTA", revista.getCartaEditor().getMensaje(), String.valueOf(revista.getCartaEditor().getFecha())));
                w.newLine();
            }

            for (Articulo articulito : revista.getArticulos()) {
                // Cada artículo se guarda con su tipo para saber luego qué clase reconstruir.
                w.write(String.join("|", "ARTICULO", articulito.getTipo(), String.valueOf(articulito.getId()), articulito.getTitulo()));
                w.newLine();
            }

            for (Anuncio anuncito : revista.getAnuncios()) {
                // Los anuncios se serializan con su identificador, marca y precio.
                w.write(String.join("|", "ANUNCIO", String.valueOf(anuncito.getId()), anuncito.getMarca(), String.valueOf(anuncito.getPrecio())));
                w.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error escribiendo revista.txt: " + e.getMessage());
        }
    }

// -------------------------- EXTRAS ---------------------------------------
    /**
     * Convierte un texto a {@code double}, devolviendo {@code 0.0} si la
     * conversión falla.
     *
     * @param valor texto a convertir.
     * @return el valor convertido, o {@code 0.0} si no se pudo convertir.
     */
    private static double parsearPrecio(String valor) {
        try {
            return Double.parseDouble(valor.trim());
        } catch (Exception e) {
            // Si el precio viene vacío, roto o con formato incorrecto, se usa 0.0 para no interrumpir la carga.
            return 0.0;
        }
    }

}
