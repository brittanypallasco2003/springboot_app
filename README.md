# 🏦 Prueba Técnica Backend - Java Spring Boot

Este proyecto es una solución para la prueba técnica de backend nivel Junior. Consiste en una API REST para gestionar clientes, cuentas y movimientos bancarios, cumpliendo con las funcionalidades F1, F2 y F3 solicitadas.

---

## 🚀 Tecnologías

- Java 21
- Spring Boot
- Spring Data JPA
- Spring Validation
- Lombok
- MySQL
- Docker / Docker Compose
- Postman (colección incluida)

---

## 📦 Estructura del Proyecto

```bash
src/
├── controller/          # Endpoints REST
├── service/             # Lógica de negocio
├── repository/          # Acceso a base de datos
├── model/               # Entidades JPA
├── dto/                 # DTOs para requests y responses
├── exception/           # Manejo centralizado de errores
```
## 🧪 Endpoints disponibles (F1)
### 👤 Clientes
- POST /clientes
- GET /clientes
- PUT /clientes/{id}
- DELETE /clientes/{id}

## 💳 Cuentas
- POST /cuentas
- GET /cuentas
- PUT /cuentas/{id}
- DELETE /cuentas/{id}

## 💰 Movimientos
- POST /movimientos
- GET /movimientos

⚠️ No se permite DELETE de movimientos por consistencia contable.

## 🔁 Funcionalidades Clave
- ✅ F1: CRUD de Cliente, Cuenta y Movimiento
- ✅ F2: Registro de movimientos 
    - Actualiza el saldo disponible de la cuenta
    - Registra historial de transacciones

- ✅ F3: Validación de saldo insuficiente
    - Muestra "Saldo no disponible" cuando no hay fondos
    - Código HTTP: 422 Unprocessable Entity

## 📂 Postman
Incluye una colección Postman ["Technical_Test.postman_collection"](Technical_Test.postman_collection.json) para probar todos los endpoints con ejemplos de request y response.

## 📄 Script de la Base de datos
Incluye scrip de la base de datos, entidades y esquema datos, con el nombre ["BaseDatos.sql"](./src/main/resources/BaseDatos.sql)

## 🔧 Instalación local con Docker Compose

Clona este repositorio:

```bash
git clone https://github.com/brittanypallasco2003/springboot_app.git
```

Ve al directorio del proyecto

```bash
cd technical-test-backend
```

3. Copia el archivo .env.example a .env:

```bash
cp .env.example .env
```

### Variables de Entorno

Es obligatorio editarlo antes de ejecutar el proyecto, y reemplazar los valores por los tuyos reales:

| Variable                | Descripción                                                                                                      |
| ----------------------- | ---------------------------------------------------------------------------------------------------------------- |
| `SPRING_DATASOURCE_USERNAME`            | Usuario que la aplicación utilizará para acceder a la base de datos.                                             |
| `SPRING_DATASOURCE_PASSWORD`        | Contraseña del usuario especificado en `SPRING_DATASOURCE_USERNAME`.                                                             |
| `SPRING_DATASOURCE_URL` | URL de conexión a la base de datos (generalmente `jdbc:mysql://db:3306/nombre_bd` cuando se usa Docker Compose). |

4. Levanta los contenedores

```bash
docker-compose up --build
```

5. Las tablas se crearán automáticamente gracias a JPA/Hibernate (no hay datos iniciales insertados).