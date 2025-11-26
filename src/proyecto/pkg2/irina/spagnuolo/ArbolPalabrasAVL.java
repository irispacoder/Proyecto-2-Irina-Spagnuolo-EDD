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

    private int obtenerAltura(NodoPClave nodo) {
        return (nodo == null) ? 0 : nodo.getHeight();
    }
    
    private int obtenerBalance(NodoPClave nodo) {
        return (nodo == null) ? 0 : obtenerAltura(nodo.getHijoIZ()) - obtenerAltura(nodo.getHijoDE());
    }

    private NodoPClave rotacionDerecha(NodoPClave y) {
        NodoPClave x = y.getHijoIZ();
        NodoPClave T2 = x.getHijoDE();

        x.setHijoDE(y);
        y.setHijoIZ(T2);

        y.setHeight(Math.max(obtenerAltura(y.getHijoIZ()), obtenerAltura(y.getHijoDE())) + 1);
        x.setHeight(Math.max(obtenerAltura(x.getHijoIZ()), obtenerAltura(x.getHijoDE())) + 1);

        return x;
    }

    private NodoPClave rotacionIzquierda(NodoPClave x) {
        NodoPClave y = x.getHijoDE();
        NodoPClave T2 = y.getHijoIZ();

        y.setHijoIZ(x);
        x.setHijoDE(T2);

        x.setHeight(Math.max(obtenerAltura(x.getHijoIZ()), obtenerAltura(x.getHijoDE())) + 1);
        y.setHeight(Math.max(obtenerAltura(y.getHijoIZ()), obtenerAltura(y.getHijoDE())) + 1);

        return y;
    }

    public void insertarOActualizar(String palabra, String claveResumen) {
        palabra = palabra.toLowerCase().trim();
        this.raiz = insertarRecursivo(this.raiz, palabra, claveResumen);
    }

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

    public boolean buscar(String palabra) {
        return buscarRecursivo(raiz, palabra);
    }

    private boolean buscarRecursivo(NodoPClave nodo, String palabra) {
        if (nodo == null) return false;
        int cmp = comparadorEspanol.compare(palabra, nodo.getpClave());
        if (cmp == 0) return true;
        if (cmp < 0) return buscarRecursivo(nodo.getHijoIZ(), palabra);
        else return buscarRecursivo(nodo.getHijoDE(), palabra);
    }
    
    public String[] getTitulosPorPalabra(String palabra) {
        palabra = palabra.toLowerCase().trim();
        NodoPClave nodo = buscarNodo(raiz, palabra);
        if (nodo == null) return new String[0];
        return nodo.getTitulos(); 
    }
    
    private NodoPClave buscarNodo(NodoPClave actual, String palabra) {
        if (actual == null) return null;
        int cmp = comparadorEspanol.compare(palabra, actual.getpClave());
        if (cmp == 0) return actual;
        if (cmp < 0) return buscarNodo(actual.getHijoIZ(), palabra);
        else return buscarNodo(actual.getHijoDE(), palabra);
    }
    
    public void inOrden(){
        inOrdenRecursivo(raiz);
    }
    private void inOrdenRecursivo(NodoPClave Nodo){
        if (Nodo != null) {
            inOrdenRecursivo(Nodo.getHijoIZ());
            inOrdenRecursivo(Nodo.getHijoDE());
        }
    }
}

    
