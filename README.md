# 📋 ForoHub - API REST de Foro Comunitario

> Challenge Backend Java - Alura Latam | Oracle ONE Program

![Java](https://img.shields.io/badge/Java-17-orange?style=flat-square&logo=java)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.3-green?style=flat-square&logo=spring)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-18-blue?style=flat-square&logo=postgresql)
![JWT](https://img.shields.io/badge/JWT-Security-black?style=flat-square)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6-green?style=flat-square&logo=spring)

---

## 📖 Descripción

ForoHub es una API REST de foro comunitario desarrollada en Java con Spring Boot. Permite a los usuarios registrarse, autenticarse y participar en el foro creando, consultando, actualizando y eliminando tópicos. Implementa un sistema de seguridad robusto con Spring Security y autenticación basada en tokens JWT, garantizando que solo usuarios autenticados puedan realizar operaciones de escritura.

---

## ✨ Funcionalidades

### Gestión de Usuarios
- Registro de nuevos usuarios con contraseña encriptada (BCrypt)
- Autenticación mediante email y contraseña
- Generación de token JWT con expiración de 2 horas

### Gestión de Tópicos
- Crear nuevos tópicos con título, mensaje y curso
- Listar todos los tópicos del foro
- Consultar un tópico específico por ID
- Actualizar título, mensaje, curso y status de un tópico
- Eliminar tópicos
- Validación de tópicos duplicados

### Seguridad
- Autenticación con JSON Web Token (JWT)
- Rutas públicas y rutas protegidas
- Usuarios no autenticados reciben error 403
- Contraseñas encriptadas con BCrypt

### Interfaz Web
- Página de inicio
- Formulario de registro de usuarios
- Formulario de inicio de sesión
- Catálogo de tópicos con opción de crear nuevos

---

## 🛠️ Tecnologías Utilizadas

| Tecnología | Versión | Uso |
|--------  --|---------|-----|
| Java       | 17      | Lenguaje principal |
| Spring Boot| 4.0.3   | Framework principal |
| Spring Security | 6  | Seguridad y autenticación |
| Spring Data JPA | -  | Persistencia de datos |
| PostgreSQL | 18      | Base de datos |
| Thymeleaf | -        | Interfaz web |
| JWT (Auth0) | 4.4.0  | Tokens de autenticación |
| Lombok | -           | Reducción de código |
| Maven | - | Gestión de dependencias |

---

## 🔒 Endpoints de la API

### Públicos (no requieren autenticación)

| Método | Endpoint | Descripción | Body |
|--------|----------|-------------|------|
| POST | /usuarios | Registrar nuevo usuario | nombre, email, contrasena |
| POST | /login | Obtener token JWT | email, contrasena |

### Protegidos (requieren token JWT)

| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /topicos    | Listar todos los tópicos |
| GET | /topicos/{id} | Consultar tópico por ID |
| POST | /topicos   | Crear nuevo tópico |
| PUT | /topicos/{id} | Actualizar tópico |
| DELETE | /topicos/{id} | Eliminar tópico |

---

## 📋 Ejemplos de uso

### Registrar usuario
```json
POST /usuarios
{
    "nombre": "Juan Pérez",
    "email": "juan@email.com",
    "contrasena": "123456"
}
```

### Iniciar sesión
```json
POST /login
{
    "email": "juan@email.com",
    "contrasena": "123456"
}
```
Respuesta: token JWT

### Crear tópico (con token)
```json
POST /topicos
Authorization: Bearer {token}

{
    "titulo": "¿Cómo usar Spring Security?",
    "mensaje": "Tengo dudas sobre la configuración de Spring Security",
    "curso": "Spring Boot"
}
```

---

## ⚙️ Configuración e Instalación

### Requisitos previos
- Java 17 o superior
- Maven 3.8+
- PostgreSQL con pgAdmin 4

### 1. Clonar el repositorio
```bash
git clone https://github.com/IrvinFranciscoJarquinTorres/forohub-challenge-alura.git
cd forohub-challenge-alura
```

### 2. Crear la base de datos en PostgreSQL
```sql
CREATE DATABASE forohub_db;
```

### 3. Configurar credenciales
Copia el archivo de ejemplo y edítalo con tus datos:
```bash
cp src/main/resources/application-example.properties src/main/resources/application.properties
```

Edita `application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/forohub_db
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
api.security.secret=tu_clave_secreta
```

### 4. Ejecutar el proyecto
```bash
mvn spring-boot:run
```

### 5. Acceder a la aplicación
- Interfaz web: http://localhost:8080
- API REST: http://localhost:8080/topicos

---

## 🗄️ Estructura del Proyecto
```
forohub/
├── src/main/java/com/forohub/
│   ├── controller/
│   │   ├── AutenticacionController.java
│   │   ├── TopicoController.java
│   │   ├── UsuarioController.java
│   │   └── WebController.java
│   ├── dto/
│   │   ├── DatosActualizarTopico.java
│   │   ├── DatosAutenticacion.java
│   │   ├── DatosRegistroUsuario.java
│   │   ├── DatosRespuestaTopico.java
│   │   └── DatosTopico.java
│   ├── model/
│   │   ├── StatusTopico.java
│   │   ├── Topico.java
│   │   └── Usuario.java
│   ├── repository/
│   │   ├── TopicoRepository.java
│   │   └── UsuarioRepository.java
│   ├── security/
│   │   ├── SecurityConfigurations.java
│   │   ├── SecurityFilter.java
│   │   └── TokenService.java
│   └── service/
│       ├── AutenticacionService.java
│       ├── TopicoService.java
│       └── UsuarioService.java
├── src/main/resources/
│   ├── templates/
│   │   ├── index.html
│   │   ├── login.html
│   │   ├── registro.html
│   │   └── topicos.html
│   ├── static/
│   │   ├── css/styles.css
│   │   └── js/app.js
│   └── application-example.properties
├── .gitignore
├── pom.xml
└── README.md
```

---

## 👨‍💻 Autor

**Irvin Francisco Jarquín Torres**
Challenge Alura Latam - Oracle ONE | Spring Boot Backend
