package com.ssafy.vocabularyservice.domain.vocabulary.repository;

import com.ssafy.vocabularyservice.domain.vocabulary.Vocabulary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 단어장 Data JPA 저장소
 * @author 임우택
 */
@Repository
public interface VocabularyRepository extends JpaRepository<Vocabulary, Long> {
}
