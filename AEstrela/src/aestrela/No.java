/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aestrela;

/**
 *
 * @author gdmou
 */
public class No {

    private final String nome;
    private double valorFuncaoG;
    private final double valorFuncaoH;
    private double valorFuncaoF = 0;
    private Aresta[] adjacentes;
    private No adjacente;

    public No(String nome, double valorFuncaoH) {
        this.nome = nome;
        this.valorFuncaoH = valorFuncaoH;
    }

    public double getValorFuncaoH() {
        return valorFuncaoH;
    }

    public double getValorFuncaoG() {
        return valorFuncaoG;
    }

    public void setValorFuncaoG(double valorFuncaoG) {
        this.valorFuncaoG = valorFuncaoG;
    }

    public double getValorFuncaoF() {
        return valorFuncaoF;
    }

    public void setValorFuncaoF(double valorFuncaoF) {
        this.valorFuncaoF = valorFuncaoF;
    }

    public void setAdjacentes(Aresta[] adjacentes) {
        this.adjacentes = adjacentes;
    }

    public void setAdjacente(No adjacente) {
        this.adjacente = adjacente;
    }

    public String getNome() {
        return nome;
    }

    public Aresta[] getAdjacentes() {
        return adjacentes;
    }

    public No getAdjacente() {
        return adjacente;
    }

}
