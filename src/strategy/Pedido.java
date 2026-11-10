package strategy;

public final class Pedido {
    private final int id;
    private final double pesoKg;
    private final double distanciaKm;
    private final double valorItens;

    public Pedido(int id, double pesoKg, double distanciaKm, double valorItens) {
        if (pesoKg <= 0) throw new IllegalArgumentException("peso deve ser maior que zero");
        if (distanciaKm < 0) throw new IllegalArgumentException("distancia negativa");
        if (valorItens < 0) throw new IllegalArgumentException("valor negativo");
        this.id = id;
        this.pesoKg = pesoKg;
        this.distanciaKm = distanciaKm;
        this.valorItens = valorItens;
    }

    public int getId() { return id; }
    public double getPesoKg() { return pesoKg; }
    public double getDistanciaKm() { return distanciaKm; }
    public double getValorItens() { return valorItens; }
}
