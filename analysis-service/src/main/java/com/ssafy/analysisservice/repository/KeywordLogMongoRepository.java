package com.ssafy.analysisservice.repository;

import com.ssafy.analysisservice.domain.KeywordLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface KeywordLogMongoRepository extends MongoRepository<KeywordLog, String> {

    List<KeywordLog> findByCreatedDateBetween(LocalDateTime from, LocalDateTime to);
}
