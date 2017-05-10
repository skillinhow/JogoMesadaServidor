/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbl2servidor;

import java.util.Iterator;
import java.util.LinkedList;

/**
 *
 * @author thelu
 */
public class ControleSalas {

    static LinkedList<Sala> salas = new LinkedList<>();

    public static boolean estaCheia(int id) {
        Sala aux = salas.get(id);
        LinkedList aux2 = aux.getPlayers();
        if (aux2.size() == aux.getNumJogadores()) {
            return true;
        }
        return false;
    }

    public static boolean estaAtiva() {
        for (Iterator<Sala> iterator = salas.iterator(); iterator.hasNext();) {
            Sala next = iterator.next();
            if (next.isAtiva()) {
                return true;
            }
        }
        return false;
    }
    
    public static Sala buscaSala(int id){
        for (Iterator<Sala> iterator = salas.iterator(); iterator.hasNext();) {
            Sala next = iterator.next();
            if (next.getId() == id) {
                return next;
            }
        }
        return null;
    }
}
