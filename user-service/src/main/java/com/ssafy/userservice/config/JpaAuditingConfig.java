package com.ssafy.userservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * JPA 환경 설정
 *
 * @author 임우택
 */
@EnableJpaAuditing
@Configuration
public class JpaAuditingConfig {
}
