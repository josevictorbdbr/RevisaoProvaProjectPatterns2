package adapter;

import java.util.List;
import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        String origem = "80010-000";
        String destino = "01310-100";
        double kg = 2.5;

        ClienteTransportadoraGama gama = new ClienteTransportadoraGama();
        List<CalculadoraFrete> lista = List.of(
            new FreteAlfaAdapter(new ServicoTransportadoraAlfa()),
            new FreteBetaAdapter(new ApiTransportadoraBeta()),
            new FreteGamaAdapter(gama, false),
            new FreteGamaAdapter(gama, true)
        );

        for (CalculadoraFrete t : lista) {
            System.out.println(String.format(new Locale("pt", "BR"), "%s: R$ %.2f (%d dias)",
                t.nomeTransportadora(), t.calcular(origem, destino, kg), t.prazoDias(origem, destino)));
        }

        CalculadoraFrete melhor = new CotacaoService(lista).maisBarata(origem, destino, kg);
        System.out.println("Mais barata: " + melhor.nomeTransportadora());
    }
}
