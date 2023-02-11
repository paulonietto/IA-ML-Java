/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fuzzy;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author gdmou
 */
public class Fuzzy {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] valores = {{0, 0}, {0, 2}, {0, 4}, {1, 1}, {1, 2}, {1, 3}, {2, 2}, {3, 2}, {4, 2}, {5, 1}, {5, 2}, {5, 3}, {6, 0}, {6, 2}, {6, 4}};
        fuzzyCMeans(valores, 1.25, 0.01);
    }

    private static void fuzzyCMeans(double valores[][], double m, double threshold) {

        double[][] centroide = {{0.1, 2}, {4.65, 2}};
        // linha System.out.println(centroid.length);
        //coluna System.out.println(centroid[0].length);
        double diferencaMaxima;
        double matrizDistancia[][] = geraMatrizDistancia(valores, centroide);
        double matrizPertinencia[][] = geraMatrizPertinencia(matrizDistancia, m);
        do {

            double[][] novosCentroides = calculaNovosCentroides(matrizPertinencia, valores, m);
            double matrizDistanciaNova[][] = geraMatrizDistancia(valores, novosCentroides);
            double matrizPertinenciaNova[][] = geraMatrizPertinencia(matrizDistanciaNova, m);

            //imprimir2(matrizPertinencia);
            // imprimir3(novosCentroides);
            // imprimir3(centroid);
            // imprimir2(matrizPertinenciaNova);
            diferencaMaxima = calculaDiferenca(matrizPertinencia, matrizPertinenciaNova);
            matrizDistancia = matrizDistanciaNova;
            matrizPertinencia = matrizPertinenciaNova;
            centroide = novosCentroides;
            System.out.println(diferencaMaxima);
            imprimir2(matrizPertinencia);

        } while (diferencaMaxima > threshold);
    }

    private static double[][] calculaNovosCentroides(double[][] matrizPertinencia, double[][] valores, double m) {
        double centroid[][] = new double[matrizPertinencia[0].length][valores[0].length];
        for (int i = 0; i < matrizPertinencia[0].length; i++) {
            for (int j = 0; j < matrizPertinencia[0].length; j++) {
                double soma = 0;
                double somatorioPertinencia = 0;
                for (int k = 0; k < matrizPertinencia.length; k++) {
                    soma += Math.pow(matrizPertinencia[k][i], m) * valores[k][j];
                    somatorioPertinencia += Math.pow(matrizPertinencia[k][i], m);
                }
                centroid[i][j] = soma / somatorioPertinencia;
            }
        }
        return centroid;
    }

    private static double[][] geraMatrizPertinencia(double[][] matrizDistancia, double m) {
        //  imprimir(matrizDistancia);
        double matrizPertinencia[][] = new double[matrizDistancia.length][matrizDistancia[0].length];
        for (int i = 0; i < matrizDistancia.length; i++) {
            for (int j = 0; j < matrizDistancia[0].length; j++) {
                double soma = 0;
                for (int k = 0; k < matrizDistancia[0].length; k++) {
                    soma += Math.pow((matrizDistancia[i][j] / matrizDistancia[i][k]), 1 / (m - 1));
                }
                matrizPertinencia[i][j] = 1 / soma;
            }
        }
        return matrizPertinencia;
    }

    private static double[][] geraMatrizDistancia(double[][] valores, double[][] centroid) {
        double matrizDistancia[][] = new double[valores.length][centroid.length];
        zeraMatriz(matrizDistancia);
        for (int i = 0; i < matrizDistancia[0].length; i++) {
            for (int j = 0; j < matrizDistancia.length; j++) {
                matrizDistancia[j][i] = distanciaEuclidiana(valores[j], centroid[i]);
            }
        }
        return matrizDistancia;
    }

    private static void zeraMatriz(double[][] matrizDistancia) {
        for (int i = 0; i < matrizDistancia.length; i++) {
            for (int j = 0; j < matrizDistancia[0].length; j++) {
                matrizDistancia[i][j] = Double.NaN;
            }
        }
    }

    private static double distanciaEuclidiana(double[] vetor, double[] vetor2) {
        double soma = 0;
        for (int i = 0; i < vetor.length; i++) {
            soma += Math.pow((vetor[i] - vetor2[i]), 2);
        }
        return Math.sqrt(soma);
    }

    private static void imprimir(double[][] matrizDistancia) {
        for (int i = 0; i < matrizDistancia[0].length; i++) {
            System.out.println("Centroid " + (i + 1));
            for (int j = 0; j < matrizDistancia.length; j++) {
                System.out.println(matrizDistancia[j][i]);
            }
        }
    }

    private static void imprimir2(double[][] matrizPertinencia) {
        System.out.println("\n\n\n");
        for (int i = 0; i < matrizPertinencia[0].length; i++) {
            System.out.println("Centroid " + (i + 1));
            for (int j = 0; j < matrizPertinencia.length; j++) {
                System.out.println(matrizPertinencia[j][i]);
            }
        }
        System.out.println("\n\n\n");
    }

    private static double calculaDiferenca(double[][] matrizPertinencia, double[][] matrizPertinenciaNova) {
        double maior = Math.abs(matrizPertinencia[0][0] - matrizPertinenciaNova[0][0]);
        for (int i = 0; i < matrizPertinencia.length; i++) {
            double temp = Math.abs(matrizPertinencia[i][0] - matrizPertinenciaNova[i][0]);
            if (maior < temp) {
                maior = temp;
            }
        }

        return maior;
    }

    private static void imprimir3(double[][] novosCentroides) {
        for (int i = 0; i < novosCentroides.length; i++) {
            System.out.println("Centroid " + (i + 1));
            for (int j = 0; j < novosCentroides.length; j++) {
                System.out.println(novosCentroides[i][j]);

            }

        }
    }
}
