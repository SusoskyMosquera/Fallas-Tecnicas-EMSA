package com.emsa.workflow;


import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;

import java.util.HashMap;
import java.util.Map;

public class SaveReport implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        // Obtener la información del modelo BPMN
        String nombre = (String) execution.getVariable("nombre");
        String domicilio = (String) execution.getVariable("domicilio");
        Long valorTotal = (Long) execution.getVariable("valorTotal");
        String queja = (String) execution.getVariable("falla");
        String descripcionVisita = (String) execution.getVariable("descripcionVisita");

        // Crear un mapa para almacenar la información de la visita técnica
        Map<String, Object> visitaTecnica = new HashMap<>();
        visitaTecnica.put("Cliente que reportó la falla", nombre);
        visitaTecnica.put("Dirección de la falla", domicilio);
        visitaTecnica.put("Valor total de la solución", valorTotal);
        visitaTecnica.put("Falla reportada", queja);

        System.out.println("Descripción de lo sucedido durante la visita técnica: " + descripcionVisita);

        // Recorrer el mapa e imprimir cada clave y valor
        for (Map.Entry<String, Object> entry : visitaTecnica.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}
