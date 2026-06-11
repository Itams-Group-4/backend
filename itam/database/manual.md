# Base de Datos ITAM - Docker PostgreSQL

## Requisitos

* Docker Engine
* Docker Compose

Verificar instalación:

```bash
docker --version
docker compose version
```

---

## Estructura del Proyecto

```text
ITAM/
│
├── docker-compose.yml
│
└── INIT/
    ├── 01_tables.sql
    ├── 02_configuration.sql
    └── 03_inserts.sql
```

---

## Configuración Docker

Archivo `docker-compose.yml`:

```yaml
services:
  postgres:
    image: postgres:16
    container_name: itam_postgres

    restart: unless-stopped

    environment:
      POSTGRES_DB: itam_db
      POSTGRES_USER: pones_tu_usuario
      POSTGRES_PASSWORD: pones_tu_contraseña

    ports:
      - "5432:5432"

    volumes:
      - postgres_data:/var/lib/postgresql/data
      - ./init:/docker-entrypoint-initdb.d

volumes:
  postgres_data:
```

---

## Levantar la Base de Datos

Desde la raíz del proyecto ejecutar:

```bash
sudo docker compose up -d
```

Verificar que el contenedor se encuentre activo:

```bash
sudo docker ps
```

Salida esperada:

```text
CONTAINER ID   IMAGE         NAMES
xxxxxxxxxxxx   postgres:17   itam_postgres
```

---

## Revisar Logs

Para verificar que los scripts SQL fueron ejecutados correctamente:

```bash
sudo docker logs itam_postgres
```

Se debería de ver:

```text
running /docker-entrypoint-initdb.d/01_tables.sql
running /docker-entrypoint-initdb.d/02_configuration.sql
running /docker-entrypoint-initdb.d/03_inserts.sql

database system is ready to accept connections
```

---

## Conectarse a PostgreSQL

Ingresar al contenedor:

```bash
sudo docker exec -it itam_postgres psql -U admin -d itam_db
```

---

`

## Verificar Datos Iniciales

Consultar usuarios ( la contraseña de los usuarios es password ):

```sql
SELECT * FROM users;
```

Contar registros:

```sql
SELECT COUNT(*) FROM users;
```

---

## Reiniciar la Base de Datos

Detener contenedor:

```bash
sudo docker compose down
```

---

## Eliminar Base de Datos y Recrearla

Si se modifican los scripts SQL y se desea reconstruir completamente la base:

```bash
sudo docker compose down -v
sudo docker compose up -d
```

El parámetro `-v` elimina el volumen persistente y fuerza la ejecución nuevamente de:

1. 01_tables.sql
2. 02_configuration.sql
3. 03_inserts.sql

---

## Comandos Útiles

Ver contenedores activos:

```bash
sudo docker ps
```

Ver todos los contenedores:

```bash
sudo docker ps -a
```
```

Ingresar al contenedor:

```bash
sudo docker exec -it itam_postgres bash
```
```

Detener contenedor:

```bash
sudo docker stop itam_postgres
```

Iniciar contenedor:

```bash
sudo docker start itam_postgres
```
