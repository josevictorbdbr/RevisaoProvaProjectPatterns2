package strategy;

public class RetiradaNaLoja implements FreteStrategy {
    @Override
    public String getNome() { return "RETIRADA"; }

    @Override
    public double calcular(Pedido p) { return 0.0; }
}
