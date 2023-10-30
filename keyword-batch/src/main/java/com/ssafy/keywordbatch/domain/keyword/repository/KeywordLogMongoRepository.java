package com.ssafy.keywordbatch.domain.keyword.repository;

import com.ssafy.keywordbatch.domain.keyword.KeywordLogDocs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeywordLogMongoRepository extends MongoRepository<KeywordLogDocs, String> {
}
