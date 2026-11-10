package adapter;

public class ServicoTransportadoraAlfa {
    public long cotarEntrega(String cepOrigem8, String cepDestino8, long pesoGramas) {
        return 1000 + pesoGramas * 24 / 25;
    }

    public int estimarPrazo(String cepOrigem8, String cepDestino8) { return 6; }
}
