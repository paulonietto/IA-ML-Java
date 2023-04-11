public class Distancia {
    public double euclidiana(double[] vetor1, double[] vetor2) {
        double somatorio = 0;
        for (int i = 0; i < vetor1.length; i++) {
            somatorio += Math.pow((vetor1[i] - vetor2[i]), 2);
        }
        return Math.sqrt(somatorio);
    }
}
