/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.itson.benito.enumerador;

/**
 *
 * @author Jesus Javier Quintero Fierro
 */
public enum Estado {
    ABIERTO(0),
    CERRADO(1),
    CANCELADO(2);
    
    private int numero;
    Estado(int numero){
        this.numero=numero;
    }
}
