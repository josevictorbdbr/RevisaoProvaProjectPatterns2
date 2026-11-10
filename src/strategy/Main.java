package strategy;

import java.util.Locale;

public class Main {
    public static void main(String[] args) {
        Pedido pedido = new Pedido(1, 2, 100, 200.00);
        CalculadoraDeFrete calc = new CalculadoraDeFrete();
        ConfiguracaoLoja config = ConfiguracaoLoja.getInstance();

        calc.setEstrategia(new FretePac());
        imprimir("PAC", calc.calcular(pedido));
        calc.setEstrategia(new FreteSedex());
        imprimir("SEDEX", calc.calcular(pedido));
        calc.setEstrategia(new RetiradaNaLoja());
        imprimir("RETIRADA", calc.calcular(pedido));

        config.setPacPorKg(5.00);
        calc.setEstrategia(new FretePac());
        imprimir("PAC (5,00/kg)", calc.calcular(pedido));

        config.restaurarPadroes();

        System.out.println("Mesma instancia: "
            + (ConfiguracaoLoja.getInstance() == ConfiguracaoLoja.getInstance()));

        AuditLog.getInstance().getRegistros().forEach(System.out::println);
    }

    private static void imprimir(String nome, double valor) {
        System.out.println(String.format(Locale.US, "%s: R$ %.2f", nome, valor));
    }
}
