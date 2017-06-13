/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbl2servidor;

/** Classe principal do servidor. Inicializa o servidor.
 *
 * @author Lucas Cardoso e Emanuel Santana
 */
public class PBL2Servidor {

    private static Conexao cone;
    
    public static void main(String[] args) {
        cone = new Conexao(50000);
        cone.start();
        System.out.println("nasceu");
    }
    
}
