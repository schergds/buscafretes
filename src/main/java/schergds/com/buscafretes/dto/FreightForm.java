package schergds.com.buscafretes.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import schergds.com.buscafretes.entity.Freight;
import schergds.com.buscafretes.entity.enums.TipoCaminhao;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FreightForm {

    private Long id;

    @NotBlank(message = "Título é obrigatório")
    private String titulo;

    @NotBlank(message = "Descrição é obrigatória")
    private String descricao;

    @NotBlank(message = "Origem é obrigatória")
    private String origem;

    @NotBlank(message = "Destino é obrigatório")
    private String destino;

    @NotNull(message = "Valor é obrigatório")
    private BigDecimal valor;

    @NotNull(message = "Tipo de caminhão é obrigatório")
    private TipoCaminhao tipoCaminhao;

    private String peso;

    @NotBlank(message = "Telefone é obrigatório")
    private String telefoneContato;

    @NotBlank(message = "WhatsApp é obrigatório")
    private String whatsapp;

    @NotNull(message = "Data de coleta é obrigatória")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataColeta;

    public FreightForm() {}

    public FreightForm(Freight freight) {
        this.id = freight.getId();
        this.titulo = freight.getTitulo();
        this.descricao = freight.getDescricao();
        this.origem = freight.getOrigem();
        this.destino = freight.getDestino();
        this.valor = freight.getValor();
        this.tipoCaminhao = freight.getTipoCaminhao();
        this.peso = freight.getPeso();
        this.telefoneContato = freight.getTelefoneContato();
        this.whatsapp = freight.getWhatsapp();
        this.dataColeta = freight.getDataColeta();
    }

    public Freight toEntity(Freight freight) {
        if (freight == null) {
            freight = new Freight();
        }
        freight.setTitulo(this.titulo);
        freight.setDescricao(this.descricao);
        freight.setOrigem(this.origem);
        freight.setDestino(this.destino);
        freight.setValor(this.valor);
        freight.setTipoCaminhao(this.tipoCaminhao);
        freight.setPeso(this.peso);
        freight.setTelefoneContato(this.telefoneContato);
        freight.setWhatsapp(this.whatsapp);
        freight.setDataColeta(this.dataColeta);
        return freight;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public String getOrigem() { return origem; }
    public void setOrigem(String origem) { this.origem = origem; }
    public String getDestino() { return destino; }
    public void setDestino(String destino) { this.destino = destino; }
    public BigDecimal getValor() { return valor; }
    public void setValor(BigDecimal valor) { this.valor = valor; }
    public TipoCaminhao getTipoCaminhao() { return tipoCaminhao; }
    public void setTipoCaminhao(TipoCaminhao tipoCaminhao) { this.tipoCaminhao = tipoCaminhao; }
    public String getPeso() { return peso; }
    public void setPeso(String peso) { this.peso = peso; }
    public String getTelefoneContato() { return telefoneContato; }
    public void setTelefoneContato(String telefoneContato) { this.telefoneContato = telefoneContato; }
    public String getWhatsapp() { return whatsapp; }
    public void setWhatsapp(String whatsapp) { this.whatsapp = whatsapp; }
    public LocalDate getDataColeta() { return dataColeta; }
    public void setDataColeta(LocalDate dataColeta) { this.dataColeta = dataColeta; }
}
