package strategy;

import java.util.Locale;

public class CalculadoraDeFrete {
    private FreteStrategy estrategia;

    public CalculadoraDeFrete() {}

    public CalculadoraDeFrete(FreteStrategy estrategia) { this.estrategia = estrategia; }

    public void setEstrategia(FreteStrategy estrategia) { this.estrategia = estrategia; }

    public double calcular(Pedido pedido) {
        if (estrategia == null) throw new IllegalStateException("nenhuma estrategia definida");
        double frete = estrategia.calcular(pedido);
        AuditLog.getInstance().registrar(String.format(Locale.US,
            "pedido=%d estrategia=%s frete=%.2f", pedido.getId(), estrategia.getNome(), frete));
        return frete;
    }
}
