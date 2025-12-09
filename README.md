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
