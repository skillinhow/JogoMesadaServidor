/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pbl2servidor;

/**
 *
 * @author thelu
 */
public class PBL2Servidor {

    private static testeCLI.Conexao cone;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        cone = new testeCLI.Conexao(50000);
        cone.start();
        System.out.println("nasceu");
    }
    
}
