/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;

/**
 *
 *
 */
public class HashTable {
    private Lista[] tabla; 
    private int capacidad;
    private int tamaño;

    public HashTable(int capacidad) {
        this.tabla = new Lista[capacidad];
        this.capacidad = capacidad;
        this.tamaño = 0;
        for (int i = 0; i < capacidad; i++) {
            tabla[i] = new Lista();
        }
    }

    public Lista[] getTabla() {
        return tabla;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getTamaño() {
        return tamaño;
    }

    private int fHash(String titulo) {
        int hash = 0;
        for (int i = 0; i < titulo.length(); i++) {
            hash += titulo.charAt(i);
        }
        return hash % capacidad;
    }

    public void agregarElem(Resumen resumen) {
        int indice = fHash(resumen.getTitulo());
        if (!contiene(resumen.getTitulo())) {
            tabla[indice].addFinal(resumen);
            tamaño++;
        }
    }


    public Resumen buscarElem(String titulo) {
        int indice = fHash(titulo);
        Nodo actual = tabla[indice].getHead();

        while (actual != null) {
            Resumen resumen = (Resumen) actual.getDato();
            if (compararTitulos(resumen.getTitulo(), titulo)) {
                return resumen;
            }
            actual = actual.getNext();
        }
        return null;
    }

    public boolean contiene(String titulo) {
        return buscarElem(titulo) != null;
    }

    public boolean eliminarElem(String titulo) {
        int indice = fHash(titulo);
        Nodo actual = tabla[indice].getHead();

        while (actual != null) {
            Resumen resumen = (Resumen) actual.getDato();
            if (compararTitulos(resumen.getTitulo(), titulo)) {
                tabla[indice].eliminar(resumen);
                tamaño--;
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }

    public Lista obtenerTodos() {
        Lista todos = new Lista();
        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i].getHead();
            while (actual != null) {
                todos.addFinal(actual.getDato());
                actual = actual.getNext();
            }
        }
        return todos;
    }
    
    public String[] obtenerTitulos() {
        String[] titulos = new String[tamaño];
        int index = 0;

        for (int i = 0; i < capacidad; i++) {
            Nodo actual = tabla[i].getHead();
            while (actual != null) {
                Resumen resumen = (Resumen) actual.getDato();
                titulos[index++] = resumen.getTitulo();
                actual = actual.getNext();
            }
        }
        return titulos;
    }

    private boolean compararTitulos(String titulo1, String titulo2) {
        if (titulo1 == null && titulo2 == null) return true;
        if (titulo1 == null || titulo2 == null) return false;
        if (titulo1.length() != titulo2.length()) return false;

        for (int i = 0; i < titulo1.length(); i++) {
            if (titulo1.charAt(i) != titulo2.charAt(i)) return false;
        }
        return true;
    }
    
    public void insertar(String clave, Object valor){
        int indice = fHash(clave);
    }
    
    public Object buscar(String clave){
        int indice = fHash(clave);
        return null;
    }
}
