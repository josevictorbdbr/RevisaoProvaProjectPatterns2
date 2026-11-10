package adapter;

public class FreteAlfaAdapter implements CalculadoraFrete {
    private final ServicoTransportadoraAlfa alfa;

    public FreteAlfaAdapter(ServicoTransportadoraAlfa alfa) { this.alfa = alfa; }

    @Override
    public double calcular(String cepOrigem, String cepDestino, double pesoKg) {
        long gramas = Math.round(pesoKg * 1000);
        long centavos = alfa.cotarEntrega(CepUtil.soDigitos(cepOrigem), CepUtil.soDigitos(cepDestino), gramas);
        return centavos / 100.0;
    }

    @Override
    public int prazoDias(String cepOrigem, String cepDestino) {
        return alfa.estimarPrazo(CepUtil.soDigitos(cepOrigem), CepUtil.soDigitos(cepDestino));
    }

    @Override
    public String nomeTransportadora() { return "Alfa"; }
}
