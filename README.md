# Location Service

Este es un microservicio hecho con Spring Boot para gestionar las ubicaciones (ciudades y departamentos) en una plataforma inmobiliaria. El servicio se conecta a una base de datos MySQL y tienes dos formas de configurarla: usando Docker o con un archivo `.sql`.

---

## Requisitos

- JDK 17 o superior
- Docker (opcional, si prefieres usar contenedores)
- MySQL (si no usas Docker)

---

## Configuración de la Base de Datos

Tienes dos opciones:

### Opción 1: Usar Docker

1. Asegúrate de tener Docker instalado.
2. Desde la raíz del proyecto, ve a la carpeta de Docker:

    ```bash
    cd infrastructure/docker
    ```

3. Levanta el contenedor con MySQL:

    ```bash
    docker-compose up -d
    ```

4. El contenedor se llamará `location-db` y MySQL quedará expuesto en el puerto `3306`. Verifica que esté corriendo:

    ```bash
    docker ps
    ```

5. Copia el script SQL dentro del contenedor y ejecútalo:

    ```bash
    docker cp ../database/init.sql location-db:/init.sql
    docker exec -it location-db mysql -u root -p < /init.sql
    ```

   > Contraseña: `root` (tal como está definida en el `docker‑compose.yml`)

### Opción 2: Usar el archivo `.sql` manualmente

1. Crea una base de datos `hogar360_location` en tu servidor MySQL local.
2. Ejecuta el script SQL que se encuentra en:

    ```
    infrastructure/database/init.sql
    ```

3. En tu cliente MySQL:

    ```sql
    SOURCE /ruta/a/your/project/infrastructure/database/init.sql;
    ```

4. Configura los datos de conexión en `src/main/resources/application.properties`:

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/hogar360_location
    spring.datasource.username=root
    spring.datasource.password=root
    ```

---

## Configuración del Proyecto

1. Clona el repositorio y entra en la rama de la historia de usuario 3:

    ```bash
    git clone https://github.com/Andreastephgm/location-service.git
    cd location-service
    git checkout feat/HU03-list-locations
    ```

2. Ajusta `application.properties` según la opción de base de datos que elegiste.
3. Si usas Docker, asegúrate de que `location-db` esté corriendo (`docker-compose up -d`).

---

## Iniciar el Proyecto

Para arrancar el microservicio:

```bash
./gradlew bootRun
