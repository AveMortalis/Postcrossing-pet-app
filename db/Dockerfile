FROM mysql:8.0.21
ENV MYSQL_USER=root
ENV MYSQL_ROOT_PASSWORD=root
ENV MYSQL_DATABASE=postcrossingapp
ADD 0_schema.sql /docker-entrypoint-initdb.d
ADD 1_data.sql /docker-entrypoint-initdb.d
EXPOSE 3306