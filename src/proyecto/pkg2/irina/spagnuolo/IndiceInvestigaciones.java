/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;

/**
 *
 * 
 */
public class IndiceInvestigaciones {
    private HashTable tablaTitulos;          
    private ArbolAVLAutores arbolAutores;    
    private ArbolPalabrasAVL arbolPalabras;  
    
    public IndiceInvestigaciones(int capacidad) {
        tablaTitulos = new HashTable(capacidad);
        arbolAutores = new ArbolAVLAutores();
        arbolPalabras = new ArbolPalabrasAVL();
    }

    public void agregarResumen(Resumen resumen) {
        tablaTitulos.agregarElem(resumen);

        String[] autores = resumen.getAutores();
        for (int i = 0; i < autores.length; i++) {
            arbolAutores.insertarOActualizar(autores[i], resumen);
        }

        String[] palabras = resumen.getKeyword();
        for (int i = 0; i < palabras.length; i++) {
            arbolPalabras.insertarOActualizar(palabras[i], resumen.getTitulo());
        }
    }

    public Resumen buscarPorTitulo(String titulo) {
        return tablaTitulos.buscarElem(titulo);
    }

    public Autores buscarPorAutor(String nombreAutor) {
        return arbolAutores.buscar(nombreAutor);
    }

    public boolean existePalabraClave(String palabra) {
        return arbolPalabras.buscar(palabra);
    }

    public String[] obtenerTitulos() {
        return tablaTitulos.obtenerTitulos();
    }

    public String[] obtenerAutoresOrdenados() {
        return arbolAutores.inOrdenArray();
    }

}
