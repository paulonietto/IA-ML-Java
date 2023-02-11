/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn4;

import java.util.Hashtable;
import java.util.Map;

/**
 *
 * @author pniet
 */
public class KNN {

    private int k;
    private Dado[] conjunto;

    public KNN(int k, Dado[] conjunto) {
        this.k = k;
        this.conjunto = conjunto;
    }

    public String executar(double[] novoDado) {
        for (int i = 0; i < conjunto.length; i++) {
            double distancia
                    = distanciaEuclidiana(novoDado, conjunto[i].getValores());
            conjunto[i].setDistancia(distancia);
        }
        /*for (Dado dado : conjunto) {
            dado.setDistancia(distanciaEuclidiana(novoDado, dado.getValores()));
        }*/
        Dado[] maisProximos = encontrarMaisProximos();
        return classeMaisRecorrente(maisProximos);
    }

    private String classeMaisRecorrente(Dado[] maisProximos) {
        Hashtable<String, Integer> pares = new Hashtable<String, Integer>();
        for (Dado dado : maisProximos) {
            if (!pares.containsKey(dado.getClasse())) {
                pares.put(dado.getClasse(), 1);
            } else {
                int valor = pares.get(dado.getClasse());
                pares.replace(dado.getClasse(), ++valor);
            }
        }
        int maisRecorrente = 0;
        String classe = "";
        for (Map.Entry<String, Integer> par : pares.entrySet()) {
            if (par.getValue() > maisRecorrente) {
                maisRecorrente = par.getValue();
                classe = par.getKey();
            }
        }
        return classe;
    }

    private Dado[] encontrarMaisProximos() {
        mergeSort(0, conjunto.length - 1);
        Dado[] maisProximos = new Dado[k];
        for (int i = 0; i < k; i++) {
            maisProximos[i] = conjunto[i];
            System.out.println(conjunto[i].getDistancia());
        }
        return maisProximos;
    }

    public double distanciaEuclidiana(double[] dado1, double[] dado2) {
        double somatorio = 0;
        for (int i = 0; i < dado1.length; i++) {
            somatorio += Math.pow((dado1[i] - dado2[i]), 2);
        }
        return Math.sqrt(somatorio);
    }

    private void mergeSort(int inicio, int fim) {
        if (inicio < fim) {
            int meio = (inicio + fim) / 2;
            mergeSort(inicio, meio);
            mergeSort(meio + 1, fim);
            intercala(inicio, meio, fim);
        }
    }

    private void intercala(int inicio, int meio, int fim) {
        Dado[] auxiliar = new Dado[conjunto.length];
        for (int i = inicio; i <= meio; i++) {
            auxiliar[i] = conjunto[i];
        }
        for (int j = meio + 1; j <= fim; j++) {
            auxiliar[fim + meio + 1 - j] = conjunto[j];
        }
        int i = inicio;
        int j = fim;
        for (int k = inicio; k <= fim; k++) {
            if (auxiliar[i].getDistancia() <= auxiliar[j].getDistancia()) {
                conjunto[k] = auxiliar[i++];
            } else {
                conjunto[k] = auxiliar[j--];
            }
        }
    }

}
