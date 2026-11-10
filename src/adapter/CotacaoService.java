package adapter;

import java.util.Comparator;
import java.util.List;

public class CotacaoService {
    private final List<CalculadoraFrete> transportadoras;

    public CotacaoService(List<CalculadoraFrete> transportadoras) {
        this.transportadoras = transportadoras;
    }

    public CalculadoraFrete maisBarata(String origem, String destino, double kg) {
        return transportadoras.stream()
            .min(Comparator.comparingDouble(t -> t.calcular(origem, destino, kg)))
            .orElseThrow();
    }
}
