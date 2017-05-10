/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbl2servidor;

import java.util.LinkedList;

/**
 *
 * @author thelu
 */
public class Sala {
    
    private static int idcont = 0;
    private int id;
    private int numJogadores;
    private LinkedList<Jogadores> players;
    private int duracao;
    private boolean ativa;

    public Sala(int numJogadores, int duracao) {
        this.id =  idcont;
        this.numJogadores = numJogadores;
        this.duracao = duracao;
        this.players = new LinkedList<>();
        this.ativa = true;
        idcont++;
    }

    public int getId() {
        return id;
    }

    public int getNumJogadores() {
        return numJogadores;
    }

    public LinkedList<Jogadores> getPlayers() {
        return players;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }
    
    
    
}
