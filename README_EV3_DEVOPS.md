# Evaluación Parcial 3 - Observabilidad e Infraestructura Backend

## Descripción

Este repositorio corresponde al backend del proyecto **PokecardStore**, desarrollado con Spring Boot. Para la Evaluación Parcial 3 se implementó una solución de observabilidad y monitoreo utilizando PostgreSQL, Docker Compose, Prometheus y Grafana.

El objetivo fue permitir la supervisión en tiempo real del estado de la aplicación, métricas de rendimiento y disponibilidad del servicio.

---

## Tecnologías Utilizadas

- Java 21
- Spring Boot 4
- Spring Security
- PostgreSQL 16
- Docker
- Docker Compose
- GitHub Actions
- Spring Boot Actuator
- Micrometer Prometheus
- Prometheus
- Grafana

---

## Integracion Continua con GitHub Actions

Se agrego un workflow de CI en:

```text
.github/workflows/ci.yml
```

El pipeline se ejecuta en cada push hacia las ramas `main`, `dev`, y en cada pull request hacia `main` y `dev`.

### Validaciones del pipeline

- Configura Java 21.
- Levanta PostgreSQL 16 como servicio de GitHub Actions.
- Ejecuta la compilacion y pruebas con Maven.
- Analiza calidad y seguridad del codigo con SonarCloud.
- Construye la imagen Docker del backend.
- Valida la configuracion de Docker Compose.

---

## Arquitectura Implementada

El backend Spring Boot expone métricas mediante Actuator y Micrometer, las cuales son recolectadas por Prometheus y visualizadas en Grafana.

```text
Spring Boot
     │
     ▼
Actuator + Micrometer
     │
     ▼
/actuator/prometheus
     │
     ▼
Prometheus
     │
     ▼
Grafana
```

---

## Infraestructura Docker

Se implementó Docker Compose para levantar los siguientes servicios:

- PostgreSQL
- Prometheus
- Grafana

### Evidencia Docker Compose

![Docker Compose](![alt text](image.png))

### Levantar servicios

```bash
docker compose up -d
```

### Detener servicios

```bash
docker compose down
```

### Verificar contenedores

```bash
docker ps
```

---

## Configuración de PostgreSQL

Se configuró una base de datos PostgreSQL ejecutándose localmente mediante Docker.

### Parámetros configurados

| Parámetro | Valor |
|------------|---------|
| Base de datos | pokecardstore |
| Usuario | pokecardstore_user |
| Puerto | 5432 |

---

## Observabilidad

### Spring Boot Actuator

Se habilitaron los siguientes endpoints:

```text
/actuator/health
/actuator/prometheus
```

### Estado de salud de la aplicación

![Actuator Health](![alt text](image-1.png))

```text
http://localhost:8080/actuator/health
```

### Métricas Prometheus

![Actuator Prometheus](![alt text](image-2.png))

```text
http://localhost:8080/actuator/prometheus
```

---

## Prometheus

Prometheus fue configurado para recolectar métricas expuestas por el backend mediante el endpoint:

```text
/actuator/prometheus
```

### Acceso

```text
http://localhost:9090
```

### Evidencia de Targets

![Prometheus Targets](![alt text](image-3.png))

### Verificación de Targets

En la sección:

```text
Status → Targets
```

deben visualizarse los siguientes servicios en estado **UP**:

- pokecard-backend
- prometheus

---

## Grafana

Grafana fue configurado como plataforma de visualización y monitoreo de métricas.

### Acceso

```text
http://localhost:3000
```

### Credenciales

```text
Usuario: admin
Contraseña: devops
```

---

## Dashboard Implementado

Se creó un dashboard llamado:

```text
Dashboard de Observabilidad - PokecardStore
```

### Vista General del Dashboard

![Dashboard Grafana](![alt text](image-4.png))

---

### Disponibilidad de Servicios

![Panel Disponibilidad](![alt text](image-5.png))

Consulta utilizada:

```promql
up
```

Permite verificar si los servicios monitoreados se encuentran disponibles.

---

### Uso de Memoria JVM

![Panel Memoria](![alt text](image-6.png))

Consulta utilizada:

```promql
sum(jvm_memory_used_bytes)
```

Permite visualizar el consumo de memoria utilizado por la aplicación Java.

---

### Consumo de CPU del Backend

![Panel CPU](![alt text](image-7.png))

Consulta utilizada:

```promql
process_cpu_usage
```

Permite monitorear el uso de CPU de la aplicación.

---

### Total de Solicitudes HTTP

![Panel HTTP](![alt text](image-8.png))

Consulta utilizada:

```promql
http_server_requests_seconds_count
```

Permite visualizar la cantidad total de solicitudes procesadas por el backend.

---

## Resultado

Se implementó una solución completa de observabilidad para el backend del proyecto PokecardStore utilizando herramientas estándar del ecosistema DevOps.

La solución permite:

- Supervisar el estado de salud de la aplicación.
- Monitorear métricas de rendimiento.
- Visualizar información en tiempo real mediante dashboards.
- Facilitar el análisis y diagnóstico del sistema.
- Sentar las bases para futuros despliegues en entornos cloud.

---

## Autores

**Geraldine Carolina Becerra**
**Luis Guillermo Muñoz Soto**

Implementación de infraestructura y observabilidad:

- PostgreSQL
- Docker Compose
- Spring Boot Actuator
- Micrometer Prometheus
- Prometheus
- Grafana
- Dashboard de monitoreo
