/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmedias;

/**
 *
 * @author profpa2
 */
public class Euclidiana {

    public double calcular(double[] vetor1, double[] vetor2) {
       double somatorio = 0;
        for (int i = 0; i < vetor1.length; i++) {
            somatorio +=Math.pow((vetor1[i]-vetor2[i]), 2);            
        }
        return Math.sqrt(somatorio);
    }
    
}
