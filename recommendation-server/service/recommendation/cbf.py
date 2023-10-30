import logging as log
import time

import numpy as np
from scipy.sparse import csr_matrix
from sklearn.metrics.pairwise import cosine_similarity

from dao.article_dao import get_article_indices
from dao.tf_idf_weights_dao import get_article_tf_idf, get_article_tf_idf_by_article_id

# init logging
log.basicConfig(level=log.DEBUG)


def get_tfidf_matrix(article_ids, current_id):
    """
    기사 식별키 리스트에 해당하는 TF-IDF 가중치 벡터 값 조회
    
    :param article_ids: 기사 식별키 리스트 
    :return: 기사 식별키 리스트에 해당하는 TF-IDF 가중치 벡터 리스트
    """

    tf_idf_matrix = get_article_tf_idf(article_ids)
    current_tf_idf_matrix = get_article_tf_idf_by_article_id(current_id)

    # current_tf_idf_matrix의 행만 선택하여 병합
    tf_idf_matrix.extend(current_tf_idf_matrix)

    num_rows = max(data['article_id'] for data in tf_idf_matrix) + 1
    num_cols = max(data['keyword_vector'] for data in tf_idf_matrix) + 1

    rows = np.array([data['article_id'] for data in tf_idf_matrix], dtype=np.int32)
    cols = np.array([data['keyword_vector'] for data in tf_idf_matrix], dtype=np.int32)
    values = np.array([data['weight'] for data in tf_idf_matrix], dtype=np.float64)

    return csr_matrix((values, (rows, cols)), dtype=np.float64, shape=(num_rows, num_cols))


def get_recommend_ids(article_id):
    """
    코사인 유사도를 사용한 기사 추천

    :param: article_id : 현재 읽고 있는 기사 식별키
    :return: 유사도가 높은 기사 제목 리스트
    """
    log.debug("cbf#getRecommendation called")

    # 1. 기사 PK 리스트 조회
    start = time.time()
    article_indices = get_article_indices()

    article_ids = [id[0] for id in article_indices]
    end = time.time()
    log.debug(f"article read time: {end-start: .5f}")

    # 2. 기사 PK 리스트에 해당하는 TF-IDF 가중치 값만 조회 후 csr_matrix로 변환
    start = time.time()
    tf_idf_matrix = get_tfidf_matrix(article_ids, article_id)
    end = time.time()
    log.debug(f"tfidf create time: {end-start: .5f}")

    # 3. TF-IDF 가중치를 사용해서 코사인 유사도 계산
    start = time.time()
    cosine_sim = cosine_similarity(tf_idf_matrix.getrow(article_id), tf_idf_matrix)
    end = time.time()
    log.debug(f"cosine running time: {end-start: .5f}")

    # 4. 코사인 유사도 리스트 정렬 후 상위 N 개의 추천 결과 리스트 반환
    sim_scores = list(enumerate(cosine_sim[0]))
    sim_scores = sorted(sim_scores, key=lambda x: x[1], reverse=True)
    sim_scores = sim_scores[1:]
    articles_indices = [i[0] for i in sim_scores]

    return articles_indices[:10]
