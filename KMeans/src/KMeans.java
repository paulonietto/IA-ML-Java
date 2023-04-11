import java.util.Random;

public class KMeans {
    private int k;
    private Dado dados[];
    private Agrupamento agrupamento;
    int maximoIteracoes;

    public KMeans(int k, Dado[] dados, int maximoIteracoes) throws Exception {
        if (k < dados.length) {
            this.k = k;
            this.dados = dados;
            agrupamento = new Agrupamento(k);
            this.maximoIteracoes = maximoIteracoes;
        } else {
            throw new Exception("Exception message");
        }
    }

    public KMeans(int k, Dado[] dados) throws Exception {
        if (k < dados.length) {
            this.k = k;
            this.dados = dados;
            agrupamento = new Agrupamento(k);
        } else {
            throw new Exception("Exception message");
        }
    }

    public Agrupamento executar() {
        selecionarCentroides();
        boolean novosCentroides = true;
        if (maximoIteracoes > 0) {
            while (novosCentroides && maximoIteracoes > 0) {
                agrupamento.reiniciarGrupos();
                associarGrupoMenorDistancia();
                novosCentroides = agrupamento.verificarNovosCentroides();
                maximoIteracoes--;
            }
        } else {
            while (novosCentroides) {
                agrupamento.reiniciarGrupos();
                associarGrupoMenorDistancia();
                novosCentroides = agrupamento.verificarNovosCentroides();
            }
        }
        return agrupamento;
    }

    private void associarGrupoMenorDistancia() {
        Distancia distancia = new Distancia();
        for (int i = 0; i < dados.length; i++) {
            Dado dado = dados[i];
            Grupo grupoMenorDistancia = null;
            double menorDistancia = Double.MAX_VALUE;
            for (Grupo grupo : agrupamento.getGrupos()) {
                double distanciaDado =
                        distancia.euclidiana(grupo.getCentroide(),
                                dado.getAtributos());
                if (distanciaDado < menorDistancia) {
                    menorDistancia = distanciaDado;
                    grupoMenorDistancia = grupo;
                }
            }
            grupoMenorDistancia.adicionarDado(dado);
        }
    }

    private void selecionarCentroides() {
        int[] indices = retornarIndicesUnicos();
        for (int i = 0; i < indices.length; i++) {
            int indice = indices[i];
            Grupo grupo = new Grupo(dados[indice].getAtributos().clone());
            agrupamento.adicionarGrupo(grupo);
        }

    }

    private int[] retornarIndicesUnicos() {
        Random random = new Random();
        int[] indices = new int[k];
        int indice;
        int contador = 0;
        boolean existe;
        while (contador < k) {
            existe = false;
            indice = random.nextInt(dados.length);
            for (int i = contador; i >= 0; i--) {
                if (indice == indices[i]) {
                    existe = true;
                    i = -1;
                }
            }
            if (!existe) {
                indices[contador++] = indice;
            }
        }
        return indices;
    }
}