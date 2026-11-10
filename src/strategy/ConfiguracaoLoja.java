package strategy;

public final class ConfiguracaoLoja {
    private static final double PADRAO_PAC_KG = 2.50;
    private static final double PADRAO_PAC_KM = 0.05;
    private static final double PADRAO_SEDEX_KG = 4.00;
    private static final double PADRAO_SEDEX_KM = 0.12;
    private static final double PADRAO_URGENCIA = 15.00;

    private volatile double pacPorKg = PADRAO_PAC_KG;
    private volatile double pacPorKm = PADRAO_PAC_KM;
    private volatile double sedexPorKg = PADRAO_SEDEX_KG;
    private volatile double sedexPorKm = PADRAO_SEDEX_KM;
    private volatile double taxaUrgencia = PADRAO_URGENCIA;

    private ConfiguracaoLoja() {}

    private static class Holder {
        static final ConfiguracaoLoja INSTANCIA = new ConfiguracaoLoja();
    }

    public static ConfiguracaoLoja getInstance() { return Holder.INSTANCIA; }

    public double getPacPorKg() { return pacPorKg; }
    public void setPacPorKg(double v) { pacPorKg = v; }
    public double getPacPorKm() { return pacPorKm; }
    public void setPacPorKm(double v) { pacPorKm = v; }
    public double getSedexPorKg() { return sedexPorKg; }
    public void setSedexPorKg(double v) { sedexPorKg = v; }
    public double getSedexPorKm() { return sedexPorKm; }
    public void setSedexPorKm(double v) { sedexPorKm = v; }
    public double getTaxaUrgencia() { return taxaUrgencia; }
    public void setTaxaUrgencia(double v) { taxaUrgencia = v; }

    public synchronized void restaurarPadroes() {
        pacPorKg = PADRAO_PAC_KG;
        pacPorKm = PADRAO_PAC_KM;
        sedexPorKg = PADRAO_SEDEX_KG;
        sedexPorKm = PADRAO_SEDEX_KM;
        taxaUrgencia = PADRAO_URGENCIA;
    }
}
