package strategy;

public class FretePac implements FreteStrategy {
    @Override
    public String getNome() { return "PAC"; }

    @Override
    public double calcular(Pedido p) {
        ConfiguracaoLoja c = ConfiguracaoLoja.getInstance();
        return p.getPesoKg() * c.getPacPorKg() + p.getDistanciaKm() * c.getPacPorKm();
    }
}
