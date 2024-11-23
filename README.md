# ProgAV2-TP1
Replica del protocolo AMQP hecho en Lenguaje Java

Para iniciar el proyecto hay que pararse en el directorio que se encuentra el mvnw.cmd y ejecutar la siguiente linea
```
"./mvnw spring-boot:run --quiet"
```
Los Endpoints son los siguientes:
- GET http://localhost:8080/queue (obtiene la cola en su estado actual e imprime los datos)
- POST http://localhost:8080/queue/push (Agrega un Body mediante el verbo POST a la cola)
- DELETE http://localhost:8080/queue/pop (Recive el ultimo mensaje de la cola y lo elimina de la misma (Metodo FIFO))

Cuerpo del Body que espera como respuesta el endpoint "/queue/push/":
```json
    {
        "message":"hello_world",
        "to": "world",
        "state": "vigente",
        "priority": 100,
        "userId": "001",
        "contentType": "json",
        "replyTo": "god",
        "expiryTime": "1000"
    }
```
