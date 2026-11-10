package pedidos;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Prototype<Pedido>, Serializable {
    private static long contador = 0;

    private long id;
    private String cliente;
    private String enderecoEntrega;
    private List<ItemPedido> itens = new ArrayList<>();
    private LocalDate criadoEm;

    public Pedido(String cliente, String enderecoEntrega) {
        this.cliente = cliente;
        this.enderecoEntrega = enderecoEntrega;
        renovarIdentidade();
    }

    private static synchronized long proximoId() { return ++contador; }

    private void renovarIdentidade() {
        this.id = proximoId();
        this.criadoEm = LocalDate.now();
    }

    public long getId() { return id; }
    public String getCliente() { return cliente; }
    public void setCliente(String cliente) { this.cliente = cliente; }
    public String getEnderecoEntrega() { return enderecoEntrega; }
    public void setEnderecoEntrega(String e) { this.enderecoEntrega = e; }
    public List<ItemPedido> getItens() { return itens; }
    public LocalDate getCriadoEm() { return criadoEm; }

    public void adicionarItem(ItemPedido item) { itens.add(item); }

    public BigDecimal valorTotal() {
        return itens.stream()
            .map(ItemPedido::subtotal)
            .reduce(BigDecimal.ZERO, BigDecimal::add)
            .setScale(2, RoundingMode.HALF_UP);
    }

    @Override
    public Pedido clonar() {
        Pedido copia = new Pedido(cliente, enderecoEntrega);
        for (ItemPedido item : itens) copia.itens.add(new ItemPedido(item));
        return copia;
    }

    public Pedido clonarPorSerializacao() {
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            try (ObjectOutputStream out = new ObjectOutputStream(bytes)) {
                out.writeObject(this);
            }
            try (ObjectInputStream in = new ObjectInputStream(new ByteArrayInputStream(bytes.toByteArray()))) {
                Pedido copia = (Pedido) in.readObject();
                copia.renovarIdentidade();
                return copia;
            }
        } catch (IOException | ClassNotFoundException e) {
            throw new IllegalStateException("falha ao clonar por serializacao", e);
        }
    }
}
