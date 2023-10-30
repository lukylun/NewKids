package com.ssafy.vocabularyservice.domain.word.repository;

import com.ssafy.vocabularyservice.domain.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 단어 Data JPA 저장소
 *
 * @author 임우택
 */
@Repository
public interface WordRepository extends JpaRepository<Word, Long> {

    /**
     * 단어키로 단어 엔티티 조회
     * @param wordKey 조회할 단어키
     * @return 조회된 단어 엔티티
     */
    Optional<Word> findByWordKey(String wordKey);
}
