/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbl2servidor;

/**Classe que define a estrutura dos jogadoes
 *
 * @author Lucas Cardoso e Emanuel Santana
 */
public class Jogadores {
    
    private String nick;
    private String ip;
    private String porta;
    private int sala;

    /**Método construtor da classe
     * 
     * @param nick Recebe o nick do cliente
     * @param ip Recebe o ip do cliente
     * @param porta Recebe a porta do cliente
     */
    public Jogadores(String nick, String ip, String porta) {
        this.nick = nick;
        this.ip = ip;
        this.porta = porta;
    }

    public String getNick() {
        return nick;
    }

    public int getSala() {
        return sala;
    }

    public void setSala(int sala) {
        this.sala = sala;
    }

    public String getIp() {
        return ip;
    }

    public String getPorta() {
        return porta;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }
    
    
}
