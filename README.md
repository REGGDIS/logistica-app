
# 📦 Sistema de Gestión Logística - Spring Boot

Este es un sistema web CRUD desarrollado con **Spring Boot**, **Thymeleaf** y **MySQL**, que permite gestionar paquetes, asignar rutas de envío y realizar seguimiento del estado de los envíos.

---

## 🚀 Funcionalidades actuales

- ✅ Registrar nuevos paquetes
- ✅ Listar todos los paquetes registrados
- ✅ Editar información de paquetes
- ✅ Eliminar paquetes
- ✅ Asignar rutas a paquetes
- ✅ Ver detalles de un paquete individual
- ✅ Registrar historial de estados de envío (con estado y fecha)
- ✅ Visualizar el historial completo de cada paquete en su vista de detalle
- ✅ Validación de estados mediante lista desplegable predefinida

---

## 🖼️ Capturas de Pantalla

### 📦 Lista Paquetes

![Lista-Paquetes](screenshots/lista-paquetes.png)

---

### ✏️ Edición de Paquete

![Edicion-Paquete](screenshots/detalle-paquete.png)

---

### 📨 Estado de Envío

![Estado-Envio](screenshots/formulario-estado.png)

---

## 🛠️ Tecnologías utilizadas

- Java 23
- Spring Boot 3.5.4
- Spring Data JPA
- Thymeleaf
- MySQL
- Bootstrap 5

---

## ⚙️ Requisitos

- JDK 17 o superior (Java 23 recomendado)
- Maven
- MySQL Server

---

## 🔧 Configuración

1. Clona el repositorio:

```bash
git clone https://github.com/tu-usuario/logistica-java.git
cd logistica-java
```

2. Configura la base de datos en `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/logistica_db?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
```

3. Ejecuta la aplicación desde tu IDE o usando Maven:

```bash
./mvnw spring-boot:run
```

4. Accede a la app en:  
   [http://localhost:8080/paquetes](http://localhost:8080/paquetes)

---

## 🗃️ Estructura de la Base de Datos

Actualmente el sistema cuenta con las siguientes entidades:

- **Paquete**: id, descripción, destinatario, dirección de entrega, ruta asignada, historial de estados.
- **Ruta**: id, origen, destino.
- **EstadoEnvio**: id, estado, fecha, paquete asociado.

---

## ✍️ Autor

- 👨‍💻 **Roberto González**
- 🎓 Estudiante de Ingeniería en Informática
- 📫 Contacto: [LinkedIn](https://www.linkedin.com/) | [GitHub](https://github.com/REGGDIS)

---

## 📌 Licencia

Este proyecto está licenciado bajo la **MIT License**. Consulta el archivo [`LICENSE`](LICENSE) para más información.
