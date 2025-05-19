package com.br.rankup.backend.Controller;

import com.br.rankup.backend.DTO.ScoreDTO;
import com.br.rankup.backend.Model.Score;
import com.br.rankup.backend.Model.User;
import com.br.rankup.backend.Repository.ScoreRepository;
import com.br.rankup.backend.Repository.UserRepository;
import com.br.rankup.backend.Service.ScoreService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/scores")
public class ScoreController {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private ScoreService scoreService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping()
    public List<Score> obterScores(){
        return scoreRepository.findAll();
    }

    @PostMapping()
    public ResponseEntity<?> cadastrarScore(@RequestBody @Valid ScoreDTO dto){
        scoreService.adicionarScore(dto);
        return ResponseEntity.ok("Score cadastrado com sucesso");
    }
    @PostMapping("/atualizar-rankings")
    public ResponseEntity<String> atualizarTodosRanks() {
        scoreService.atualizarRankings();
        return ResponseEntity.ok("Rankings atualizados com sucesso.");
    }
}
