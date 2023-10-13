package com.example.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JogosEHerois {
    private List<Jogo> jogos;
    private List<Heroi> herois;
    @JsonProperty("tempo_em_milisegundos")
    private long tempoEmMiliSegundos;
}
