/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kmedias;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author profpa2
 */
public class Kmedias {

    private int numeroGrupos;
    double[][] dados;

    public Kmedias(double[][] dados, int numeroGrupos) {
        this.numeroGrupos = numeroGrupos;
        this.dados = dados;
    }

    public ArrayList<HashMap<Integer, double[]>> executar() {
        Euclidiana distancia = new Euclidiana();
        ArrayList<HashMap<Integer, double[]>> agrupamento
                = new ArrayList<HashMap<Integer, double[]>>(numeroGrupos);
        for (int i = 0; i < numeroGrupos; i++) {
            HashMap<Integer, double[]> grupo = new HashMap<Integer, double[]>();
            grupo.put(-1, centroidesAleatorios(dados));
            agrupamento.add(grupo);
        }
        boolean novosCentroides = true;
        while (novosCentroides) {
            for (int i = 0; i < dados.length; i++) {
                double menorDistancia = Double.MAX_VALUE;
                int grupoMaisProximo = -1;
                for (int j = 0; j < agrupamento.size(); j++) {
                    HashMap<Integer, double[]> grupo = agrupamento.get(j);
                    double[] centroide = grupo.get(-1);
                    double distanciaElemento
                            = distancia.calcular(centroide, dados[i]);
                    if (menorDistancia > distanciaElemento) {
                        menorDistancia = distanciaElemento;
                        grupoMaisProximo = j;
                    }
                }
                HashMap<Integer, double[]> grupo = agrupamento.get(grupoMaisProximo);
                grupo.put(i, dados[i]);
            }
            novosCentroides = false;
            for (int i = 0; i < agrupamento.size(); i++) {
                HashMap<Integer, double[]> grupo = agrupamento.get(i);
                double novoCentroide[]
                        = recalculaCentroide(grupo);
                boolean iguais = verificarIgualdade(grupo.get(-1), novoCentroide);
                if (!iguais) {
                    novosCentroides = true;
                    grupo.replace(-1, novoCentroide);                    
                }
            }
            if (novosCentroides) {
                for (int i = 0; i < agrupamento.size(); i++) {
                    zerarGrupos(agrupamento.get(i));

                }
            }
        }
        return agrupamento;
    }

    private double[] centroidesAleatorios(double[][] dados) {
        Random random = new Random();
        int dado = random.nextInt(dados.length);
        return dados[dado].clone();
    }

    private double[] recalculaCentroide(HashMap<Integer, double[]> grupo) {
        double centroide[] = grupo.remove(-1);
        double novoCentroide[] = new double[centroide.length];
        for (double[] dado : grupo.values()) {
            for (int i = 0; i < dado.length; i++) {
                novoCentroide[i] += dado[i];
            }
        }
        for (int i = 0; i < novoCentroide.length; i++) {
            /*double media = Math.round((novoCentroide[i]/grupo.size()) * 10000.0)/10000.0;
            novoCentroide[i] = media;*/
            novoCentroide[i]=novoCentroide[i]/grupo.size();
        }
        grupo.put(-1, centroide);
        return novoCentroide;
    }

    private boolean verificarIgualdade(double[] centroide, double[] novoCentroide) {
        for (int i = 0; i < novoCentroide.length; i++) {
            if (centroide[i] != novoCentroide[i]) {
                return false;
            }
        }
        return true;
    }

    private void zerarGrupos(HashMap<Integer, double[]> grupo) {
        double[] centroide = grupo.remove(-1);
        grupo.clear();
        grupo.put(-1, centroide);
    }
}
