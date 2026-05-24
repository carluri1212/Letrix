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

// Lee los ficheros .txt de la carpeta assets y los carga en la BBDD
// la primera vez que se abre la app. Cada categoría tiene su .txt.
public class ControladorFichero {

    // Los índices de los dos arrays se corresponden:
    // CATEGORIAS[0] = "geografia" -> FICHEROS[0] = "geografia.txt"
    private static final String[] CATEGORIAS = {
            "geografia", "deporte", "anatomia", "cine"
    };
    private static final String[] FICHEROS = {
            "geografia.txt", "deporte.txt", "anatomia.txt", "cine.txt"
    };

    private final Context context;
    private final PalabraDAO palabraDAO;
    private final CategoriaDAO categoriaDAO;

    public ControladorFichero(Context context) {
        this.context = context;
        this.palabraDAO = new PalabraDAO(context);
        this.categoriaDAO = new CategoriaDAO(context);
    }

    // Si la tabla de palabras está vacía, lee los .txt y los vuelca.
    // Si ya hay palabras no hace nada, así no recargamos cada vez.
    public void cargarDiccionarioSiVacio() {
        if (palabraDAO.contarPalabras() > 0) {
            return;
        }

        for (int i = 0; i < CATEGORIAS.length; i++) {
            String nombreCategoria = CATEGORIAS[i];
            String nombreFichero = FICHEROS[i];

            int idCategoria = categoriaDAO.buscarIdPorNombre(nombreCategoria);

            List<String> palabras = leerPalabras(nombreFichero);
            for (int j = 0; j < palabras.size(); j++) {
                String texto = palabras.get(j);
                // el id va a null porque lo asigna la BBDD
                Palabra palabra = new Palabra(
                        null,
                        texto,
                        String.valueOf(idCategoria)
                );
                palabraDAO.insertar(palabra);
            }
        }
    }

    // Abre un fichero de la carpeta assets y devuelve la lista de
    // palabras, una por línea, en mayúsculas. Uso try/catch/finally
    // para cerrar el BufferedReader pase lo que pase.
    private List<String> leerPalabras(String nombreFichero) {
        List<String> palabras = new ArrayList<>();
        InputStream is = null;
        BufferedReader lector = null;

        try {
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
            e.printStackTrace();
        } finally {
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