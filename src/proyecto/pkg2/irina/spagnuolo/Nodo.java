/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;

/**
 *
 * Clase nodo
 * @param <T>
 */
public class Nodo<T> {
    private T dato;
    private Nodo<T> next;

    public Nodo(T dato) {
        this.dato = dato;
        this.next = null;
    }

    /**
     * @return the dato
     */
    public T getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * @return the next
     */
    public Nodo<T> getNext() {
        return next;
    }

    /**
     * @param next the next to set
     */
    public void setNext(Nodo<T> next) {
        this.next = next;
    }
    
    
    
    
}
