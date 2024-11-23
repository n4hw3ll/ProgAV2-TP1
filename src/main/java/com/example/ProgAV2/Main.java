package com.example.ProgAV2;

import Intermedio.Nodo;
import Intermedio.Servicio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;

@SpringBootApplication(scanBasePackages = {"com.Intermedio.Servicio", "Intermedio"})
@RestController
@RequestMapping("/queue")
public class Main {

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private final Servicio servicio;

    @Autowired
    public Main(Servicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/push")
    public ResponseEntity<Respuesta> push(@RequestBody Nodo params) {
        Nodo nodo1 = new Nodo(
                params.getUserId(),
                params.getContentType(),
                params.getMessage(),
                params.getTo(),
                params.getReplyTo(),
                params.getExpiryTime(),
                params.getState(),
                params.getPriority());
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new Respuesta("Mensaje nuevo fue agregado", this.servicio.addNode(nodo1)));
    }

    @GetMapping("/pop")
    public ResponseEntity<Respuesta> pop() {

        if (this.servicio.getQueue().isEmpty())
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Respuesta("Error: La Cola esta vacia", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Respuesta("Mensaje entregado con exito", this.servicio.removeNode()));
    }

    @GetMapping
    public ResponseEntity<Respuesta> getQueue() {

        if (this.servicio.getQueue().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new Respuesta("Error: La Cola esta vacia", null));
        }

        return ResponseEntity.status(HttpStatus.OK)
                .body(new Respuesta("Mensajes encolados", this.servicio.getQueue()));
    }
}