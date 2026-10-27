
package listas;

import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Lista simplemente enlazada genérica.
 */
public class ListaEnlazada<T> {

    private Nodo<T> cabeza;
    private int cantidad;

    public ListaEnlazada() {
        this.cabeza = null;
        this.cantidad = 0;
    }

    public boolean estaVacia() {
        return cantidad == 0;
    }

    public int tamanio() {
        return cantidad;
    }

    public void agregarAlInicio(T dato) {
        Nodo<T> nuevoNodo = new Nodo<T>(dato);

        nuevoNodo.setSiguiente(this.cabeza);
        this.cabeza = nuevoNodo;
        this.cantidad++;
    }

    public void agregarAlFinal(T dato) {
        if (estaVacia()) {
            agregarAlInicio(dato);
        } else {
            Nodo<T> nuevoNodo = new Nodo<T>(dato);
            Nodo<T> puntero = this.cabeza;

            while (puntero.getSiguiente() != null) {
                puntero = puntero.getSiguiente();
            }

            puntero.setSiguiente(nuevoNodo);
            this.cantidad++;
        }
    }

    public T obtenerPrimero() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        return cabeza.getDato();
    }

    public T obtenerUltimo() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        Nodo<T> puntero = cabeza;

        while (puntero.getSiguiente() != null) {
            puntero = puntero.getSiguiente();
        }

        return puntero.getDato();
    }

    public T obtener(int indice) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> puntero = cabeza;

        for (int i = 0; i < indice; i++) {
            puntero = puntero.getSiguiente();
        }

        return puntero.getDato();
    }

    public boolean contiene(T dato) {
        return indiceDe(dato) != -1;
    }

    public int indiceDe(T dato) {
        Nodo<T> puntero = cabeza;
        int indice = 0;

        while (puntero != null) {
            if (Objects.equals(puntero.getDato(), dato)) {
                return indice;
            }

            puntero = puntero.getSiguiente();
            indice++;
        }

        return -1;
    }

    public void insertar(int indice, T dato) {
        if (indice < 0 || indice > cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        if (indice == 0) {
            agregarAlInicio(dato);
            return;
        }

        if (indice == cantidad) {
            agregarAlFinal(dato);
            return;
        }

        Nodo<T> nuevoNodo = new Nodo<T>(dato);
        Nodo<T> anterior = cabeza;

        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }

        nuevoNodo.setSiguiente(anterior.getSiguiente());
        anterior.setSiguiente(nuevoNodo);
        cantidad++;
    }

    public T reemplazar(int indice, T dato) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Nodo<T> puntero = cabeza;

        for (int i = 0; i < indice; i++) {
            puntero = puntero.getSiguiente();
        }

        T datoAnterior = puntero.getDato();
        puntero.setDato(dato);

        return datoAnterior;
    }

    public T eliminarPrimero() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        T datoEliminado = cabeza.getDato();

        cabeza = cabeza.getSiguiente();
        cantidad--;

        return datoEliminado;
    }

    public T eliminarUltimo() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        if (cantidad == 1) {
            return eliminarPrimero();
        }

        Nodo<T> puntero = cabeza;

        while (puntero.getSiguiente().getSiguiente() != null) {
            puntero = puntero.getSiguiente();
        }

        T datoEliminado = puntero.getSiguiente().getDato();

        puntero.setSiguiente(null);
        cantidad--;

        return datoEliminado;
    }

    public T eliminar(int indice) {
        if (indice < 0 || indice >= cantidad) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        if (indice == 0) {
            return eliminarPrimero();
        }

        Nodo<T> anterior = cabeza;

        for (int i = 0; i < indice - 1; i++) {
            anterior = anterior.getSiguiente();
        }

        Nodo<T> nodoEliminado = anterior.getSiguiente();

        anterior.setSiguiente(nodoEliminado.getSiguiente());
        cantidad--;

        return nodoEliminado.getDato();
    }

    public boolean eliminarValor(T dato) {
        int indice = indiceDe(dato);

        if (indice == -1) {
            return false;
        }

        eliminar(indice);
        return true;
    }

    public void vaciar() {
        cabeza = null;
        cantidad = 0;
    }


    @Override
    public String toString() {
        StringBuilder texto = new StringBuilder("[");
        Nodo<T> puntero = cabeza;

        while (puntero != null) {
            texto.append(puntero.getDato());

            if (puntero.getSiguiente() != null) {
                texto.append(", ");
            }

            puntero = puntero.getSiguiente();
        }

        texto.append("]");
        return texto.toString();
    }


    public void invertir() {
        Nodo<T> anterior = null;
        Nodo<T> actual = cabeza;
        Nodo<T> siguiente;

        while (actual != null) {
            siguiente = actual.getSiguiente();
            actual.setSiguiente(anterior);

            anterior = actual;
            actual = siguiente;
        }

        cabeza = anterior;
    }

    public T obtenerMedio() {
        if (estaVacia()) {
            throw new NoSuchElementException("La lista está vacía");
        }

        Nodo<T> lento = cabeza;
        Nodo<T> rapido = cabeza;

        while (rapido != null && rapido.getSiguiente() != null) {
            lento = lento.getSiguiente();
            rapido = rapido.getSiguiente().getSiguiente();
        }

        return lento.getDato();
    }

    public void eliminarDuplicados() {
        Nodo<T> actual = cabeza;

        while (actual != null) {
            Nodo<T> anterior = actual;
            Nodo<T> siguiente = actual.getSiguiente();

            while (siguiente != null) {
                if (Objects.equals(actual.getDato(), siguiente.getDato())) {
                    anterior.setSiguiente(siguiente.getSiguiente());
                    cantidad--;
                    siguiente = anterior.getSiguiente();
                } else {
                    anterior = siguiente;
                    siguiente = siguiente.getSiguiente();
                }
            }

            actual = actual.getSiguiente();
        }
    }
}
