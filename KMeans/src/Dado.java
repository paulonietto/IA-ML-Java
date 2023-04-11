import java.util.Arrays;

public class Dado {
    private double [] atributos;
    public Dado(double [] atributos){
        this.atributos = atributos;
    }

    public double[] getAtributos() {
        return atributos;
    }


    @Override
    public String toString() {
        return "Dado{" +
                "vetor=" + Arrays.toString(atributos) +
                '}';
    }
}
