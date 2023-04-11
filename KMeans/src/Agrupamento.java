public class Agrupamento {
    private Grupo[] grupos;
    private int indice;

    public Agrupamento(int quantidade) {
        this.grupos = new Grupo[quantidade];
        this.indice=0;
    }

    public Grupo[] getGrupos() {
        return grupos;
    }

    public boolean adicionarGrupo(Grupo grupo){
        grupos[indice++] = grupo;
        return true;
    }

    public boolean verificarNovosCentroides() {
        boolean novosCentroides = false;
        for (Grupo grupo:grupos) {
            boolean centroideAlterado = grupo.recalcularCentroide();
            if(centroideAlterado){
                novosCentroides = true;
            }
        }

        return novosCentroides;
    }

    public void reiniciarGrupos() {
        for (Grupo grupo:grupos) {
            grupo.reiniciar();
        }

    }
}
