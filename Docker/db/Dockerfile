
# Usa la imagen oficial de PostgreSQL
FROM postgres:latest

# Establece las variables de entorno necesarias para la base de datos
ENV POSTGRES_DB=dbpayroll
ENV POSTGRES_USER=postgres
ENV POSTGRES_PASSWORD=1234

# Exponer el puerto 5432, que es el puerto de PostgreSQL
EXPOSE 5432

# Copiar el archivo init.sql para inicializar la base de datos
##COPY ./init.sql /docker-entrypoint-initdb.d/
#COPY ./pg_hba.conf /var/lib/postgresql/data/pg_hba.conf