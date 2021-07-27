package com.example.restservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {
    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong(); //way to set id

    //greeting?name=Dan -> Hello Dan
    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name",defaultValue = "World")String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
}
