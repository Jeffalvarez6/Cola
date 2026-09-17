import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Stack;

public class Cola<T> implements Iterable<T> {
    private static class Nodo<T> {
        T info;
        Nodo<T> sig;
        Nodo(T info) { this.info = info; }
    }

    private Nodo<T> frente;
    private Nodo<T> fin;
    private int tamano;

    public void agregar(T elem) {
        Nodo<T> nuevo = new Nodo<>(elem);
        if (estaVacia()) {
            frente = nuevo;
        } else {
            fin.sig = nuevo;
        }
        fin = nuevo;
        tamano++;
    }

    public T eliminar() {
        if (estaVacia()) throw new NoSuchElementException("Cola vacía");
        T elem = frente.info;
        frente = frente.sig;
        if (frente == null) fin = null;
        tamano--;
        return elem;
    }

    public boolean estaVacia() { return frente == null; }
    public int tamano() { return tamano; }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Nodo<T> actual = frente;

            @Override
            public boolean hasNext() {
                return actual != null;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                T dato = actual.info;
                actual = actual.sig;
                return dato;
            }
        };
    }

    public T obtenerUltimo() {
        if (estaVacia()) throw new NoSuchElementException("La cola está vacía.");
        return fin.info;
    }

    public Cola<T> alReves() {
        Cola<T> colaInvertida = new Cola<>();
        Stack<T> pilaAux = new Stack<>();

        for (T elem : this) {
            pilaAux.push(elem);
        }
        while (!pilaAux.isEmpty()) {
            colaInvertida.agregar(pilaAux.pop());
        }
        return colaInvertida;
    }

    public void concatenar(Cola<T> otraCola) {
        for (T elem : otraCola) {
            this.agregar(elem);
        }
    }

    public static <T> Cola<T> intercalar(Cola<T> c1, Cola<T> c2) {
        Cola<T> resultado = new Cola<>();
        Iterator<T> it1 = c1.iterator();
        Iterator<T> it2 = c2.iterator();

        while (it1.hasNext() || it2.hasNext()) {
            if (it1.hasNext()) resultado.agregar(it1.next());
            if (it2.hasNext()) resultado.agregar(it2.next());
        }
        return resultado;
    }
}