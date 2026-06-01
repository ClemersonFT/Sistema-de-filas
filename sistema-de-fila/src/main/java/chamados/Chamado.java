package chamados;

public class Chamado {

    private int prioridade;
    private String descricao;
    private final long timestamp;

    public Chamado(int prioridade, String descricao) {
        this.prioridade = prioridade;
        this.descricao  = descricao;
        this.timestamp  = System.nanoTime();
    }


    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("[Prioridade %d] %s", prioridade, descricao);
    }
}
