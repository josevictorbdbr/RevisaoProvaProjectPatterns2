package strategy;

public class FreteSedex implements FreteStrategy {
    @Override
    public String getNome() { return "SEDEX"; }

    @Override
    public double calcular(Pedido p) {
        ConfiguracaoLoja c = ConfiguracaoLoja.getInstance();
        return p.getPesoKg() * c.getSedexPorKg()
             + p.getDistanciaKm() * c.getSedexPorKm()
             + c.getTaxaUrgencia();
    }
}
