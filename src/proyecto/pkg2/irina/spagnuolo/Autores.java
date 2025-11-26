/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;

/**
 *
 * 
 */
public class Autores {
    private String nombreAutor;
    private int altura;
    private Autores hijoIzquierdo;
    private Autores hijoDerecho;
    private Lista<Resumen> resumenes;

    public Autores(String nombreAutor, Resumen resumen) {
        this.nombreAutor = nombreAutor;
        this.altura = 1;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
        this.resumenes = new Lista<>();
        this.resumenes.addFinal(resumen);
    }
    
    public void addResumen(Resumen resumen){
        this.resumenes.addFinal(resumen);
    }

    /**
     * @return the nombreAutor
     */
    public String getNombreAutor() {
        return nombreAutor;
    }

    /**
     * @param nombreAutor the nombreAutor to set
     */
    public void setNombreAutor(String nombreAutor) {
        this.nombreAutor = nombreAutor;
    }

    /**
     * @return the altura
     */
    public int getAltura() {
        return altura;
    }

    /**
     * @param altura the altura to set
     */
    public void setAltura(int altura) {
        this.altura = altura;
    }

    /**
     * @return the hijoIzquierdo
     */
    public Autores getHijoIzquierdo() {
        return hijoIzquierdo;
    }

    /**
     * @param hijoIzquierdo the hijoIzquierdo to set
     */
    public void setHijoIzquierdo(Autores hijoIzquierdo) {
        this.hijoIzquierdo = hijoIzquierdo;
    }

    /**
     * @return the hijoDerecho
     */
    public Autores getHijoDerecho() {
        return hijoDerecho;
    }

    /**
     * @param hijoDerecho the hijoDerecho to set
     */
    public void setHijoDerecho(Autores hijoDerecho) {
        this.hijoDerecho = hijoDerecho;
    }

    /**
     * @return the resumenes
     */
    public Lista<Resumen> getResumenes() {
        return resumenes;
    }

    /**
     * @param resumenes the resumenes to set
     */
    public void setResumenes(Lista<Resumen> resumenes) {
        this.resumenes = resumenes;
    }
    
    
    
    
}
