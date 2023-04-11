import java.io.IOException;


public class Principal {
    public static void main(String[] args) throws IOException {
        try {
            String nomeArquivo = ".\\r152.txt";
            LerCsv ler_csv = new LerCsv();
            Dado[] dados = ler_csv.realizarLeitura(nomeArquivo);
            //imprimir(dados);
            KMeans kmeans = new KMeans(15, dados, 100);
            Agrupamento agrupamento = kmeans.executar();
            Visualizacao visualizar = new Visualizacao("K-Means");
            visualizar.gerarGrafico(agrupamento);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private static void imprimir(Dado[] dados) {
        for (int i = 0; i < dados.length; i++) {
            System.out.println(dados[i].toString());
        }
    }
}