package schergds.com.buscafretes.entity.enums;

public enum TipoCaminhao {
    TRUCK("Truck"),
    TOCO("Toco"),
    BITREM("Bitrem"),
    RODOTREM("Rodotrem"),
    VAN("Van"),
    CARRETA("Carreta");

    private final String descricao;

    TipoCaminhao(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}
