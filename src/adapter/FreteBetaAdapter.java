package adapter;

public class FreteBetaAdapter implements CalculadoraFrete {
    private final ApiTransportadoraBeta beta;

    public FreteBetaAdapter(ApiTransportadoraBeta beta) { this.beta = beta; }

    @Override
    public double calcular(String cepOrigem, String cepDestino, double pesoKg) {
        String texto = beta.simular(CepUtil.comHifen(cepOrigem), CepUtil.comHifen(cepDestino), pesoKg);
        return Double.parseDouble(texto.replace("R$", "").trim().replace(".", "").replace(',', '.'));
    }

    @Override
    public int prazoDias(String cepOrigem, String cepDestino) {
        String texto = beta.previsaoEntrega(CepUtil.comHifen(cepOrigem), CepUtil.comHifen(cepDestino));
        return Integer.parseInt(texto.replaceAll("\\D", ""));
    }

    @Override
    public String nomeTransportadora() { return "Beta"; }
}
