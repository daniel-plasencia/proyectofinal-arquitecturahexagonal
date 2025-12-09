# ALUMNO: Daniel Plasencia

# Proyecto: Banco Digital – Arquitectura Hexagonal  
**Tecnologías:**
* Spring Boot
* Arquitectura Hexagonal
* JPA/Hibernate
* H2
* MapStruct
* Lombok

Este proyecto implementa un sistema bancario simplificado usando **Arquitectura Hexagonal (Ports & Adapters)**.  

Permite gestionar:
* Clientes
* Cuentas
* Transacciones (transferencias entre cuentas con comisión fija).

---

## 1. Requisitos Previos

Instalar:

- **Java 21**
- **Maven 3.8+**
- **IntelliJ IDEA**
- **Postman** (para pruebas)

---

## 2. Ejecutar la Aplicación

### **Con IntelliJ**

1. Importar como **Maven Project**
2. Habilitar Lombok:  
   `Settings → Build Tools → Annotation Processors → Enable`
3. Ejecutar la clase:

```
ArquitecturahexagonalApplication
```
---


## 3. Base de Datos H2

La BD es **en memoria** y se crea al iniciar la app.

### Acceso a H2 Console

```
http://localhost:8080/h2-console
```

### Configuración

| Campo       | Valor              |
|-------------|--------------------|
| JDBC URL    | `jdbc:h2:mem:proyectohexagonal` |
| User        | `sa`               |
| Password    | *(vacío)*          |

---

## 4. Arquitectura del Proyecto

```
domain/
  model/
  exceptions/
application/
  ports/
    input/
    output/
  usecases/
infrastructure/
  adapters/
    input/rest/
    output/persistence/
    output/notification/
  config/
```

Ventajas:

- Bajo acoplamiento  
- Fácil testeo y escalabilidad  
- Independencia del framework  
- Reglas de negocio puras en `domain`

---

# 5. Endpoints de la API

---

# Módulo Cliente

### **GET /clientes**
Listar clientes.

### **GET /clientes/{id}**
Buscar cliente por ID.

---

# Módulo Cuenta

### **POST /cuentas**
Crear nueva cuenta.

**Body ejemplo:**
```json
{
  "clienteId": "CLI-1",
  "numeroCuenta": "001-ACC-TEST",
  "saldo": 500,
  "estado": "ACTIVO"
}

** Si no se coloca el saldo, se pondrá en 0 **
```

### **GET /cuentas**
Listar todas las cuentas.

### **GET /cuentas/{id}**
Obtener cuenta por ID.

### **GET /cuentas/{id}/saldo**
Consultar saldo de la cuenta.

### **GET /cuentas/por-cliente/{clienteId}**
Listar cuentas de un cliente.

---

# Módulo Transacciones

### **POST /transacciones**
Realizar transferencia.

**Body ejemplo:**
```json
{
  "cuentaOrigenId": "ACC-1",
  "cuentaDestinoId": "ACC-2",
  "monto": 100.00,
  "tipo": "TRANSFERENCIA",
  "descripcion": "Pago de prueba"
}
```

### **GET /transacciones**
Listar transacciones.

### **GET /transacciones/{id}**
Ver detalle de una transacción.

### **GET /transacciones/por-origen/{cuentaId}**
Transacciones donde la cuenta es origen.

### **GET /transacciones/por-destino/{cuentaId}**
Transacciones donde la cuenta es destino.

---

## 6. Compilar el Proyecto

```bash
mvn clean install
```

---

## 7. Resetear la BD

Al reiniciar la aplicación, H2 en memoria se recrea automáticamente.

---

## 8. Diagrama de arquitectura hexagonal realizada

    --- INFRAESTRUCTURA -----------------------------------------------------------

                         ┌──────────────────────────────┐
                         │       Input Adapters         │
                         │      REST Controllers        │
                         │(Cliente, Cuenta, Transaccion)│
                         └──────────────┬───────────────┘
                                        │
    --- APLICACION -------------------------------------------------------------

                                        │
                                        ▼
                           ┌─────────────────────────┐
                           │     INPUT PORTS         │
                           │  (Use Case Interfaces)  │
                           └────────────┬────────────┘
                                        │
                                        ▼
                       ┌─────────────────────────────────────┐
                       │           APPLICATION               │
                       │        USE CASES (Domain Logic)     │
                       │  - CrearCuentaUseCaseImpl           │
                       │  - ConsultarSaldoUseCaseImpl        │
                       │  - EncontrarCuentaUseCaseImpl       │
                       │  - TransferirDineroUseCaseImpl      │
                       │  - EncontrarClienteUseCaseImpl      │
                       │  - EncontrarTransferenciaUseCaseImpl│
                       └──────────────────┬──────────────────┘
                                          │
    --- DOMINIO ----------------------------------------------------------------

                                          │
                                          ▼
                        ┌──────────────────────────────────┐
                        │             DOMAIN               │
                        │  - Modelos (Cliente, Cuenta, Tx) │
                        │  - Validaciones de negocio       │
                        │  - Excepciones                   │
                        └──────────────────┬───────────────┘
                                           │
    --- APLICACION --------------------------------------------------------------

                                           │
                                           ▼
                       ┌────────────────────────────────────┐
                       │           OUTPUT PORTS             │
                       │ - CuentaRepositoryPort             │
                       │ - ClienteRepositoryPort            │
                       │ - TransaccionRepositoryPort        │
                       │ - NotificacionPort                 │
                       └──────────────────┬─────────────────┘
                                          │
    --- INFRAESTRUCTURA ---------------------------------------------------------
                                          │
                                          ▼
           ┌──────────────────────────────┴─────────────────────────────┐
           │                      ADAPTERS                               │
           │                                                             │
           │   Persistence Adapters (Infra Output)                       │
           │   - CuentaRepositoryAdapter → CuentaJpaRepository           │
           │   - ClienteRepositoryAdapter → ClienteJpaRepository         │
           │   - TransaccionRepositoryAdapter → TransaccionJpaRepository │
           │                                                             │
           │   Notification Adapter                                      │
           │   - NotificacionAdapter (Console)            │
           └────────────────────────────────────────────────────────────┘

**Explicación**

---
#### A. Adaptadores de Entrada (Input Adapters) (CAPA INFRAESTRUCTURA)

Son los controladores REST que exponen los endpoints públicos del sistema.

Ejemplos:

- `ClienteController`
- `CuentaController`
- `TransaccionController`

Responsabilidades:

- Recibir solicitudes HTTP
- Validar datos
- Convertir DTO ↔ Domain
- Invocar el caso de uso correspondiente

**Los controladores NO contienen lógica de negocio.**

---
#### B. Interfaces de Entrada (Input Ports) (CAPA APLICACION)

Son **interfaces** que definen las operaciones disponibles del sistema.  
No contienen lógica; solo **qué** acciones se pueden ejecutar.

Ejemplos en el proyecto:

- `CrearCuentaUseCase`
- `ConsultarSaldoUseCase`
- `TransferirDineroUseCase`
- `EncontrarClienteUseCase`

Permiten que los controladores REST interactúen con los casos de uso **sin conocer detalles del dominio o infraestructura**.

---

#### C. Casos de Uso (CAPA APLICACION)

Implementan la lógica de aplicación.  
Coordinan entidades, validan datos, manejan transacciones y llaman a los repositorios.

Ejemplos:

- `CrearCuentaUseCaseImpl`
- `TransferirDineroUseCaseImpl`
- `EncontrarClienteUseCaseImpl`

Características:

- Contienen reglas de aplicación (no de dominio)
- No conocen JPA, HTTP ni ninguna tecnología externa
- Usan **output ports** para interactuar con infraestructura

---

#### D. Dominio (CAPA DOMINIO)

Es el corazón del sistema.  
Contiene las **reglas del negocio puras**, implementadas mediante:

- Entidades: `Cliente`, `Cuenta`, `Transaccion`
- Validaciones internas
- Excepciones
- Invariantes del negocio

Esta capa **no depende de Spring ni de frameworks**, lo que hace que sea altamente testeable y portable.

---

#### E. Interfaces de Salida (Output Ports) (CAPA APLICACION)

Son interfaces que definen *lo que la aplicación necesita* de la infraestructura.

Ejemplos:

- `CuentaRepositoryPort`
- `ClienteRepositoryPort`
- `TransaccionRepositoryPort`
- `NotificacionPort`

Los casos de uso solo dependen de estas interfaces, y no de implementaciones concretas.

---

#### F. Adaptadores de Infraestructura (Output Adapters) (CAPA INFRAESTRUCTURA)

Implementan los *output ports*.  
Son específicos de la tecnología utilizada (JPA, H2, email, consola, etc.).

Ejemplos:

- `CuentaRepositoryAdapter`
- `TransaccionRepositoryAdapter`
- `ClienteRepositoryAdapter`
- `NotificacionAdapter`

Responsabilidades:

- Convertir Domain ↔ Entity (mapper)
- Acceder a la base de datos mediante JPA
- Persistir información
- Enviar notificaciones

---
## 7. Patrones de Diseño

### Patron Singleton

En la arquitectura implementada, la clase de configuración de Spring **(Config)** funciona bajo el patrón **Singleton**.
Esto ocurre automáticamente porque Spring Boot, al procesar las clases anotadas con **@Configuration**, crea una única instancia de dicha clase durante el ciclo de vida de la aplicación.

El objetivo es garantizar que:

* Los beans definidos dentro de la clase de configuración se construyan una sola vez.
* Todas las partes de la aplicación usen la misma instancia compartida.
* Se evite la creación repetitiva e innecesaria de objetos.

De esta forma, Spring implementa el patrón Singleton sin necesidad de código adicional (como constructores privados)

### Patron Adapter 

En el proyecto se aplica el patrón Adapter tanto para la entrada como para la salida:

#### Output Adapters:

* **CuentaRepositoryAdapter** implementa **CuentaRepositoryPort** y adapta Spring Data JPA (CuentaJpaRepository).
* **ClienteRepositoryAdapter** implementa **ClienteRepositoryPort** y adapta Spring Data JPA (ClienteJpaRepository).
* **TransaccionRepositoryAdapter** implementa **TransaccionRepositoryPort** y adapta Spring Data JPA (TransaccionJpaRepository).
* **NotificacionAdapter** implementa **NotificacionPort** y adapta las notificaciones a una implementación concreta (consola).

#### Input Adapters:

* ClienteController, CuentaController y TransaccionController actúan como adaptadores REST, transformando HTTP/JSON en llamadas a los casos de uso.

---
## 8. ADR

En carpeta /docs


