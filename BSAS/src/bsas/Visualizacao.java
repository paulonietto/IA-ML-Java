/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bsas;

import javax.swing.JFrame;
import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author pniet
 */
public class Visualizacao extends JFrame {
private XYSeriesCollection conjuntoDados;
    public Visualizacao(String title) {
        super(title);
        conjuntoDados = new XYSeriesCollection();

        // Create dataset
        
    }
    public void gerarGrafico(){
    

        // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Visualização do Agrupamento",
                "Eixo-X", "Eixo-Y",  conjuntoDados);

        //Changes background color
        XYPlot plot = (XYPlot) chart.getPlot();
        //plot.setBackgroundPaint(new Color(255, 228, 196));
plot.setBackgroundPaint(new Color(240,240,240));
        // Create Panel
        ChartPanel painel = new ChartPanel(chart);
        setContentPane(painel);
    
    
    }

    public void inserirDados(double[][] dados, int x, int y, String nome) {
        

        
        XYSeries series = new XYSeries(nome);
        for (int i = 0; i < dados.length; i++) {
            series.add(dados[i][x],dados[i][y]);
            
        }
        
        conjuntoDados.addSeries(series);

    }
}
