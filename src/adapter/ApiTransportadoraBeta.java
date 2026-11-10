package adapter;

import java.util.Locale;

public class ApiTransportadoraBeta {
    public String simular(String cepOrigem, String cepDestino, double kg) {
        return String.format(new Locale("pt", "BR"), "R$ %.2f", 15.00 + kg * 8.46);
    }

    public String previsaoEntrega(String cepOrigem, String cepDestino) { return "5 dias uteis"; }
}
