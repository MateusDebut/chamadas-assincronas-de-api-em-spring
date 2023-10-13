package com.example.server.controller;

import com.example.server.model.Heroi;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/herois")
public class HeroiController {
    private List<Heroi> herois = new ArrayList<>();

    public HeroiController(){
        herois.add(new Heroi(1, "Lyn"));
        herois.add(new Heroi(2, "Elywood"));
        herois.add(new Heroi(3, "Hector"));
    }

    @GetMapping
    public ResponseEntity<List<Heroi>> getHerois() throws InterruptedException {
        Random random = new Random();
        int tempoDeEspera = 3000;
        Thread.sleep(tempoDeEspera);
        return ResponseEntity.ok(this.herois);
    }
}
