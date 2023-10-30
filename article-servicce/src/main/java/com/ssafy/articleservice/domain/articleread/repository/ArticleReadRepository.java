package com.ssafy.articleservice.domain.articleread.repository;

import com.ssafy.articleservice.domain.articleread.ArticleRead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleReadRepository extends JpaRepository<ArticleRead, Long> {

    @Query("select ar.id from ArticleRead ar where ar.memberKey=:memberKey and ar.article.id=:articleId")
    Optional<Long> findByMemberKeyAndArticleId(@Param("memberKey") String memberKey, @Param("articleId") Long articleId);
}
