package pedidos;

import java.math.BigDecimal;
import java.util.List;

public class PedidosApp {
    public static void main(String[] args) {
        Pedido modelo = new Pedido("(modelo)", "-");
        modelo.adicionarItem(new ItemPedido("Café 500g", 10, new BigDecimal("18.90")));
        modelo.adicionarItem(new ItemPedido("Açúcar 1kg", 20, new BigDecimal("4.50")));
        modelo.adicionarItem(new ItemPedido("Filtro de papel", 5, new BigDecimal("7.20")));

        CatalogoDePedidos catalogo = new CatalogoDePedidos();
        catalogo.registrar("reposicao-mensal", modelo);

        Pedido a = catalogo.criar("reposicao-mensal");
        a.setCliente("Mercado Central");
        a.setEnderecoEntrega("Rua das Flores, 120");

        Pedido b = catalogo.criar("reposicao-mensal");
        b.setCliente("Padaria Boa Massa");
        b.setEnderecoEntrega("Av. Paraná, 800");
        b.getItens().get(0).setQuantidade(20);

        Pedido c = catalogo.criar("reposicao-mensal");
        c.setCliente("Restaurante do Porto");
        c.setEnderecoEntrega("Rua do Cais, 45");
        c.adicionarItem(new ItemPedido("Adoçante 100ml", 12, new BigDecimal("6.00")));

        for (Pedido p : List.of(modelo, a, b, c)) imprimir(p);

        ca("CA-01", "ids diferentes",
            modelo.getId() != a.getId() && modelo.getId() != b.getId() && modelo.getId() != c.getId()
            && a.getId() != b.getId() && a.getId() != c.getId() && b.getId() != c.getId());
        ca("CA-02", "modelo continua 315,00", igual(modelo.valorTotal(), "315.00"));
        ca("CA-03", "totais A, B e C",
            igual(a.valorTotal(), "315.00") && igual(b.valorTotal(), "504.00") && igual(c.valorTotal(), "387.00"));
        ca("CA-04", "modelo continua com 3 itens", modelo.getItens().size() == 3);
        ca("CA-05", "nenhum item compartilhado", semItemCompartilhado(modelo, List.of(a, b, c)));
        ca("CA-06", "criar inexistente lanca excecao", lancaExcecao(catalogo));
        ca("CA-07", "cliente do modelo preservado", "(modelo)".equals(modelo.getCliente()));

        medirTempos(modelo);
    }

    private static void imprimir(Pedido p) {
        System.out.println("Pedido #" + p.getId() + " │ " + p.getCliente() + " │ " + p.getEnderecoEntrega()
            + " │ itens=" + p.getItens().size() + " │ total=R$ " + p.valorTotal());
    }

    private static void ca(String id, String descricao, boolean ok) {
        System.out.println(id + " " + descricao + ": " + (ok ? "OK" : "FALHA"));
    }

    private static boolean igual(BigDecimal valor, String esperado) {
        return valor.compareTo(new BigDecimal(esperado)) == 0;
    }

    private static boolean semItemCompartilhado(Pedido modelo, List<Pedido> copias) {
        for (Pedido p : copias)
            for (ItemPedido i : p.getItens())
                for (ItemPedido m : modelo.getItens())
                    if (i == m) return false;
        return true;
    }

    private static boolean lancaExcecao(CatalogoDePedidos catalogo) {
        try {
            catalogo.criar("inexistente");
            return false;
        } catch (IllegalArgumentException e) {
            return true;
        }
    }

    private static void medirTempos(Pedido modelo) {
        int n = 100_000;
        long t0 = System.nanoTime();
        for (int i = 0; i < n; i++) modelo.clonar();
        long t1 = System.nanoTime();
        for (int i = 0; i < n; i++) modelo.clonarPorSerializacao();
        long t2 = System.nanoTime();
        System.out.println("clonar(): " + (t1 - t0) / 1_000_000 + " ms");
        System.out.println("clonarPorSerializacao(): " + (t2 - t1) / 1_000_000 + " ms");
    }
}
