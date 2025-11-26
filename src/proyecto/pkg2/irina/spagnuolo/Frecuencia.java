/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;

/**
 *Clas manejja la frecuencia de las palabras clave en los resumenes
 * 
 */
public class Frecuencia {
    private String pClave;
    private String claveResumen;
    private int frecuencia;

    public Frecuencia(String pClave, String claveResumen) {
        this.pClave = pClave;
        this.claveResumen = claveResumen;
        this.frecuencia = 1;
    }

    /**
     * @return the pClave
     */
    public String getpClave() {
        return pClave;
    }

    /**
     * @param pClave the pClave to set
     */
    public void setpClave(String pClave) {
        this.pClave = pClave;
    }

    /**
     * @return the claveResumen
     */
    public String getClaveResumen() {
        return claveResumen;
    }

    /**
     * @param claveResumen the claveResumen to set
     */
    public void setClaveResumen(String claveResumen) {
        this.claveResumen = claveResumen;
    }

    /**
     * @return the frecuencia
     */
    public int getFrecuencia() {
        return frecuencia;
    }

    /**
     * @param frecuencia the frecuencia to set
     */
    public void setFrecuencia(int frecuencia) {
        this.frecuencia = frecuencia;
    }
    
    /**
     * 
     */
    public void incrementoF(){
        this.frecuencia++;
    }
    
    
    
    
}
