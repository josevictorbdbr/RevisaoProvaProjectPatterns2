package adapter;

public class FreteGamaAdapter implements CalculadoraFrete {
    private final ClienteTransportadoraGama gama;
    private final boolean expresso;

    public FreteGamaAdapter(ClienteTransportadoraGama gama, boolean expresso) {
        this.gama = gama;
        this.expresso = expresso;
    }

    private TarifaGama tarifa(String origem, String destino, double kg) {
        int o = Integer.parseInt(CepUtil.soDigitos(origem));
        int d = Integer.parseInt(CepUtil.soDigitos(destino));
        return gama.consultarTarifa(o, d, kg, expresso);
    }

    @Override
    public double calcular(String cepOrigem, String cepDestino, double pesoKg) {
        TarifaGama t = tarifa(cepOrigem, cepDestino, pesoKg);
        return t.getValorBruto() * (1 - t.getDesconto());
    }

    @Override
    public int prazoDias(String cepOrigem, String cepDestino) {
        return (int) Math.ceil(tarifa(cepOrigem, cepDestino, 1).getPrazoHoras() / 24.0);
    }

    @Override
    public String nomeTransportadora() { return expresso ? "Gama Expresso" : "Gama"; }
}
