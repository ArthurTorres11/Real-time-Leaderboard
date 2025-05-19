package com.br.rankup.backend.Controller;

import com.br.rankup.backend.DTO.LeaderboardDTO;
import com.br.rankup.backend.Model.User;
import com.br.rankup.backend.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/leaderboard")
public class LeaderboardController {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<LeaderboardDTO> getLeaderboard() {
        Set<ZSetOperations.TypedTuple<Object>> ranking = redisTemplate.opsForZSet()
                .reverseRangeWithScores("rankings", 0, -1);

        List<LeaderboardDTO> leaderboard = new ArrayList<>();
        int position = 1;

        if (ranking != null) {
            for (ZSetOperations.TypedTuple<Object> entry : ranking) {
                Long userId = Long.valueOf(entry.getValue().toString());
                int score = entry.getScore().intValue();

                User user = userRepository.findById(userId.intValue()).orElse(null);
                if (user != null) {
                    leaderboard.add(new LeaderboardDTO(user.getName(), score, position++));
                }
            }
        }

        return leaderboard;
    }
}
