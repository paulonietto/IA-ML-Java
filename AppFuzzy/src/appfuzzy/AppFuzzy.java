/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package appfuzzy;

import java.util.Scanner;
import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;

/**
 *
 * @author pniet
 */
public class AppFuzzy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o valor da temperatura");
        double temperatura = entrada.nextDouble();
        System.out.println("Digite o valor da umidade");
        double umidade = entrada.nextDouble();
        String caminho = "C:\\Users\\pniet\\Desktop\\2020 - 1\\Inteligência Artificial\\Aula 5\\irrigar.fcl"; 
        
        FIS fuzzy = FIS.load(caminho);
        fuzzy.setVariable("temperatura", temperatura);
        fuzzy.setVariable("umidade", umidade);
        fuzzy.evaluate();
        Variable irrigacao = fuzzy.getVariable("irrigacao");
        
        JFuzzyChart.get().chart(irrigacao,irrigacao.getDefuzzifier(),true);
        JFuzzyChart.get().chart(fuzzy);
                
    }
    
}
