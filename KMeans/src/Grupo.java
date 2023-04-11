import java.util.ArrayList;
import java.util.List;

public class Grupo {
    private List<Dado> dados;
    private double[] centroide;

    public Grupo(double[] centroide) {
        this.centroide = centroide;
        this.dados = new ArrayList<Dado>();
    }

    public List<Dado> getDados() {
        return dados;
    }

    public double[] getCentroide() {
        return centroide;
    }

    public boolean verificarAlteracaoCentroide(double[] novoCentroide) {
        for (int i = 0; i < centroide.length; i++) {
            if (centroide[i] != novoCentroide[i]) {
                alterarCentroide(novoCentroide);
                return true;
            }
        }
        return false;
    }

    public void alterarCentroide(double[] novoCentroide) {
        centroide = novoCentroide;
    }

    public void adicionarDado(Dado dado) {
        dados.add(dado);
    }

    public boolean recalcularCentroide() {
        double[] novoCentroide = new double[centroide.length];
        for (Dado dado : dados) {
            double[] atributos = dado.getAtributos();
            for (int i = 0; i < centroide.length; i++) {
                novoCentroide[i] += atributos[i];
            }
        }
        for (int i = 0; i < novoCentroide.length; i++) {
            novoCentroide[i] /= dados.size();
        }
        return verificarAlteracaoCentroide(novoCentroide);
    }

    public void reiniciar() {
        dados.clear();
    }
}
