import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LerCsv {

    public Dado[] realizarLeitura(String nomeArquivo) throws IOException {
        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeArquivo));
            int quantidadeLinhas = 0;
            int quantidadeAtributos;
            while (br.ready()) {
                br.readLine();
                quantidadeLinhas++;
            }
            br.close();
            Dado[] dados = new Dado[quantidadeLinhas];
            int indice = 0;
            BufferedReader br2 = new BufferedReader(new FileReader(nomeArquivo));
            while (br2.ready()) {
                String linha = br2.readLine();
                String vetString[] = linha.split(",");
                int tamanho = vetString.length;
                double[] atributos = new double[tamanho];
                for (int i = 0; i < tamanho; i++) {
                    atributos[i] = Double.parseDouble(vetString[i]);
                }
                dados[indice++] = new Dado(atributos);
            }

            return dados;

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        }
    }
}
