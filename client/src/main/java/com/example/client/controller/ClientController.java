package com.example.client.controller;

import com.example.client.model.Heroi;
import com.example.client.model.Jogo;
import com.example.client.model.JogosEHerois;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/client")
public class ClientController {

    private final String ROTA_JOGOS = "http://localhost:8080/jogos";
    private final String ROTA_HEROIS = "http://localhost:8081/herois";

    private final WebClient webClient = WebClient.create();

    @GetMapping
    public ResponseEntity<JogosEHerois> getPokemonDetails(String pokemonName) throws ExecutionException, InterruptedException {
        long inicio = System.currentTimeMillis();
        CompletableFuture<List<Jogo>> futureJogos = getJogos();

        CompletableFuture<List<Heroi>> futureHerois = getHerois();

        CompletableFuture.allOf(futureJogos, futureHerois).join();
        long fim = System.currentTimeMillis();

        long tempoDecorrido = fim - inicio;

        JogosEHerois jogosEHerois = new JogosEHerois(futureJogos.get(), futureHerois.get(), tempoDecorrido);
        return ResponseEntity.ok(jogosEHerois);
    }

    @Async
    public CompletableFuture<List<Heroi>> getHerois() {
        Heroi[] herois = webClient.get()
                .uri(ROTA_HEROIS)
                .retrieve()
                .bodyToMono(Heroi[].class)
                .block();
        assert herois != null;
        List<Heroi> listaHerois = List.of(herois);
        return CompletableFuture.completedFuture(listaHerois);
    }

    @Async
    public CompletableFuture<List<Jogo>> getJogos() {
        Jogo[] jogos = webClient.get()
                .uri(ROTA_JOGOS)
                .retrieve()
                .bodyToMono(Jogo[].class)
                .block();
        assert jogos != null;
        List<Jogo> listaJogos = List.of(jogos);
        return CompletableFuture.completedFuture(listaJogos);
    }

}
