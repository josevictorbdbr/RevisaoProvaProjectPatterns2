package adapter;

public final class CepUtil {
    private CepUtil() {}

    public static String soDigitos(String cep) {
        String d = cep == null ? "" : cep.replaceAll("\\D", "");
        if (d.length() != 8) throw new IllegalArgumentException("CEP deve ter 8 digitos: " + cep);
        return d;
    }

    public static String comHifen(String cep) {
        String d = soDigitos(cep);
        return d.substring(0, 5) + "-" + d.substring(5);
    }
}
