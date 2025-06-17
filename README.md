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

### 💻 Tecnologías Utilizadas

| Tecnología     | Rol                                |
|----------------|-------------------------------------|
| **Java 21**    | Lógica de negocio (back-end)        |
| **Spring Boot**| Framework backend y API REST        |
| **MySQL**      | Base de datos relacional            |
| **HTML/CSS/JS**| Maquetado y estilos responsivos     |
| **MVC**        | Arquitectura del sistema            |
| **DTO**    | Patrón para gestión de datos        |
| **SOLID**      | Principios de diseño de software    |


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

--

# 🚀 Guía Completa de Configuración: Polyclinic-Appointment-App

Esta guía te llevará paso a paso a través de todo el proceso para configurar y ejecutar el sistema de citas médicas "Polyclinic-Appointment-App" en tu máquina local, asumiendo que partes desde cero.

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


4.  **MySQL Server:**
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


## 💡 Próximos Pasos y Notas Adicionales:

* **Actualizar el Proyecto:** Si el proyecto en GitHub recibe actualizaciones, puedes descargar los últimos cambios desde la raíz de tu proyecto con:
    ```bash
    cd Polyclinic-Appointment-App
    git pull
    ```
    Después de `git pull`, es posible que necesites ejecutar `mvn clean install` en el `backend` y `npm install` (o `yarn install`) en el `frontend` nuevamente si hubo cambios en las dependencias.

* **Problemas:** Si encuentras errores, revisa los mensajes en ambas terminales (backend ) para pistas.

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
