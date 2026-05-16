package schergds.com.buscafretes.entity.enums;

public enum StatusFrete {
    ABERTO("Aberto"),
    EM_NEGOCIACAO("Em Negociação"),
    FINALIZADO("Finalizado");

    private final String descricao;

    StatusFrete(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
