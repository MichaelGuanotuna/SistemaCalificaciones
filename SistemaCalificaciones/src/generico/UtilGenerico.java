
package generico;

import java.util.ArrayList;

public class UtilGenerico<T> {

    // Agregar varios elementos a una lista
    @SafeVarargs
    public final void agregarVarios(ArrayList<T> lista, T... elementos) {

        for (int i = 0; i < elementos.length; i++) {
            lista.add(elementos[i]);
        }
    }

    // Mostrar todos los elementos de una lista
    public void mostrarElementos(ArrayList<T> lista) {

        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i));
        }
    }

    // Buscar un elemento dentro de una lista
    public boolean contiene(ArrayList<T> lista, T elemento) {

        for (int i = 0; i < lista.size(); i++) {

            if (lista.get(i).equals(elemento)) {
                return true;
            }
        }

        return false;
    }
}

