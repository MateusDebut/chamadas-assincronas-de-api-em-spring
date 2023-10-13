package com.example.jogos.service;

import com.example.jogos.model.Jogo;
import com.example.jogos.repository.JogoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JogoService {

    @Autowired
    private JogoRepository jogoRepository;

    public List<Jogo> getAll() {
        return jogoRepository.findAll();
    }

    public Jogo save(Jogo jogo) {
        return jogoRepository.save(jogo);
    }

    public Jogo favoritar(int idJogo) {
        Optional<Jogo> optionalJogo = jogoRepository.findById(idJogo);

        if (optionalJogo.isPresent()) {
            Jogo jogo = optionalJogo.get();
            jogo.setEstaFavoritado(!jogo.isEstaFavoritado());
            return jogoRepository.save(jogo);
        }
        return null;
    }

}
