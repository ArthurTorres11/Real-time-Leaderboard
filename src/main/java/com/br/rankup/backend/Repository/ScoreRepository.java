package com.br.rankup.backend.Repository;

import com.br.rankup.backend.Model.Score;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreRepository extends JpaRepository<Score, Long> {
    List<Score> findTop5ByOrderByTimestampDesc();
    List<Score> findTop5ByUserIdOrderByTimestampDesc(Long userId);

}
