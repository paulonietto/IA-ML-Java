/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aestrela;

/**
 * @author gdmou
 */
public class Aresta {
    private final double custo;
    private final No alvo;

    public Aresta(No alvo, double custo) {
        this.alvo = alvo;
        this.custo = custo;
    }

    public double getCusto() {
        return custo;
    }

    public No getAlvo() {
        return alvo;
    }

}
