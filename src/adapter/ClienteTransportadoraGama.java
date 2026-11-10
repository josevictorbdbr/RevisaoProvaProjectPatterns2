package adapter;

public class ClienteTransportadoraGama {
    public TarifaGama consultarTarifa(int cepOrigem, int cepDestino, double pesoKg, boolean expresso) {
        return expresso ? new TarifaGama(60.00, 0.20, 24) : new TarifaGama(40.00, 0.25, 72);
    }
}
