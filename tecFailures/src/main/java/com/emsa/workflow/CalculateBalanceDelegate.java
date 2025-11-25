package com.emsa.workflow;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("calculateBalance")
public class CalculateBalanceDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Recupera las variables valorTotal y valorPagado
        // Note: Using camelCase as found in the form definitions
        Number valorTotalNum = (Number) execution.getVariable("valorTotal");
        Number valorPagadoNum = (Number) execution.getVariable("valorPagado");

        long valorTotal = valorTotalNum != null ? valorTotalNum.longValue() : 0L;
        long valorPagado = valorPagadoNum != null ? valorPagadoNum.longValue() : 0L;

        long diferencia = valorPagado - valorTotal;
        String estadoPago;
        long saldoCliente;

        if (diferencia > 0) {
            estadoPago = "Saldo a favor";
            saldoCliente = diferencia;
        } else if (diferencia < 0) {
            estadoPago = "Saldo faltante";
            saldoCliente = Math.abs(diferencia);
        } else {
            estadoPago = "Completo";
            saldoCliente = 0;
        }

        execution.setVariable("estadoPago", estadoPago);
        execution.setVariable("saldoCliente", saldoCliente);
    }
}
