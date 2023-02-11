/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsas;

/**
 *
 * @author PROF804
 */
public class Euclidiana {

    public double calcular(double[] vetor1, double[] vetor2) {
        double valor = 0;
        for (int i = 0; i < vetor2.length; i++) {
            valor += Math.pow((vetor1[i] - vetor2[i]), 2);
        }
        return Math.sqrt(valor);
    }
}
