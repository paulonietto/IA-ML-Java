/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aestrela;

import java.util.PriorityQueue;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.Comparator;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author gdmou
 */
public class AEstrela {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //h scores is the stright-line distance from the current city to Bucharest

        //Inicializa os nós com os valores H
        No Arad = new No("Arad", 366);
        No Zerind = new No("Zerind", 374);
        No Oradea = new No("Oradea", 380);
        No Sibiu = new No("Sibiu", 253);
        No Fagaras = new No("Fagaras", 178);
        No Rimnicu = new No("Rimnicu Vilcea", 193);
        No Pitesti = new No("Pitesti", 98);
        No Timisoara = new No("Timisoara", 329);
        No Lugoj = new No("Lugoj", 244);
        No Mehadia = new No("Mehadia", 241);
        No Drobeta = new No("Drobeta", 242);
        No Craiova = new No("Craiova", 160);
        No Bucharest = new No("Bucharest", 0);
        No Giurgiu = new No("Giurgiu", 77);

        //Inicia arestas como os custos G dos vizinhos
        Arad.setAdjacentes(new Aresta[]{
            new Aresta(Zerind, 75),
            new Aresta(Sibiu, 140),
            new Aresta(Timisoara, 118)
        });

        Zerind.setAdjacentes(new Aresta[]{
            new Aresta(Arad, 75),
            new Aresta(Oradea, 71)
        });

        Oradea.setAdjacentes(new Aresta[]{
            new Aresta(Zerind, 71),
            new Aresta(Sibiu, 151)
        });

        Sibiu.setAdjacentes(new Aresta[]{
            new Aresta(Arad, 140),
            new Aresta(Fagaras, 99),
            new Aresta(Oradea, 151),
            new Aresta(Rimnicu, 80)});

        Fagaras.setAdjacentes(new Aresta[]{
            new Aresta(Sibiu, 99),
            new Aresta(Bucharest, 211)
        });

        Rimnicu.setAdjacentes(new Aresta[]{
            new Aresta(Sibiu, 80),
            new Aresta(Pitesti, 97),
            new Aresta(Craiova, 146)
        });

        Pitesti.setAdjacentes(new Aresta[]{
            new Aresta(Rimnicu, 97),
            new Aresta(Bucharest, 101),
            new Aresta(Craiova, 138)
        });

        Timisoara.setAdjacentes(new Aresta[]{
            new Aresta(Arad, 118),
            new Aresta(Lugoj, 111)
        });

        Lugoj.setAdjacentes(new Aresta[]{
            new Aresta(Timisoara, 111),
            new Aresta(Mehadia, 70)
        });

        Mehadia.setAdjacentes(new Aresta[]{
            new Aresta(Lugoj, 70),
            new Aresta(Drobeta, 75)
        });

        Drobeta.setAdjacentes(new Aresta[]{
            new Aresta(Mehadia, 75),
            new Aresta(Craiova, 120)
        });

        Craiova.setAdjacentes(new Aresta[]{
            new Aresta(Drobeta, 120),
            new Aresta(Rimnicu, 146),
            new Aresta(Pitesti, 138)
        });

        Bucharest.setAdjacentes(new Aresta[]{
            new Aresta(Pitesti, 101),
            new Aresta(Giurgiu, 90),
            new Aresta(Fagaras, 211)
        });

        Giurgiu.setAdjacentes(new Aresta[]{
            new Aresta(Bucharest, 90)
        });

        buscaAEstrela(Arad, Bucharest);

        System.out.println("Camino: " + percorreCaminho(Bucharest));
    }

    public static String percorreCaminho(No alvo) {
        String caminho = " ]";
        for (No no = alvo; no != null; no = no.getAdjacente()) {
            caminho = ", " + no.getNome()+ caminho;
        }
        caminho = "[" + caminho.subSequence(1, caminho.length());
        return caminho;
    }

    public static void buscaAEstrela(No origem, No destino) {

        Set<No> nosExplorados = new HashSet<No>();

        PriorityQueue<No> fila = new PriorityQueue<No>(20,
                new Comparator<No>() {
            //override método compare para ordenar por prioridade
            @Override
            public int compare(No i, No j) {
                if (i.getValorFuncaoF() > j.getValorFuncaoF()) {
                    return 1;
                } else if (i.getValorFuncaoF() < j.getValorFuncaoF()) {
                    return -1;
                } else {
                    return 0;
                }
            }

        }
        );

        //custo do primeiro Nó
        origem.setValorFuncaoG(0);

        fila.add(origem);
        boolean encontrado = false;
        if (origem == destino) {
            encontrado = true;
        }

        while (fila.isEmpty() != true && encontrado == false) {

            //pega o Nó com menor valor de função f
            No noAtual = fila.poll();

            nosExplorados.add(noAtual);

            //verifica se encontrou o destino
            if (noAtual.getNome().equals(destino.getNome())) {
                encontrado = true;
            }

            //verifica cada filho do nó atual
            for (Aresta aresta : noAtual.getAdjacentes()) {
                No noFilho = aresta.getAlvo();
                double custo = aresta.getCusto();
                double funcaoGTemp = noAtual.getValorFuncaoG() + custo;
                double funcaoFTemp = funcaoGTemp + noFilho.getValorFuncaoH();


                /*caso o nó filho já tenha sido explorado e seu valor de função f é 
                maior que a função f do pai, então ele é desconsidera*/
                              
                if ((nosExplorados.contains(noFilho))
                        && (funcaoFTemp >= noFilho.getValorFuncaoF())) {
                    continue;
                } //senão se o nó filho não está na fila ou sua função f é a menor  
                else if ((!fila.contains(noFilho))
                        || (funcaoFTemp < noFilho.getValorFuncaoF())) {

                    noFilho.setAdjacente(noAtual);
                    noFilho.setValorFuncaoG(funcaoGTemp);
                    noFilho.setValorFuncaoF(funcaoFTemp);

                    if (fila.contains(noFilho)) {
                        fila.remove(noFilho);
                    }

                    fila.add(noFilho);

                }

            }

        }

    }
}
