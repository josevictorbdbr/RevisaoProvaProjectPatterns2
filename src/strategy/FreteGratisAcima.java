package strategy;

public class FreteGratisAcima implements FreteStrategy {
    private static final double LIMITE = 300.00;
    private final FreteStrategy interna;

    public FreteGratisAcima(FreteStrategy interna) { this.interna = interna; }

    @Override
    public String getNome() { return interna.getNome(); }

    @Override
    public double calcular(Pedido p) {
        return p.getValorItens() >= LIMITE ? 0.0 : interna.calcular(p);
    }
}
