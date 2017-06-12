/*
 * Comtém as Classes responsáveis pela criação e funcionamento do servidor
 */
package pbl2servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe que controla todo o funcionamento do sistema, recebendo solicitações
 * do cliente e enviando confirmações e erros para o mesmo.
 *
 * @author Lucas Cardoso
 */
public class Atendente extends Thread {

    private Socket sktCli;
    private Conexao server;
    private boolean salaCheia;
    private int id;

    /**
     * Construtor da classe Atendente.
     *
     * @param sktCli Parâmetro que recebe a conexão do cliente.
     * @param server Parâmetro que recebe o objeto conexão do servidor.
     */
    public Atendente(Socket sktCli, Conexao server) {
        this.sktCli = sktCli;
        this.server = server;
        this.salaCheia = false;

    }

    /**
     * Método principal da Classe Atendente, responsável por realizar o controle
     * das operações selecionadas pelo usuário. Responsável também pelo código
     * executado em cada Thread no sistema.
     *
     */
    @Override
    public void run() {

        try {
            ObjectOutputStream oo = new ObjectOutputStream(sktCli.getOutputStream());
            ObjectInputStream oi = new ObjectInputStream(sktCli.getInputStream());

            String x = (String) oi.readObject();
            String[] aux = x.split("@");

            System.out.println(x);

            if ((aux[0].equals("E") && Conexao.count == 1) || (aux[0].equals("E") && !ControleSalas.estaAtiva())) {
                oo.writeObject("C");
                oo.flush();

                x = (String) oi.readObject();
                aux = x.split("@");

                if (aux[0].equals("C")) {
                    criaSala(aux[1], aux[2], aux[3]);
                    System.out.println("Configurações: jogadores: " + aux[1] + " duração: " + aux[2] + " mese(s)");
                    oo.writeObject("H");
                    oo.flush();
                }
            } else if (aux[0].equals("E")) {
                entraSala(aux[1]);
                oo.writeObject("H");
                oo.flush();
            }

            boolean cheio = false;
            do {
                x = (String) oi.readObject();
                aux = x.split("@");
//            Sala salaaux = ControleSalas.buscaSala(id);
//            String pacote = "P2P@" + salaaux.getDuracao() + "@" + salaaux.getNumJogadores();
//            LinkedList players = salaaux.getPlayers();
//            for (Iterator iterator = players.iterator(); iterator.hasNext();) {
//                Jogadores next = (Jogadores) iterator.next();
//                pacote += "@" + next.getNick() + "@" + next.getIp() + "@" + next.getPorta() + "";
//            }
//            System.out.println(pacote);
//            salaaux.setAtiva(false);
//            oo.writeObject(pacote);
//            oo.flush();
//            System.out.println("Sala ativa? "+ControleSalas.estaAtiva());

                if (aux[0].equals("R")) {
                    cheio = ControleSalas.estaCheia(id);
                    if (cheio) {
                        System.out.println("sala cheia");
                        Sala salaaux = ControleSalas.buscaSala(id);
                        String pacote = "Y@" + salaaux.getDuracao() + "@" + salaaux.getNumJogadores();
                        LinkedList players = salaaux.getPlayers();
                        for (Iterator iterator = players.iterator(); iterator.hasNext();) {
                            Jogadores next = (Jogadores) iterator.next();
                            pacote += "@" + next.getNick() + "@" + next.getIp() + "@" + next.getPorta() + "";
                        }
                        System.out.println(pacote);
                        salaaux.setAtiva(false);
                        oo.writeObject(pacote);
                        oo.flush();
                        System.out.println("Sala ativa? " + ControleSalas.estaAtiva());
                    }
                    System.out.println("Sala não cheia");
                    oo.writeObject("N");
                    oo.flush();
                }
            } while (!cheio);

//            System.out.println("Sala ativa? " + ControleSalas.estaAtiva());
//            boolean cheio = ControleSalas.estaCheia(id);
//            while (cheio == false) {
//                cheio = ControleSalas.estaCheia(id);
//                Thread.sleep(1000, 10000);
//            }
//
//            x = (String) oi.readObject();
//            aux = x.split("@");
//
//            if (aux[0].equals("PP") && cheio) {
//                Sala salaaux = ControleSalas.buscaSala(id);
//                String pacote = "P2P@" + salaaux.getDuracao() + "@" + salaaux.getNumJogadores();
//                LinkedList players = salaaux.getPlayers();
//                for (Iterator iterator = players.iterator(); iterator.hasNext();) {
//                    Jogadores next = (Jogadores) iterator.next();
//                    pacote += "@" + next.getNick() + "@" + next.getIp() + "@" + next.getPorta() + "";
//                }
//                System.out.println(pacote);
//                salaaux.setAtiva(false);
//                oo.writeObject(pacote);
//                oo.flush();
//                System.out.println("Sala ativa? " + ControleSalas.estaAtiva());
//            }
//
//            x = (String) oi.readObject();
//            System.out.println(x);
//            aux = x.split("@");
//            if (aux[0].equals("R")) {
//                oo.writeObject("S");
//                oo.flush();
//            }
        } catch (IOException ex) {
            System.out.println("Cliente não mandou nada");
        } catch (ClassNotFoundException ex) {
            System.out.println("Classe errada animal");
        }
//        catch (InterruptedException ex) {
//            Logger.getLogger(Atendente.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    private void criaSala(String maxPlayers, String duracao, String nick) {
        int numPlayers = Integer.parseInt(maxPlayers);

        Sala nova = new Sala(numPlayers, Integer.parseInt(duracao));
        Jogadores aux = new Jogadores(nick, Conexao.ip, Conexao.portaCli);
        aux.setSala(nova.getId());
        id = nova.getId();
        LinkedList aux2 = nova.getPlayers();
        aux2.add(aux);
        ControleSalas.salas.add(nova);
        System.out.println("Quantidade de salas - " + ControleSalas.salas.size());
    }

    private void entraSala(String nick) {
        Jogadores jogador = new Jogadores(nick, Conexao.ip, Conexao.portaCli);
        for (Iterator iterator = ControleSalas.salas.iterator(); iterator.hasNext();) {
            Sala next = (Sala) iterator.next();
            if (!ControleSalas.estaCheia(next.getId())) {
                LinkedList aux = next.getPlayers();
                jogador.setSala(next.getId());
                id = next.getId();
                aux.add(jogador);
                System.out.println("Id da sala " + next.getId());
                System.out.println("Maximo jogadores " + next.getNumJogadores());
                for (Iterator iterator1 = aux.iterator(); iterator1.hasNext();) {
                    Jogadores next1 = (Jogadores) iterator1.next();
                    System.out.println("Nick " + next1.getNick() + " ip " + next1.getIp() + " porta " + next1.getPorta());

                }
                break;
            }

        }
    }

}
