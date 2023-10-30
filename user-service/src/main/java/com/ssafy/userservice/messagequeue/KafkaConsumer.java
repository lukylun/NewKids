package com.ssafy.userservice.messagequeue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.userservice.domain.member.Member;
import com.ssafy.userservice.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Slf4j
@Transactional
public class KafkaConsumer {

    private final MemberRepository memberRepository;

    @KafkaListener(topics = "exp-member-topic")
    public void updateExp(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        Map<Object, Object> map = new HashMap<>();
        ObjectMapper mapper = new ObjectMapper();
        try {
            map = mapper.readValue(kafkaMessage, new TypeReference<Map<Object, Object>>() {
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        String memberKey = (String) map.get("memberKey");

        Member member = getMemberEntity(memberKey);

        Integer exp = (Integer) map.get("exp");

        member.increaseExp(exp);
    }

    private Member getMemberEntity(String memberKey) {
        Optional<Member> findMember = memberRepository.findByMemberKey(memberKey);
        if (findMember.isEmpty()) {
            throw new NoSuchElementException("등록되지 않은 사용자입니다.");
        }
        return findMember.get();
    }
}
