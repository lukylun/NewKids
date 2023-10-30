package com.ssafy.keywordservice.domain.keyword.repository;

import com.ssafy.keywordservice.domain.keyword.Keyword;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Long> {

    @Query("select k.id from Keyword k where k.word = :word")
    Optional<Long> existByWord(@Param("word") String word);

    Optional<Keyword> findByWord(String word);
}
