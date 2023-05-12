package com.basoft.games.services;

import com.basoft.games.dto.GameListDTO;
import com.basoft.games.dto.GameMinDTO;
import com.basoft.games.entities.Game;
import com.basoft.games.projections.GameMinProjection;
import com.basoft.games.repositories.GameListRepository;
import com.basoft.games.repositories.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class GameListService {

    @Autowired
    private GameListRepository gameListRepository;

    @Autowired
    private GameRepository gameRepository;

    @Transactional(readOnly = true)
    public List<GameListDTO> findAll() {
        return gameListRepository.findAll().stream().map(GameListDTO::new).toList();
    }

    @Transactional
    public void move(Long listId, int sourceIndex, int destinationIndex) {

        List<GameMinProjection> list = gameRepository.searchByList(listId);

         GameMinProjection movedGame = list.remove(sourceIndex);
         list.add(destinationIndex, movedGame);

         int min = Math.min(sourceIndex, destinationIndex);
         int max = Math.max(sourceIndex, destinationIndex);

         for (int i = min; i <= max; i++) {
             gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
         }
    }
}
