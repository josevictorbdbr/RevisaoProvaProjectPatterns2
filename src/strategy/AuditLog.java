package strategy;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public final class AuditLog {
    private static volatile AuditLog instancia;
    private final List<String> registros = new CopyOnWriteArrayList<>();

    private AuditLog() {}

    public static AuditLog getInstance() {
        if (instancia == null) {
            synchronized (AuditLog.class) {
                if (instancia == null) instancia = new AuditLog();
            }
        }
        return instancia;
    }

    public void registrar(String mensagem) { registros.add(mensagem); }

    public List<String> getRegistros() { return Collections.unmodifiableList(registros); }

    public void limpar() { registros.clear(); }
}
