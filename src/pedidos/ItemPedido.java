package pedidos;

import java.io.Serializable;
import java.math.BigDecimal;

public class ItemPedido implements Serializable {
    private final String produto;
    private int quantidade;
    private final BigDecimal precoUnitario;

    public ItemPedido(String produto, int quantidade, BigDecimal precoUnitario) {
        this.produto = produto;
        this.quantidade = quantidade;
        this.precoUnitario = precoUnitario;
    }

    public ItemPedido(ItemPedido origem) {
        this(origem.produto, origem.quantidade, origem.precoUnitario);
    }

    public String getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public BigDecimal getPrecoUnitario() { return precoUnitario; }

    public BigDecimal subtotal() {
        return precoUnitario.multiply(BigDecimal.valueOf(quantidade));
    }
}
