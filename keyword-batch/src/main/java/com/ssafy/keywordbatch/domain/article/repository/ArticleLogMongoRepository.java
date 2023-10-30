package com.ssafy.keywordbatch.domain.article.repository;

import com.ssafy.keywordbatch.domain.article.ArticleLogDocs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleLogMongoRepository extends MongoRepository<ArticleLogDocs, String> {
}
