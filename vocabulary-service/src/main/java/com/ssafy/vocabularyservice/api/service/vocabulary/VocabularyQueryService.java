package com.ssafy.vocabularyservice.api.service.vocabulary;

import com.ssafy.vocabularyservice.api.controller.vocabulary.response.CheckVocabularyResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.VocabularyResponse;
import com.ssafy.vocabularyservice.api.controller.vocabulary.response.WordClientResponse;
import com.ssafy.vocabularyservice.domain.vocabulary.repository.VocabularyQueryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class VocabularyQueryService {

    private final VocabularyQueryRepository vocabularyQueryRepository;

    public Page<VocabularyResponse> getMyVocabulary(String memberKey, Boolean check, Pageable pageable) {
        List<VocabularyResponse> content = vocabularyQueryRepository.findByMemberKey(memberKey, check, pageable);

        long totalCount = vocabularyQueryRepository.getTotalCountByMemberKey(memberKey);

        return new PageImpl<>(content, pageable, totalCount);
    }

    public CheckVocabularyResponse getMyVocabularyWithCheck(String memberKey) {
        long checkedCount = vocabularyQueryRepository.getCheckCountByMemberKey(memberKey);
        long totalCount = vocabularyQueryRepository.getTotalCountByMemberKey(memberKey);

        return CheckVocabularyResponse.builder()
            .checkedCount(checkedCount)
            .totalCount(totalCount)
            .build();
    }

    public List<WordClientResponse> getMyVocabularyClient(String memberKey) {
        List<Long> ids = vocabularyQueryRepository.findIdClientByMemberKey(memberKey);
        if (ids.size() < 10) {
            return new ArrayList<>();
        }

        Collections.shuffle(ids);

        List<Long> options = ids.subList(0, 10);

        return vocabularyQueryRepository.findClientByMemberKey(options);
    }
}
