/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;
import java.text.Collator;
import java.util.Locale;

/**
 *
 *
 */
public class ArbolAVLAutores {
    private Autores raiz;
    private final Collator comparadorEspanol;

    public ArbolAVLAutores() {
        this.raiz = null;
        this.comparadorEspanol = Collator.getInstance(new Locale("es", "ES"));
        this.comparadorEspanol.setStrength(Collator.PRIMARY);
    }

    private int obtenerAltura(Autores nodo) {
        return (nodo == null) ? 0 : nodo.getAltura();
    }

    private int obtenerFactorBalanceo(Autores nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.getHijoIzquierdo()) - obtenerAltura(nodo.getHijoDerecho());
    }

    private Autores rotarDerecha(Autores y) {
        Autores x = y.getHijoIzquierdo();
        Autores T2 = x.getHijoDerecho();

        x.setHijoDerecho(y);
        y.setHijoIzquierdo(T2);

        y.setAltura(1 + Math.max(obtenerAltura(y.getHijoIzquierdo()), obtenerAltura(y.getHijoDerecho())));
        x.setAltura(1 + Math.max(obtenerAltura(x.getHijoIzquierdo()), obtenerAltura(x.getHijoDerecho())));

        return x;
    }

    private Autores rotarIzquierda(Autores x) {
        Autores y = x.getHijoDerecho();
        Autores T2 = y.getHijoIzquierdo();

        y.setHijoIzquierdo(x);
        x.setHijoDerecho(T2);

        x.setAltura(1 + Math.max(obtenerAltura(x.getHijoIzquierdo()), obtenerAltura(x.getHijoDerecho())));
        y.setAltura(1 + Math.max(obtenerAltura(y.getHijoIzquierdo()), obtenerAltura(y.getHijoDerecho())));

        return y;
    }

    private Autores balancear(Autores actual) {
        actual.setAltura(1 + Math.max(obtenerAltura(actual.getHijoIzquierdo()), obtenerAltura(actual.getHijoDerecho())));
        int factorBalanceo = obtenerFactorBalanceo(actual);

        if (factorBalanceo > 1) {
            if (obtenerFactorBalanceo(actual.getHijoIzquierdo()) >= 0) {
                return rotarDerecha(actual);
            } else {
                actual.setHijoIzquierdo(rotarIzquierda(actual.getHijoIzquierdo()));
                return rotarDerecha(actual);
            }
        }

        if (factorBalanceo < -1) {
            if (obtenerFactorBalanceo(actual.getHijoDerecho()) <= 0) {
                return rotarIzquierda(actual);
            } else {
                actual.setHijoDerecho(rotarDerecha(actual.getHijoDerecho()));
                return rotarIzquierda(actual);
            }
        }

        return actual;
    }

    public void insertarOActualizar(String autor, Resumen resumen) {
        raiz = insertarRecursivo(raiz, autor, resumen);
    }

    private Autores insertarRecursivo(Autores actual, String autor, Resumen resumen) {
        if (actual == null) {
            return new Autores(autor, resumen);
        }

        int cmp = comparadorEspanol.compare(autor, actual.getNombreAutor());

        if (cmp < 0) {
            actual.setHijoIzquierdo(insertarRecursivo(actual.getHijoIzquierdo(), autor, resumen));
        } else if (cmp > 0) {
            actual.setHijoDerecho(insertarRecursivo(actual.getHijoDerecho(), autor, resumen));
        } else {
            actual.addResumen(resumen);
            return actual;
        }

        return balancear(actual);
    }

    public Autores buscar(String autor) {
        return buscarRecursivo(raiz, autor);
    }

    private Autores buscarRecursivo(Autores nodo, String autor) {
        if (nodo == null) return null;
        int cmp = comparadorEspanol.compare(autor, nodo.getNombreAutor());
        if (cmp == 0) return nodo;
        if (cmp < 0) return buscarRecursivo(nodo.getHijoIzquierdo(), autor);
        else return buscarRecursivo(nodo.getHijoDerecho(), autor);
    }

    public void recorrerInorden() {
        recorrerInordenRecursivo(raiz);
    }

    private void recorrerInordenRecursivo(Autores nodo) {
        if (nodo != null) {
            recorrerInordenRecursivo(nodo.getHijoIzquierdo());
            System.out.println(nodo.getNombreAutor());
            recorrerInordenRecursivo(nodo.getHijoDerecho());
        }
    }

    public String[] inOrdenArray() {
        int total = contarAutores(raiz);
        String[] autores = new String[total];
        llenarArrayInOrden(raiz, autores, new int[]{0}); // usamos un índice mutable
        return autores;
    
    }
    
    private int contarAutores(Autores nodo) {
        if (nodo == null) return 0;
        return 1 + contarAutores(nodo.getHijoIzquierdo()) + contarAutores(nodo.getHijoDerecho());
    }
    
    private void llenarArrayInOrden(Autores nodo, String[] autores, int[] index) {
        if (nodo != null) {
            llenarArrayInOrden(nodo.getHijoIzquierdo(), autores, index);
            autores[index[0]++] = nodo.getNombreAutor();
            llenarArrayInOrden(nodo.getHijoDerecho(), autores, index);
        }
    }
    
    public Lista buscarTitulos(String autor) {
        Autores nodo = buscar(autor);
        if (nodo == null) return null;

        Lista titulos = new Lista();
        Nodo actual = nodo.getResumenes().getHead();
        while (actual != null) {
            Resumen resumen = (Resumen) actual.getDato();
            titulos.addFinal(resumen.getTitulo());
            actual = actual.getNext();
        }
        return titulos;
    }

    
}
