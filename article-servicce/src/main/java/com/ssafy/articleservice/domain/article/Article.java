package com.ssafy.articleservice.domain.article;

import com.ssafy.articleservice.domain.TimeBaseEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Article extends TimeBaseEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id")
    private Long id;

    private String title;
    private String subTitle;
    private String writer;
    private LocalDateTime publishedDate;
    private String content;
    private String thumbnailImg;
    private Integer hit;
    private String htmlContent;

    @Builder
    public Article(String title, String subTitle, String writer, LocalDateTime publishedDate, String content, String thumbnailImg, Integer hit, String htmlContent) {
        this.title = title;
        this.subTitle = subTitle;
        this.writer = writer;
        this.publishedDate = publishedDate;
        this.content = content;
        this.thumbnailImg = thumbnailImg;
        this.hit = hit;
        this.htmlContent = htmlContent;
    }
}
