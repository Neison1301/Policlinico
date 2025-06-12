# 🏥 Sistema de Citas Médicas

![Logo](https://www.facebook.com/p/Policl%C3%ADnico-Infantil-Nuestra-Se%C3%B1ora-del-Sagrado-Coraz%C3%B3n-Oficial-100063538544907/?locale=es_LA) Un sistema completo para la gestión eficiente de citas médicas, diseñado para optimizar el flujo de trabajo en clínicas y consultorios. Facilita la interacción entre pacientes, recepcionistas y administradores para agendar, modificar y gestionar citas, así como para supervisar las operaciones del sistema.

### ✅ Objetivos del Proyecto

#### Objetivo General:
Implementar un **sistema digital de agendamiento de citas médicas** para optimizar la atención y experiencia del paciente.

#### Objetivos Específicos:
* Plataforma web accesible desde cualquier dispositivo.
* Gestión automática de horarios y disponibilidad.
* Eficiencia en la administración de citas.

---

### 📦 Alcance del Proyecto

#### Alcances:
* Gestión de citas médicas en línea.
* Funcionalidades para pacientes, recepcionistas y administradores.
* Plataforma 100% web responsive (adaptada a móviles y PC).
* **[OPCIONAL]** Puedes agregar una sección de limitaciones aquí, si aplica. Por ejemplo: "Limitado a la gestión de citas y no incluye facturación compleja o historial clínico completo."

---

## 🌟 Funcionalidades Detalladas por Rol

Basado en el diagrama de casos de uso, el sistema soporta las siguientes funcionalidades clave, categorizadas por actor:

### 👤 Recepcionista
* **Gestión de Asistencia:** Marcar asistencia de pacientes, registrar inasistencias o reprogramaciones.
* **Gestión de Citas:**
    * Ver citas del día (con filtros por fecha y especialidad).
    * Agendar citas para pacientes (incluye verificar disponibilidad de horarios, ingresar datos del paciente).
    * Modificar y cancelar citas (con confirmación).
    * Ver citas pasadas y próximas.
* **Gestión de Pagos:** Registrar y confirmar pagos.
* **Control de Sesión:** Iniciar y cerrar sesión.

### 👥 Paciente
* **Registro y Perfil:** Registrarse en el sistema y actualizar datos personales.
* **Agendamiento de Citas:**
    * Agendar citas médicas (incluye ver disponibilidad de citas, seleccionar especialidad, ver detalles, confirmar datos personales, realizar pago y recibir confirmación).
    * Ver citas (próximas y pasadas, con detalles).
* **Control de Sesión:** Iniciar y cerrar sesión.

### 👨‍💻 Administrador
* **Gestión de Usuarios:** Crear, modificar y eliminar cuentas de usuario (incluye la creación de cuentas de recepcionistas y médicos).
* **Gestión de Médicos:** Registrar, eliminar y actualizar datos de médicos.
* **Configuración de Disponibilidad Médica:** Asignar horarios a médicos, modificar y eliminar disponibilidad.
* **Control de Sesión:** Iniciar y cerrar sesión.

---

## 🏛️ Arquitectura y Tecnologías

El proyecto está diseñado siguiendo principios de arquitectura limpia y patrones de diseño para asegurar escalabilidad, mantenibilidad y robustez.

### Patrones de Diseño y Principios
* **MVC (Model-View-Controller):**
    * El **Frontend** (React + Vite) actúa como la **Vista** y parte del **Controlador** (manejo de interacciones de usuario y llamadas a la API).
    * El **Backend** (Java 21 + Spring Boot) implementa la lógica de **Modelo** (datos y lógica de negocio) y la otra parte del **Controlador** (manejo de las solicitudes HTTP, validación y orquestación con la capa de servicio).
* **DAO (Data Access Object):**
    * Se utiliza un patrón DAO en la capa `repository` del backend para abstraer y encapsular todas las operaciones de acceso a la base de datos (MySQL). Esto desacopla la lógica de negocio del acceso a datos.
* **DTO (Data Transfer Object):**
    * Se utilizan DTOs en la capa `dto` para transferir datos de forma eficiente entre capas del backend y con el frontend, optimizando la comunicación y la seguridad.
* **Principios SOLID:**
    * El código se ha desarrollado aplicando los principios SOLID (Single Responsibility, Open/Closed, Liskov Substitution, Interface Segregation, Dependency Inversion) para promover un diseño modular, flexible y fácil de extender y probar.
* **TDD (Test-Driven Development):**
    * El desarrollo ha seguido una metodología TDD, donde las pruebas unitarias y de integración se escriben antes que el código de producción, asegurando una alta cobertura de pruebas y reduciendo bugs.

---

### 💻 Tecnologías Utilizadas

| Tecnología     | Rol                                |
|----------------|-------------------------------------|
| **Java 21**    | Lógica de negocio (back-end)        |
| **Spring Boot**| Framework backend y API REST        |
| **MySQL**      | Base de datos relacional            |
| **React + Vite**| Frontend moderno y veloz           |
| **HTML/CSS/TS**| Maquetado y estilos responsivos     |
| **MVC**        | Arquitectura del sistema            |
| **DAO/DTO**    | Patrón para gestión de datos        |
| **SOLID**      | Principios de diseño de software    |
| **TDD**        | Desarrollo guiado por pruebas       |

---

## 📂 Estructura del Proyecto

La estructura del proyecto se organiza de la siguiente manera:

```plaintext
📁 backend/
 ┣ 📂 src/
 ┃ ┣ 📂 controller/ # Controladores REST para manejar las solicitudes HTTP
 ┃ ┣ 📂 model/      # Entidades de datos y lógica de negocio (dominio)
 ┃ ┣ 📂 repository/ # Interfaces DAO para la interacción con la base de datos
 ┃ ┣ 📂 service/    # Lógica de negocio (maneja la orquestación entre controladores y repositorios)
 ┃ ┣ 📂 dto/        # Objetos de Transferencia de Datos (para comunicación entre capas/servicios)
 ┃ ┗ 📂 config/     # Configuraciones de la aplicación (ej. seguridad, base de datos)
 ┗ application.properties # Archivo de propiedades para la configuración del backend

📁 frontend/
 ┣ 📂 src/
 ┃ ┣ 📂 components/ # Componentes reutilizables de la interfaz de usuario
 ┃ ┣ 📂 pages/      # Páginas o vistas principales de la aplicación
 ┃ ┣ 📂 services/   # Clientes para consumir APIs del backend
 ┃ ┣ 📂 interfaces/ # Definiciones de tipos (TypeScript) para datos y respuestas de API
 ┃ ┗ App.tsx       # Componente principal de la aplicación React
 ┗ vite.config.ts  # Configuración del servidor de desarrollo y build de Vite

```
--

# 🚀 Guía Completa de Configuración: Polyclinic-Appointment-App

Esta guía te llevará paso a paso a través de todo el proceso para configurar y ejecutar el sistema de citas médicas "Polyclinic-Appointment-App" en tu máquina local, asumiendo que partes desde cero.

---

## 🎯 ¿Qué es Polyclinic-Appointment-App?

Es un sistema completo para la gestión eficiente de citas médicas, diseñado para optimizar el flujo de trabajo en clínicas y consultorios. Facilita la interacción entre pacientes, recepcionistas y administradores para agendar, modificar y gestionar citas, así como para supervisar las operaciones del sistema.

### Tecnologías Clave:
* **Backend:** Java 21, Spring Boot, MySQL
* **Frontend:** React, Vite, HTML/CSS/TypeScript

---

## 1. 🛠️ Instalación de Requisitos Previos

Antes de clonar el proyecto, necesitas instalar todas las herramientas necesarias. Sigue los enlaces para descargar e instalar cada una:

1.  **Git:**
    * Herramienta esencial para descargar y gestionar el código fuente.
    * **Descarga:** [https://git-scm.com/downloads](https://git-scm.com/downloads)
    * **Verificación:** Abre tu terminal/CMD y escribe `git --version`. Deberías ver la versión instalada.

2.  **Java Development Kit (JDK) 21:**
    * Necesario para compilar y ejecutar el backend de Spring Boot.
    * **Descarga:** [Oracle JDK](https://www.oracle.com/java/technologies/downloads/) (selecciona la versión 21 para tu sistema operativo).
    * **Verificación:** Abre tu terminal/CMD y escribe `java -version`. Deberías ver la versión `21.x.x`.

3.  **Apache Maven:**
    * Herramienta de gestión de proyectos y construcción para Java.
    * **Descarga:** [https://maven.apache.org/download.cgi](https://maven.apache.org/download.cgi)
    * **Configuración:** Sigue las instrucciones de instalación para configurar la variable de entorno `PATH`.
    * **Verificación:** Abre tu terminal/CMD y escribe `mvn --version`. Deberías ver la versión instalada.

4.  **Node.js y npm:**
    * Necesario para ejecutar el frontend de React. `npm` (Node Package Manager) se instala automáticamente con Node.js.
    * **Descarga:** [https://nodejs.org/es/download/](https://nodejs.org/es/download/) (Se recomienda la versión LTS).
    * **Verificación:** Abre tu terminal/CMD y escribe `node -v` y `npm -v`. Deberías ver las versiones instaladas.

5.  **MySQL Server:**
    * La base de datos relacional que usará el backend.
    * **Descarga:** [MySQL Community Server](https://dev.mysql.com/downloads/mysql/) (incluye MySQL Workbench, una herramienta gráfica útil).
    * **Configuración:** Durante la instalación, establece un usuario `root` y una contraseña, o un usuario específico con permisos. **¡Asegúrate de recordar estas credenciales!**

---

## 2. ⬇️ Clonar el Repositorio

Ahora que tienes todas las herramientas, es hora de obtener el código del proyecto.

1.  **Abre tu Terminal (Linux/macOS) o Símbolo del Sistema / PowerShell (Windows).**
    * **Recomendación:** Abre la terminal en el lugar donde te gustaría guardar tus proyectos (ej. crea una carpeta `Proyectos` en tu directorio de usuario).

2.  **Navega a tu carpeta de proyectos (si creaste una):**

    ```bash
    # Ejemplo para Linux/macOS
    cd ~/Proyectos

    # Ejemplo para Windows (cambia TuUsuario por tu nombre de usuario)
    cd C:\Users\TuUsuario\Proyectos
    ```
    *Si la carpeta `Proyectos` no existe, puedes crearla con `mkdir Proyectos` y luego `cd Proyectos`.*

3.  **Clona el repositorio del proyecto:**

    ```bash
    git clone https://github.com/Neison1301/Polyclinic-Appointment-App.git
    ```
    Este comando descargará todo el código del proyecto a una nueva carpeta llamada `Polyclinic-Appointment-App` en tu directorio actual.

4.  **Entra a la carpeta del proyecto:**

    ```bash
    cd Polyclinic-Appointment-App
    ```
    Ahora estás dentro de la carpeta principal del proyecto.

---

## 3. ⚙️ Configuración del Backend (Java/Spring Boot)

El backend es la parte del sistema que maneja la lógica de negocio y la interacción con la base de datos.

1.  **Navega a la carpeta del backend:**

    ```bash
    cd backend
    ```

2.  **Configura la Base de Datos MySQL:**
    * **Crea la Base de Datos:** Abre tu cliente MySQL (MySQL Workbench, o la terminal de MySQL) y ejecuta el siguiente comando SQL:
        ```sql
        CREATE DATABASE citas_medicas_db;
        ```
    * **Configura las Credenciales en el Backend:**
        * Abre el archivo `src/main/resources/application.properties` dentro de la carpeta `backend` con un editor de texto (VS Code, Sublime Text, Notepad++, etc.).
        * Modifica las siguientes líneas con el **usuario y contraseña de tu servidor MySQL** que configuraste en el paso 1.5:

        ```properties
        # Configuración de la Base de Datos MySQL
        spring.datasource.url=jdbc:mysql://localhost:3306/citas_medicas_db?useSSL=false&serverTimezone=UTC
        spring.datasource.username=tu_usuario_db  # <-- ¡CAMBIA ESTO!
        spring.datasource.password=tu_contraseña_db # <-- ¡CAMBIA ESTO!
        spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

        # Configuración de JPA/Hibernate (NO CAMBIAR ESTO a menos que sepas lo que haces)
        spring.jpa.hibernate.ddl-auto=update
        spring.jpa.show-sql=true
        spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
        ```
        * **Guarda los cambios** en el archivo `application.properties`.

3.  **Instala las dependencias y construye el proyecto del Backend:**

    ```bash
    mvn clean install
    ```
    Este comando descargará todas las librerías de Java necesarias y compilará el proyecto backend. Esto puede tardar unos minutos la primera vez.

4.  **Inicia el servidor Backend:**

    ```bash
    mvn spring-boot:run
    ```
    Verás mensajes en la terminal indicando que Spring Boot se está iniciando. Debería indicar que el servidor está corriendo en el puerto `8080`, por ejemplo: `Started Application in X.XXX seconds (JVM running for Y.YYY)`.

    **¡Deja esta terminal abierta!** El backend debe estar funcionando para que el frontend pueda conectarse.

---

## 4. 🌐 Configuración del Frontend (React + Vite)

El frontend es la interfaz de usuario con la que interactúan los usuarios.

1.  **Abre una _NUEVA_ Terminal o Símbolo del Sistema / PowerShell.**
    * **Importante:** No cierres la terminal del backend que está en ejecución.

2.  **Navega a la carpeta del frontend:**
    Desde la raíz de tu carpeta `Polyclinic-Appointment-App` (donde clonaste el proyecto), navega al subdirectorio `frontend`:

    ```bash
    cd ../frontend
    ```
    *(Si abriste la nueva terminal en la misma carpeta raíz del proyecto, solo necesitas `cd frontend`)*

3.  **Instala las dependencias de Node.js:**

    ```bash
    npm install
    # Si prefieres usar Yarn, puedes usar:
    # yarn install
    ```
    Este comando descargará todas las librerías de JavaScript necesarias para el frontend. Esto puede tardar unos minutos la primera vez.

4.  **Inicia la aplicación Frontend:**

    ```bash
    npm run dev
    # Si usaste Yarn, puedes usar:
    # yarn dev
    ```
    Esto iniciará el servidor de desarrollo de Vite para el frontend. Verás un mensaje similar a: `Local: http://localhost:5173/`.

---

## 5. 🎉 ¡Explora la Aplicación!

Una vez que ambos servidores (el backend en el puerto `8080` y el frontend en el puerto `5173`) estén ejecutándose, la aplicación web completa debería abrirse automáticamente en tu navegador o podrás acceder a ella manualmente:

* **Abre tu navegador web y visita:** `http://localhost:5173/`

Ahora tienes el sistema de citas médicas "Polyclinic-Appointment-App" funcionando en tu máquina.

---

## 💡 Próximos Pasos y Notas Adicionales:

* **Primer Acceso:** Puede que necesites crear un administrador o un usuario de prueba directamente en la base de datos o a través de alguna API si el proyecto no tiene una ruta de registro inicial para administradores. Revisa el `README.md` original del proyecto si hay instrucciones para el primer acceso.
* **Actualizar el Proyecto:** Si el proyecto en GitHub recibe actualizaciones, puedes descargar los últimos cambios desde la raíz de tu proyecto con:
    ```bash
    cd Polyclinic-Appointment-App
    git pull
    ```
    Después de `git pull`, es posible que necesites ejecutar `mvn clean install` en el `backend` y `npm install` (o `yarn install`) en el `frontend` nuevamente si hubo cambios en las dependencias.

* **Problemas:** Si encuentras errores, revisa los mensajes en ambas terminales (backend y frontend) para pistas.

---

## 👨‍💻 Guía para Colaboradores – Sistema de Citas Médicas

## ✅ Requisitos previos

* Tener una cuenta en [GitHub](https://github.com).
* Haber sido agregado como colaborador al repositorio del proyecto.
* Tener `git` instalado en tu computadora.

---

## ✨ 1. Clona el repositorio

---

## 🌱 2. Crea una nueva rama para tu tarea

Usa un nombre descriptivo de lo que vas a hacer, por ejemplo:

```bash
git checkout -b feature/nombre-de-la-funcionalidad
```

---

## 💻 3. Realiza tus cambios

Ahora que estás en tu nueva rama, puedes empezar a trabajar en tu tarea asignada. Edita los archivos necesarios (código, interfaz de usuario, configuración de base de datos, etc.) para implementar la funcionalidad o corregir el problema.

---

## 📌 4. Guarda los cambios y haz commit

```Añade los archivos modificados: Esto "prepara" los archivos para ser incluidos en el próximo commit.
git add .
```
```
git status
```
```
git commit -m "Agregada funcionalidad de registro de pacientes"
```
El punto (.) añade todos los archivos nuevos y modificados en el directorio actual y subdirectorios. Si solo quieres añadir archivos específicos, usa git add nombre-del-archivo.
Haz el commit: Crea un "punto de guardado" en tu historial de Git con un mensaje que describa los cambios.

> ✨ Usa mensajes de commit claros y breves. Ejemplo: `"Fix: validación en formulario de citas"`.

---

## 🚀 5. Sube tu rama al repositorio remoto
Después de hacer commits localmente, necesitas subir tu rama al repositorio remoto (GitHub) para que otros colaboradores y el administrador puedan ver tus cambios.
```bash
git push origin feature/nombre-de-la-funcionalidad
```

---

## 🔀 6. Crea un Pull Request (PR)

* Ingresa al repositorio en GitHub.
* Haz clic en **"Compare & pull request"**.
* Escribe un resumen de los cambios realizados.
* Envía el PR para revisión.

---

## 🛑 7. Espera confirmación del administrador

> ⚠️ **No hagas `merge` directamente**. El administrador del proyecto revisará y aprobará los cambios antes de integrarlos a la rama `main`.

---

## 📅 8. Mantén tu rama actualizada

Antes de comenzar una nueva tarea o para evitar conflictos:

```bash
git checkout main
```
```
git pull origin main
```

---

## ✅ Buenas prácticas

* Haz `pull` regularmente.
* Crea ramas por cada tarea.
* Atiende los comentarios en los PR.
* No subas archivos innecesarios (por ejemplo: `.class`, `.log`, `target/`, etc.).
