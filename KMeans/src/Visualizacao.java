import org.jfree.chart.ChartFactory;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.WindowConstants;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class Visualizacao extends JFrame {
    private XYSeriesCollection frame;

    public Visualizacao(String title) {
        super(title);
        frame = new XYSeriesCollection();
    }

    private void gerarTela() {
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void gerarGrafico(Agrupamento agrupamento) {
        int i = 0;
        for (Grupo grupo : agrupamento.getGrupos()) {
            inserirDados(grupo, "Grupo " + (++i));
        }


              // Create chart
        JFreeChart chart = ChartFactory.createScatterPlot(
                "Visualização do Agrupamento",
                "Eixo-X", "Eixo-Y", frame);

        //Changes background color
        XYPlot plot = (XYPlot) chart.getPlot();
        //plot.setBackgroundPaint(new Color(255, 228, 196));
        plot.setBackgroundPaint(new Color(240, 240, 240));
        // Create Panel
        ChartPanel painel = new ChartPanel(chart);
        setContentPane(painel);
        gerarTela();
    }

    public void inserirDados(Grupo grupo, String nome) {
        XYSeries series = new XYSeries(nome);
        for (Dado dado : grupo.getDados()) {
            double[] atributos = dado.getAtributos();
            series.add(atributos[0], atributos[1]);
        }
        frame.addSeries(series);
    }
}



