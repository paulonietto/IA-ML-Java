/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knn4;

/**
 *
 * @author pniet
 */
public class Dado {
    private double[] valores;
    private String classe;
    private double distancia;

    public Dado(double[] valores, String classe) {
        this.valores = valores;
        this.classe = classe;
    }

    public double[] getValores() {
        return valores;
    }

    public String getClasse() {
        return classe;
    }

    public double getDistancia() {
        return distancia;
    }

    public void setDistancia(double distancia) {
        this.distancia = distancia;
    }    
}
