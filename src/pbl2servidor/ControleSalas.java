/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbl2servidor;

import java.util.Iterator;
import java.util.LinkedList;

/** Classe que realiza o controle das salas.
 *
 * @author Lucas Cardoso e Emanuel Santana
 */
public class ControleSalas {

    static LinkedList<Sala> salas = new LinkedList<>();

    /**Método stático que observa se a sala indicada está cheia
     * 
     * @param id Recebe o id da sala a ser observada
     * @return Retorna um valor booleano informando se está cheia ou não
     */
    public static boolean estaCheia(int id) {
        Sala aux = salas.get(id);
        LinkedList aux2 = aux.getPlayers();
        if (aux2.size() == aux.getNumJogadores()) {
            return true;
        }
        return false;
    }

    /**Método estático que verifica se as as salas estão ativas
     * 
     * @return Retorna um valor booleano informando se está ativa ou não
     */
    public static boolean estaAtiva() {
        for (Iterator<Sala> iterator = salas.iterator(); iterator.hasNext();) {
            Sala next = iterator.next();
            if (next.isAtiva()) {
                return true;
            }
        }
        return false;
    }
    
    /**Método que busca uma sala por um id específico
     * 
     * @param id Recebe o id a ser buscado
     * @return Retorna a sala encontrada
     */
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
