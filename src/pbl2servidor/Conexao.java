/*
 * Comtém as Classes responsáveis pela criação e funcionamento do servidor
 */
package pbl2servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/** Classe responsável pela configuração e inicialização do servidor
 *
 * @author Lucas Cardoso
 */
public class Conexao extends Thread {

    private ServerSocket server;
    private int porta;
    public static String ip;
    public static String portaCli;
    public static int count;
    
    /**
     * Construtor da classe
     * @param porta Parâmetro que recebe a porta de funcionamento do servidor
     */
    public Conexao(int porta) {
        this.porta = porta;
        Conexao.ip = "";
        Conexao.portaCli = "";
        Conexao.count = 0;
    }
    
    /**
     * Método responsável por executar a thread do servidor
     */
    public void run() {

        try {
            server = new ServerSocket(porta);
            System.out.println("criou a thread");
            while (true) {
                Socket sokt = server.accept();
                count++;
                ip = sokt.getInetAddress().getHostAddress();
                portaCli =  sokt.getPort()+"";
                new Atendente(sokt, this).start();
                System.out.println(count);
                System.out.println(ip);

            }
        } catch (IOException ex) {
            System.out.println("Deu rum em criar o socket");
        }
    }

}
