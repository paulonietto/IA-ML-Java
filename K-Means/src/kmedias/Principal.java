/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmedias;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author profpa2
 */
public class Principal {

    public static void main(String[] args) {
       String nomeArquivo = "D:\\OneDrive - Laureate Education - LATAMBR\\Anhembi\\2021 - 1\\Inteligência Artificial\\r152.txt";
        double dados[][] = lerArquivo(nomeArquivo);
        //imprimir(dados);
        Kmedias kmeans = new Kmedias(dados, 15);
        ArrayList<HashMap<Integer, double[]>> agrupamento = kmeans.executar();
        
        gerarVisualização(agrupamento);

    }

    public static double[][] lerArquivo(String nomeArquivo) {

        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            int quantidadeLinhas = 0;
            int quantidadeAtributos;
            String linha = "";
            while (br.ready()) {
                linha = br.readLine();
                quantidadeLinhas++;
            }

            //br.close();
            String atributos[] = linha.split(",");
            quantidadeAtributos = atributos.length;
            

            double dados[][] = new double[quantidadeLinhas][quantidadeAtributos];
            br.close();
            BufferedReader br2 = new BufferedReader(new FileReader(nomeArquivo));
            int i = 0;
            while (br2.ready()) {
                linha = br2.readLine();
                String vetString[] = linha.split(",");
                for (int j = 0; j < quantidadeAtributos; j++) {
                    dados[i][j] = Double.parseDouble(vetString[j]);

                }
                i++;
            }
            br2.close();
            return dados;

        } catch (IOException ioe) {
            ioe.printStackTrace();
            double dados[][] = new double[2][2];
            return dados;
        }

    }

    private static void imprimir(double[][] dados) {
        for (int i = 0; i < dados.length; i++) {
            for (int j = 0; j < dados[0].length; j++) {
                System.out.print(dados[i][j] + "\t");

            }
            System.out.println("");

        }
    }

    private static void gerarVisualização(ArrayList<HashMap<Integer, double[]>> agrupamento) {
        Visualizacao tela = new Visualizacao("Inteligência Artificial");
        for (int i = 0; i < agrupamento.size(); i++) {
            HashMap<Integer, double[]> grupo = agrupamento.get(i);
            double dados[][] = new double[grupo.size()][grupo.get(-1).length];
            int x = 0;
            for (double[] dado : grupo.values()) {
                dados[x] = dado;
                x++;
            }
            tela.inserirDados(dados, 0, 1, "Grupo " + (i+1));

        }
        tela.gerarGrafico();
        tela.setSize(800, 600);
        tela.setLocationRelativeTo(null);
        tela.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        tela.setVisible(true);
    }
}

