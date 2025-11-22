/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.pkg2.irina.spagnuolo;

/**
 *
 *
 */
public class Lista<T> {
    private Nodo<T> head;
    private int size;

    public Lista() {
        this.head = null;
        this.size = 0;
    }
    
    public boolean esVacia(){
        return getHead() == null;
    }
    
    //esto agrega al inicio
    public void addInicio(T dato){
        Nodo<T> newNodo = new Nodo<>(dato);
        newNodo.setNext(head);
        head = newNodo;
        size++;
    }
    
    //aqui se agrega al final
    public void addFinal(T dato){
        Nodo<T> newNodo = new Nodo<>(dato);
        if (this.esVacia()) {
            this.setHead(newNodo);
        }else{
            Nodo<T> actual = this.getHead();
            while (actual.getNext() != null) {
                actual=actual.getNext();
            }
            actual.setNext(newNodo);
        }
        size++;
    }
    
    //metodo para buscar
    public boolean searchElem(T dato){
        Nodo<T> actual = this.getHead();
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }
    
    public boolean eliminar(T dato){
        if (this.esVacia()) {
           return false; 
        }
        
        if (head.getDato().equals(dato)) {
            head = head.getNext();
            size--;
            return true;
        }
        
        Nodo<T> actual = this.head;
        while (actual.getNext() != null) {
            if(actual.getNext().getDato().equals(dato)){
                actual.setNext(actual.getNext().getNext());
                size--;
                return true;
            }
            actual = actual.getNext();
        }
        return false;
    }
    
   
    
    
    

    /**
     * @return the head
     */
    public Nodo<T> getHead() {
        return head;
    }

    /**
     * @param head the head to set
     */
    public void setHead(Nodo<T> head) {
        this.head = head;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }
    
    
    
    
}
