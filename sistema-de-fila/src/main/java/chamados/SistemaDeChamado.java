package chamados;

public class SistemaDeChamado {


    private static class No {
        Chamado chamado;
        No proximo;

        No(Chamado chamado) {
            this.chamado = chamado;
            this.proximo = null;
        }
    }


    private No cabeca;
    private int tamanho;

    public SistemaDeChamado() {
        cabeca  = null;
        tamanho = 0;
    }


    public void enfileirar(Chamado chamado) {
        No novo = new No(chamado);

        if (cabeca == null || deveVirAntes(chamado, cabeca.chamado)) {
            novo.proximo = cabeca;
            cabeca = novo;
        } else {
            No atual = cabeca;
            while (atual.proximo != null && !deveVirAntes(chamado, atual.proximo.chamado)) {
                atual = atual.proximo;
            }
            novo.proximo  = atual.proximo;
            atual.proximo = novo;
        }

        tamanho++;
    }

    public Chamado proximo() {
        verificarNaoVazia();
        Chamado atendido = cabeca.chamado;
        cabeca  = cabeca.proximo;
        tamanho--;
        return atendido;
    }


    public Chamado consultarProximo() {
        verificarNaoVazia();
        return cabeca.chamado;
    }

    public int tamanho() {
        return tamanho;
    }

    public void exibirChamadosPendentes() {
        if (tamanho == 0) {
            System.out.println("  (nenhum chamado pendente)");
            return;
        }
        No atual = cabeca;
        int posicao = 1;
        while (atual != null) {
            System.out.printf("  %d. %s%n", posicao++, atual.chamado);
            atual = atual.proximo;
        }
    }

    private boolean deveVirAntes(Chamado a, Chamado b) {
        if (a.getPrioridade() != b.getPrioridade()) {
            return a.getPrioridade() < b.getPrioridade();
        }
        return a.getTimestamp() < b.getTimestamp();
    }

    private void verificarNaoVazia() {
        if (tamanho == 0) {
            throw new IllegalStateException("Nao ha chamados na fila.");
        }
    }
}
