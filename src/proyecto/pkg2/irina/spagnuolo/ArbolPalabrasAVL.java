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
public class ArbolPalabrasAVL {
    private NodoPClave raiz;
    private final Collator comparadorEspanol;

    public ArbolPalabrasAVL() {
        this.raiz = null;
        this.comparadorEspanol = Collator.getInstance(new Locale("es", "ES"));
        this.comparadorEspanol.setStrength(Collator.PRIMARY);
    }

    /**
     * 
     * @param nodo
     * @return 
     */
    private int obtenerAltura(NodoPClave nodo) {
        return (nodo == null) ? 0 : nodo.getHeight();
    }
    
    /**
     * 
     * @param nodo
     * @return 
     */
    private int obtenerBalance(NodoPClave nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.getHijoIZ()) - obtenerAltura(nodo.getHijoDE());
    }

    /**
     * 
     * @param y
     * @return 
     */
    private NodoPClave rotacionDerecha(NodoPClave y) {
        NodoPClave x = y.getHijoIZ();
        NodoPClave T2 = x.getHijoDE();

        x.setHijoDE(y);
        y.setHijoIZ(T2);

        y.setHeight(Math.max(obtenerAltura(y.getHijoIZ()), obtenerAltura(y.getHijoDE())) + 1);
        x.setHeight(Math.max(obtenerAltura(x.getHijoIZ()), obtenerAltura(x.getHijoDE())) + 1);

        return x;
    }

    /**
     * 
     * @param x
     * @return 
     */
    private NodoPClave rotacionIzquierda(NodoPClave x) {
        NodoPClave y = x.getHijoDE();
        NodoPClave T2 = y.getHijoIZ();

        y.setHijoIZ(x);
        x.setHijoDE(T2);

        x.setHeight(Math.max(obtenerAltura(x.getHijoIZ()), obtenerAltura(x.getHijoDE())) + 1);
        y.setHeight(Math.max(obtenerAltura(y.getHijoIZ()), obtenerAltura(y.getHijoDE())) + 1);

        return y;
    }

    /**
     * 
     * @param palabra
     * @param claveResumen 
     */
    public void insertarOActualizar(String palabra, String claveResumen) {
        palabra = palabra.toLowerCase().trim();
        this.raiz = insertarRecursivo(this.raiz, palabra, claveResumen);
    }

    /**
     * 
     * @param actual
     * @param palabra
     * @param claveResumen
     * @return 
     */
    private NodoPClave insertarRecursivo(NodoPClave actual, String palabra, String claveResumen) {
        if (actual == null) {
            return new NodoPClave(palabra, claveResumen);
        }

        int resultadoComparacion = this.comparadorEspanol.compare(palabra, actual.getpClave());

        if (resultadoComparacion < 0) {
            actual.setHijoIZ(insertarRecursivo(actual.getHijoIZ(), palabra, claveResumen));
        } else if (resultadoComparacion > 0) {
            actual.setHijoDE(insertarRecursivo(actual.getHijoDE(), palabra, claveResumen));
        } else {
            actual.Factualizada(claveResumen);
            return actual;
        }

        actual.setHeight(1 + Math.max(obtenerAltura(actual.getHijoIZ()), obtenerAltura(actual.getHijoDE())));

        int balance = obtenerBalance(actual);

        if (balance > 1 && comparadorEspanol.compare(palabra, actual.getHijoIZ().getpClave()) < 0) {
            return rotacionDerecha(actual);
        }
        if (balance < -1 && comparadorEspanol.compare(palabra, actual.getHijoDE().getpClave()) > 0) {
            return rotacionIzquierda(actual);
        }
        if (balance > 1 && comparadorEspanol.compare(palabra, actual.getHijoIZ().getpClave()) > 0) {
            actual.setHijoIZ(rotacionIzquierda(actual.getHijoIZ()));
            return rotacionDerecha(actual);
        }
        if (balance < -1 && comparadorEspanol.compare(palabra, actual.getHijoDE().getpClave()) < 0) {
            actual.setHijoDE(rotacionDerecha(actual.getHijoDE()));
            return rotacionIzquierda(actual);
        }

        return actual;
    }

    /**
     * 
     * @param palabra
     * @return 
     */
    public boolean buscar(String palabra) {
        return buscarRecursivo(raiz, palabra);
    }

    /**
     * 
     * @param nodo
     * @param palabra
     * @return 
     */
    private boolean buscarRecursivo(NodoPClave nodo, String palabra) {
        if (nodo == null) return false;
        int cmp = comparadorEspanol.compare(palabra, nodo.getpClave());
        if (cmp == 0) return true;
        if (cmp < 0) return buscarRecursivo(nodo.getHijoIZ(), palabra);
        else return buscarRecursivo(nodo.getHijoDE(), palabra);
    }
    
    /**
     * 
     * @param palabra
     * @return 
     */
    public String[] getTitulosPorPalabra(String palabra) {
        palabra = palabra.toLowerCase().trim();
        NodoPClave nodo = buscarNodo(raiz, palabra);
        if (nodo == null) return new String[0];
        return nodo.getTitulos(); 
    }
    
    /**
     * 
     * @param actual
     * @param palabra
     * @return 
     */
    private NodoPClave buscarNodo(NodoPClave actual, String palabra) {
        if (actual == null) return null;
        int cmp = comparadorEspanol.compare(palabra, actual.getpClave());
        if (cmp == 0) return actual;
        if (cmp < 0) return buscarNodo(actual.getHijoIZ(), palabra);
        else return buscarNodo(actual.getHijoDE(), palabra);
    }
    
    /**
     * 
     */
    public void inOrden(){
        inOrdenRecursivo(raiz);
    }
    /**
     * 
     * @param Nodo 
     */
    private void inOrdenRecursivo(NodoPClave Nodo){
        if (Nodo != null) {
            inOrdenRecursivo(Nodo.getHijoIZ());
            inOrdenRecursivo(Nodo.getHijoDE());
        }
    }
    
    /**
     * 
     * @return 
     */
    public String[] inOrdenArray() {
        int total = contarPalabras(raiz);
        String[] palabras = new String[total];
        llenarArrayInOrden(raiz, palabras, new int[]{0});
        return palabras;
    }

    private int contarPalabras(NodoPClave nodo) {
        if (nodo == null) return 0;
        return 1 + contarPalabras(nodo.getHijoIZ()) + contarPalabras(nodo.getHijoDE());
    }

    /**
     * 
     * @param nodo
     * @param palabras
     * @param index 
     */
    private void llenarArrayInOrden(NodoPClave nodo, String[] palabras, int[] index) {
        if (nodo != null) {
            llenarArrayInOrden(nodo.getHijoIZ(), palabras, index);
            palabras[index[0]++] = nodo.getpClave();
            llenarArrayInOrden(nodo.getHijoDE(), palabras, index);
        }
    }

    /**
     * 
     * @param palabra
     * @return 
     */
    public Lista buscarResúmenes(String palabra) {
        NodoPClave nodo = buscarRec(raiz, palabra);
        return (nodo == null) ? null : nodo.getFrecuenciaLista();
    }

    /**
     * 
     * @param nodo
     * @param palabra
     * @return 
     */
    private NodoPClave buscarRec(NodoPClave nodo, String palabra) {
        if (nodo == null) return null;
        int cmp = comparadorEspanol.compare(palabra, nodo.getpClave());
        if (cmp == 0) return nodo;
        if (cmp < 0) return buscarRec(nodo.getHijoIZ(), palabra);
        else return buscarRec(nodo.getHijoDE(), palabra);
    }
}

    
