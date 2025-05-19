package com.br.rankup.backend.Service;

import com.br.rankup.backend.DTO.ScoreDTO;
import com.br.rankup.backend.Model.Enums.Rank;
import com.br.rankup.backend.Model.Score;
import com.br.rankup.backend.Model.User;
import com.br.rankup.backend.Repository.ScoreRepository;
import com.br.rankup.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ScoreService {

    @Autowired
    private ScoreRepository scoreRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void atualizarRankings() {
        List<User> usuariosOrdenados = userRepository.findAll()
                .stream()
                .sorted((u1, u2) -> Integer.compare(u2.getScore(), u1.getScore()))
                .toList();

        for (int i = 0; i < usuariosOrdenados.size(); i++) {
            User user = usuariosOrdenados.get(i);
            int posicao = i + 1;
            user.setPosition(posicao);

            if (i == 0) {
                user.setRank(Rank.TOP1);
            } else if (i <= 9) {
                user.setRank(Rank.DIAMANTE);
            } else if (i <= 49) {
                user.setRank(Rank.OURO);
            } else if (i <= 99) {
                user.setRank(Rank.PRATA);
            } else {
                user.setRank(Rank.BRONZE);
            }

            userRepository.save(user);
        }
    }

    public void adicionarScore(ScoreDTO dto){
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));

        int somaNovoScore = user.getScore() + dto.getScore();
        user.setScore(somaNovoScore);
        userRepository.save(user);
        atualizarRankings();

        Score score = new Score();
        score.setScore(dto.getScore());
        score.setTimestamp(LocalDateTime.now());
        score.setUser(user);
        scoreRepository.save(score);

        redisTemplate.opsForZSet().add("rankings", user.getName(), user.getScore());

        Long posicao = redisTemplate.opsForZSet().reverseRank("rankings", user.getId());

        if(posicao!=null){
            user.setPosition(posicao.intValue()+1);

            userRepository.save(user);
        }
    }
}
