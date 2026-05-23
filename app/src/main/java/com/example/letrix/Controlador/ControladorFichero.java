package com.example.letrix.Controlador;

import android.content.Context;

import com.example.letrix.modelo.categoria.CategoriaDAO;
import com.example.letrix.modelo.palabra.Palabra;
import com.example.letrix.modelo.palabra.PalabraDAO;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Controlador encargado de la LECTURA DE FICHEROS.
 *
 * Su trabajo: leer los ficheros de diccionario (.txt) que estan en
 * la carpeta 'assets' y volcarlos a la base de datos la primera vez
 * que se abre la app.
 *
 * Cada categoria tiene su propio fichero:
 *   - geografia.txt
 *   - deporte.txt
 *   - anatomia.txt
 *   - cine.txt
 *
 * Una palabra por linea, en mayusculas, sin tildes ni enies.
 *
 * Esta clase la trabaja Carlos (paquete Controlador).
 */
public class ControladorFichero {

    // Nombres de las categorias y sus ficheros.
    // El indice del array CATEGORIAS coincide con el de FICHEROS:
    //   CATEGORIAS[0] = "geografia"  va con FICHEROS[0] = "geografia.txt"
    private static final String[] CATEGORIAS = {
            "geografia", "deporte", "anatomia", "cine"
    };
    private static final String[] FICHEROS = {
            "geografia.txt", "deporte.txt", "anatomia.txt", "cine.txt"
    };

    private final Context context;
    private final PalabraDAO palabraDAO;
    private final CategoriaDAO categoriaDAO;

    /**
     * @param context contexto de Android. Lo necesita para abrir los
     *                ficheros de assets y para crear los DAOs.
     */
    public ControladorFichero(Context context) {
        this.context = context;
        this.palabraDAO = new PalabraDAO(context);
        this.categoriaDAO = new CategoriaDAO(context);
    }

    /**
     * Carga los 4 ficheros de diccionario en la BBDD, pero SOLO si la
     * tabla de palabras esta vacia. Asi el fichero se lee una unica
     * vez (la primera vez que se abre la app) y no en cada arranque.
     *
     * Esta es la unica funcion que necesita llamar la Vista. La llama
     * la MainActivity (o la primera Activity de la app) al arrancar.
     */
    public void cargarDiccionarioSiVacio() {
        // TODO Miguel: tienes que anadir el metodo contarPalabras() al
        //              PalabraDAO. Mientras no exista, comenta esta
        //              comprobacion para que compile.
        if (palabraDAO.contarPalabras() > 0) {
            return; // ya estaba cargado, no hacemos nada.
        }

        // Recorrer las 4 categorias y cargar el fichero de cada una.
        for (int i = 0; i < CATEGORIAS.length; i++) {
            String nombreCategoria = CATEGORIAS[i];
            String nombreFichero = FICHEROS[i];

            // TODO Miguel: anadir al CategoriaDAO un metodo
            //              buscarIdPorNombre(String) que devuelva el
            //              id (int) de la categoria con ese nombre.
            int idCategoria = categoriaDAO.buscarIdPorNombre(nombreCategoria);

            // Leer todas las palabras de este fichero y meterlas en
            // la BBDD asociadas al id de la categoria correspondiente.
            List<String> palabras = leerPalabras(nombreFichero);
            for (int j = 0; j < palabras.size(); j++) {
                String texto = palabras.get(j);
                // El id de la Palabra va a null: lo asigna la BBDD.
                Palabra palabra = new Palabra(
                        null,                       // id (lo pone la BBDD)
                        texto,                      // texto de la palabra
                        String.valueOf(idCategoria) // id de la categoria
                );
                // TODO Miguel: anadir al PalabraDAO el metodo insertar(Palabra).
                palabraDAO.insertar(palabra);
            }
        }
    }

    /**
     * Lee un fichero de la carpeta 'assets' y devuelve la lista de
     * palabras (una por linea), en mayusculas y sin lineas vacias.
     *
     * Usa el patron try / catch / finally del profesor: el BufferedReader
     * se cierra SIEMPRE, aunque haya un error durante la lectura.
     *
     * @param nombreFichero p.ej. "geografia.txt"
     */
    private List<String> leerPalabras(String nombreFichero) {
        List<String> palabras = new ArrayList<>();
        InputStream is = null;
        BufferedReader lector = null;

        try {
            // getAssets().open() abre el fichero de la carpeta assets.
            is = context.getAssets().open(nombreFichero);
            lector = new BufferedReader(new InputStreamReader(is));

            String linea = lector.readLine();
            boolean hayLinea = (linea != null);

            while (hayLinea) {
                String palabra = linea.trim().toUpperCase();
                if (!palabra.isEmpty()) {
                    palabras.add(palabra);
                }
                linea = lector.readLine();
                hayLinea = (linea != null);
            }
        } catch (IOException e) {
            // Si falla la lectura, lo unico que podemos hacer aqui es
            // dejar la lista a medias y registrar el error.
            e.printStackTrace();
        } finally {
            // Cerrar SIEMPRE el lector y el InputStream.
            if (lector != null) {
                try {
                    lector.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return palabras;
    }
}