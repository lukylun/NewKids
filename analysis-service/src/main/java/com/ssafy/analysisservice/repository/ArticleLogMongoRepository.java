package com.ssafy.analysisservice.repository;

import com.ssafy.analysisservice.domain.ArticleLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleLogMongoRepository extends MongoRepository<ArticleLog, String> {
}
