/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insieme2;

/**
 *
 * @author Carmelo
 */
public class ElementoNonPresenteException extends Exception {
    private int elemento;

    public ElementoNonPresenteException(int elemento) {
        this.elemento = elemento;
    }

    @Override
    public String toString() {
        return "ElementoNonPresenteException{" + "elemento=" + elemento + '}';
    }
    
}
