package com.basoft.games.services;

import com.basoft.games.dto.GameDTO;
import com.basoft.games.dto.GameMinDTO;
import com.basoft.games.entities.Game;
import com.basoft.games.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameService {

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public GameDTO findById( Long gameId) {
        Game game = gameRepository.findById(gameId).get();
        return new GameDTO(game);
    }

    @Transactional(readOnly = true)
    public List<GameMinDTO> findAll() {
        return gameRepository.findAll().stream().map(GameMinDTO::new).toList();
    }
}
