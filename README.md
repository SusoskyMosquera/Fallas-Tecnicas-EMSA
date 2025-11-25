"# Proyecto de Gestión de Fallas Técnicas EMSA

Este repositorio contiene la implementación de un proceso de negocio (BPMN) para la gestión de fallas técnicas de EMSA, orquestado con **Camunda Platform 7** y **Spring Boot**, e integrado con un sistema externo simulado de gestión de tickets (**FSM-Service**).

## Estructura del Proyecto

El sistema se compone de dos microservicios principales:

1.  **tecFailures (Motor de Procesos):**
    *   Aplicación Spring Boot con Camunda embebido.
    *   Contiene los modelos BPMN (`tecFailures.bpmn`, `realizarVisitaTecnica.bpmn`), tablas DMN y formularios.
    *   Orquesta todo el flujo desde el reporte de la falla hasta el pago.
    *   Puerto: `8080`.

2.  **FSM-Service (Sistema Externo Simulado):**
    *   Servicio REST simple en Spring Boot.
    *   Simula un sistema de gestión de servicios de campo (Field Service Management).
    *   Expone endpoints para crear y actualizar tickets.
    *   Puerto: `9095`.

## Flujo del Proceso de Negocio

El proceso cubre los siguientes pasos:

1.  **Registro de Reporte:** El usuario inicia el proceso reportando una falla.
2.  **Agendar Visita Técnica:** Se recolectan datos básicos y se agenda la visita.
    *   *Integración:* Se conecta con `FSM-Service` para crear un ticket externo.
3.  **Realizar Visita Técnica (Subproceso):**
    *   El técnico realiza el reporte.
    *   *Integración:* Se actualiza el estado del ticket en `FSM-Service`.
4.  **Clasificar Presupuesto (DMN):** Regla de negocio para determinar costos.
5.  **Generar Factura:** Se muestra el resumen y el estado del ticket externo.
6.  **Recibir Pago:** El usuario ingresa el monto a pagar.
7.  **Calcular Balance (Java Delegate):** El sistema calcula automáticamente si hay saldo a favor, faltante o si el pago es completo.
8.  **Resumen de Pago:** Se muestra al usuario el resultado del cálculo.

## Requisitos Previos

*   Java 17 o superior.
*   Maven.

## Instrucciones de Ejecución

Para probar el sistema completo, debes ejecutar ambos servicios simultáneamente en terminales separadas.

### 1. Iniciar el Servicio de Tickets (FSM-Service)

```bash
cd FSM-Service
./mvnw spring-boot:run
```
*El servicio iniciará en el puerto 9095.*

### 2. Iniciar el Motor de Procesos (tecFailures)

```bash
cd tecFailures
./mvnw spring-boot:run
```
*La aplicación iniciará en el puerto 8080.*

## Uso y Pruebas

1.  Accede a la **Camunda Tasklist**: [http://localhost:8080/camunda/app/tasklist/](http://localhost:8080/camunda/app/tasklist/)
    *   **Usuario:** `admin`
    *   **Contraseña:** `123`
2.  Inicia el proceso **"Fallas Técnicas de EMSA"**.
3.  Completa las tareas de usuario asignadas.
4.  Verifica en la consola de `FSM-Service` que las peticiones HTTP (`/tickets/crear`, `/tickets/actualizar`) se reciben correctamente.

## Detalles Técnicos de la Implementación

*   **Conectores HTTP:** Se utiliza `http-connector` de Camunda para las integraciones REST.
*   **Java Delegate:** La clase `CalculateBalanceDelegate` maneja la lógica de negocio para la validación de pagos.
*   **Formularios:** Se utilizan formularios embebidos de Camunda para la interacción con el usuario." 
