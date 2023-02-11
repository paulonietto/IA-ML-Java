/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsas;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author PROF804
 */
public class BSAS {

    private double[][] dados;
    private double limiar;
    private int maxGrupos;
    private Euclidiana distancia;

    public BSAS(double[][] dados, double limiar, int maxGrupos) {
        this.dados = dados;
        this.limiar = limiar;
        this.maxGrupos = maxGrupos;
        this.distancia = new Euclidiana();
    }

    public ArrayList<HashMap<Integer, double[]>> executar() {
        // QG <- 1

        //GQG <- {P1}
        HashMap<Integer, double[]> grupo1 = new HashMap<Integer, double[]>();
        grupo1.put(-1, dados[0].clone());// indice -1 é o centroide
        grupo1.put(0, dados[0]);
        ArrayList<HashMap<Integer, double[]>> agrupamento
                = new ArrayList<HashMap<Integer, double[]>>(maxGrupos);
        agrupamento.add(grupo1);

        //for i  2 to N
        for (int i = 1; i < dados.length; i++) {
            double menorDistancia = Double.MAX_VALUE;
            int grupoMaisProximo = -1;
            //encontrar Gk: d(Pi,Gk) = min1 ≤ j ≤ QG d(Pi,Gj)
            for (int j = 0; j < agrupamento.size(); j++) {
                HashMap<Integer, double[]> grupo = agrupamento.get(j);
                double distanciaGrupo
                        = distancia.calcular(dados[i], grupo.get(-1));
                if (distanciaGrupo < menorDistancia) {
                    menorDistancia = distanciaGrupo;
                    grupoMaisProximo = j;
                }
            }
            //if (d(Pi,Gk) > limiar AND q < QG) then
            if (menorDistancia > limiar && maxGrupos > agrupamento.size()) {
                HashMap<Integer, double[]> novoGrupo
                        = new HashMap<Integer, double[]>();
                novoGrupo.put(-1, dados[i].clone());
                novoGrupo.put(i, dados[i]);
                agrupamento.add(novoGrupo);
            } else {
                //Gk  Gk  {Pi}
                HashMap<Integer, double[]> tempGrupo
                        = agrupamento.get(grupoMaisProximo);
                tempGrupo.put(i, dados[i]);
                atualizaCentroide(tempGrupo);
            }
        }
   return agrupamento ;
}

private void atualizaCentroide(HashMap<Integer, double[]> grupo) {
        double[] centroide = new double[grupo.get(-1).length];
        grupo.remove(-1);
        for (double[] dado : grupo.values()) {
            for (int i = 0; i < dado.length; i++) {
                centroide[i] += dado[i];
            }
        }
        for (int i = 0; i < centroide.length; i++) {
            centroide[i] /= grupo.size();
        }
        grupo.put(-1, centroide);
    }
}
