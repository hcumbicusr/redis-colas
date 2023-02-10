***
`Usando contenedor para REDIS`
# Para levantar contenedor de Redis
> docker run --name redis -d -p 6379:6379 redis:5.0.4

# Para visualizar db redis con redis-commander
> npm install -g redis-commander
> redis-commander
`Abrir el navegador: http://127.0.0.1:8081`

***

# Para compilar projecto java gradle
`dentro de la carpeta del proyecto: "Redis"`
> ./gradlew jar

# Para ejecutar como productor de items de lista
> java -jar build/libs/Redis.jar producer

# Para ejecutar como consumidor de items de lista
> java -jar build/libs/Redis.jar consumer
