/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn4;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author pniet
 */
public class AppKNN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        Scanner entrada = new Scanner(System.in);
        System.out.println("Digite o valor de k");
        int k = entrada.nextInt();

        Dado[] conjunto = lerArquivo("C:\\Users\\pniet\\Desktop\\2020 - 1\\iris.txt");

        KNN algoritmo = new KNN(k, conjunto);

        System.out.println("Digite o novo dado");
        double[] dado = {7.6, 3.0, 6.6, 2.1};
        
       
        System.out.println(algoritmo.executar(dado));
    }

    public static Dado[] lerArquivo(String nomeArquivo) throws Exception {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            int quantidadeLinhas = 0;
            int quantidadeAtributos;
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
                quantidadeLinhas++;
            }

            br.close();
            String atributos[] = linha.split(",");
            quantidadeAtributos = atributos.length;

            BufferedReader br2 = new BufferedReader(new FileReader(nomeArquivo));
            Dado[] conjunto = new Dado[quantidadeLinhas];
            int indice = 0;
            while (br2.ready()) {
                linha = br2.readLine();
                String vetString[] = linha.split(",");
                double[] valores = new double[quantidadeAtributos - 1];
                for (int i = 0; i < quantidadeAtributos - 1; i++) {
                    valores[i] = Double.parseDouble(vetString[i]);
                }
                conjunto[indice++] = new Dado(valores, vetString[quantidadeAtributos - 1]);
            }
            br2.close();
            return conjunto;

        } catch (IOException ioe) {
            ioe.printStackTrace();
            throw new Exception(ioe.getMessage());
        }
    }
}
