/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;

/**
 *
 * 
 */
public class NodoPClave {
    private String pClave;
    private int height;
    private NodoPClave hijoIZ;
    private NodoPClave hijoDE;
    private Lista<Frecuencia> frecuenciaLista;

    public NodoPClave(String palabra, String claveResumen) {
        this.pClave = palabra;
        this.height = 1;
        this.hijoIZ = null;
        this.hijoDE = null;
        this.frecuenciaLista = new Lista<>();
        
        Frecuencia primeraFrecuencia = new Frecuencia(palabra, claveResumen);
        this.frecuenciaLista.addFinal(primeraFrecuencia);
    }
    
    public void Factualizada(String claveResumen){
        Nodo<Frecuencia> actual = this.frecuenciaLista.getHead();
        while (actual != null) {
            Frecuencia af = actual.getDato();
            if (af.getClaveResumen().equals(claveResumen)) {
                af.incrementoF();
                return;
            }
            actual = actual.getNext();
        }
        
        Frecuencia nuevaFrecuencia = new Frecuencia(pClave, claveResumen);
        this.frecuenciaLista.addFinal(nuevaFrecuencia);
    
    }
    
    public String[] getTitulos() {
        int count = frecuenciaLista.getSize();
        String[] titulos = new String[count];
        int i = 0;
        Nodo<Frecuencia> actual = frecuenciaLista.getHead();
        while (actual != null) {
            titulos[i++] = actual.getDato().getClaveResumen();
            actual = actual.getNext();
        }
        return titulos;
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
     * @return the height
     */
    public int getHeight() {
        return height;
    }

    /**
     * @param height the height to set
     */
    public void setHeight(int height) {
        this.height = height;
    }

    /**
     * @return the hijoIZ
     */
    public NodoPClave getHijoIZ() {
        return hijoIZ;
    }

    /**
     * @param hijoIZ the hijoIZ to set
     */
    public void setHijoIZ(NodoPClave hijoIZ) {
        this.hijoIZ = hijoIZ;
    }

    /**
     * @return the hijoDE
     */
    public NodoPClave getHijoDE() {
        return hijoDE;
    }

    /**
     * @param hijoDE the hijoDE to set
     */
    public void setHijoDE(NodoPClave hijoDE) {
        this.hijoDE = hijoDE;
    }

    /**
     * @return the frecuenciaLista
     */
    public Lista<Frecuencia> getFrecuenciaLista() {
        return frecuenciaLista;
    }

    /**
     * @param frecuenciaLista the frecuenciaLista to set
     */
    public void setFrecuenciaLista(Lista<Frecuencia> frecuenciaLista) {
        this.frecuenciaLista = frecuenciaLista;
    }
    
    
    
}
