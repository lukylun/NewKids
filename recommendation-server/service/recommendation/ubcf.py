import logging
import random
import time
from collections import defaultdict

import pandas as pd
from sklearn.feature_extraction.text import TfidfVectorizer
from sklearn.metrics.pairwise import cosine_similarity

from dao.article_dao import get_article_indices_by_member_key, get_articles
from dao.keyword_dao import get_member_keyword_all, get_member_keyword_by_member_key
from dao.member_dao import get_member_id_by_member_key


def get_members_interests(age):
    """
    회원별 관심 키워드 조회 (현재는 더미데이터 생성 함수)
    추후 쿼리 호출 함수로 변경해야함

    :return: 회원별 관심 키워드 리스트
    """
    member_keyword_all = get_member_keyword_all(age)
    # print(type(member_keyword_all))
    # print(member_keyword_all)

    df = pd.DataFrame(member_keyword_all)

    grouped = df.groupby('member_key')
    result_list = []

    for member_key, group in grouped:
        keywords_list = group[['keyword_id', 'word']].to_dict(orient='records')
        result_list.append({'member_key': member_key, 'keywords': keywords_list})

    return result_list


def get_members_tfidf_matrix(members_interests):
    """
    회원별 관심 키워드 TF-IDF 가중치 벡터화

    :param members_interests: 회원별 관심 키워드 리스트
    :return: 회원별 관심 키워드 TF-IDF 벡터
    """
    # TF-IDF 벡터화를 위해 관심 키워드를 텍스트로 변환
    member_interest_texts = [" ".join([interest['word'] for interest in member['keywords']]) for member in
                             members_interests]

    # TF-IDF 벡터화
    tfidf_vectorizer = TfidfVectorizer()

    return tfidf_vectorizer.fit_transform(member_interest_texts)


def get_ubcf_recommendations(member_key):
    """
    사용자 기반 협업 필터링 추천 - 사용자별 관심 키워드를 통한 추천

    :param member_key: 회원 고유값
    :return: 추천 키워드 리스트
    """
    # 회원 데이터 조회
    start = time.time()
    members_interests = get_members_interests()
    end = time.time()
    logging.debug(f"init running time: {end - start: .5f}")

    members_tfidf_matrix = get_members_tfidf_matrix(members_interests)

    # 코사인 유사도 계산 (사용자 간의 유사도)
    member_similarity = cosine_similarity(members_tfidf_matrix, members_tfidf_matrix)

    # 특정 사용자 선택 (예시로 1번 사용자 선택)
    target_member_idx = get_member_id_by_member_key(member_key)[0] - 1

    # 유사한 사용자 선택 (코사인 유사도가 가장 높은 상위 N명)
    num_neighbors = 5
    similar_members_indices = member_similarity[target_member_idx].argsort()[:-num_neighbors - 1:-1]

    # 사용자 간의 관심사 유사도 계산 및 관심사 추천
    recommendations = []
    similarity_scores = []

    for member_idx in similar_members_indices:
        if member_idx == target_member_idx:
            continue  # 자기 자신과의 유사도는 계산하지 않음

        similar_member_interests = members_interests[member_idx]["interests"]

        # 현재 사용자의 관심사로 등록된 항목을 추천 목록에서 제외
        similar_member_interests = [interest for interest in similar_member_interests if
                                    interest not in members_interests[target_member_idx]["interests"]]
        similarity_scores.append(member_similarity[target_member_idx][member_idx])
        recommendations.extend(similar_member_interests)

    # 중복 제거
    return list({interest['word']: interest for interest in recommendations}.values())


def get_most_similar_member(member_key, age):
    """
    현재 사용자와 가장 유사한 사용자가 최근 읽은 기사 추천
    
    :param member_key: 회원 고유값 
    :return: 가장 유사한 사용자가 최근 읽은 기사 중 현재 사용자가 읽지 않은 기사
    """
    # 회원 데이터 조회
    members_interests = get_members_interests(age)
    members_tfidf_matrix = get_members_tfidf_matrix(members_interests)

    # 코사인 유사도 계산 (사용자 간의 유사도)
    member_similarity = cosine_similarity(members_tfidf_matrix, members_tfidf_matrix)

    # 현재 사용자 인덱스
    target_member_index = get_member_id_by_member_key(member_key)[0] - 1

    # 가장 유사한 사용자 1명 선택 (코사인 유사도가 가장 높은 사용자)
    similar_members_indices = member_similarity[target_member_index].argsort()[::-1]
    most_similar_member_idx = -1
    for member_idx in similar_members_indices:
        if member_idx == target_member_index:
            continue
        most_similar_member_idx = member_idx

    most_similar_member_key = members_interests[most_similar_member_idx]['member_key']
    print(most_similar_member_key)

    # 가장 유사한 사용자의 회원 고유값을 사용해 최근 읽은 기사 조회 후 반환
    read_indices = get_article_indices_by_member_key(member_key)
    logging.debug(read_indices)
    article_indices = get_article_indices_by_member_key(most_similar_member_key)
    logging.debug(article_indices)

    read_indices = [id[0] for id in read_indices]
    article_indices = [id[0] for id in article_indices]

    unread_indices = [article_id for article_id in article_indices if article_id not in read_indices]

    return unread_indices


# get_most_similar_user('memberKey1')

# 회원 데이터 조회
# start = time.time()
# members_all = get_members_all()
# end = time.time()
# print(f"running time: {end - start: .5f}")

# 호출
# start = time.time()
# recommendations = get_ubcf_recommendations('memberKey1')
# end = time.time()
# print(f"running time: {end - start: .5f}")
#
# # 결과 출력: 추천 관심사 목록 출력
# print("Recommended Interests:")
# for interest in recommendations:
#     print(f"- {interest['word']}")
#
# target_user_index = get_member_id_by_member_key('memberKey1')[0] - 1
# members_interests = get_members_interests()
# # 결과 출력: 선택한 사용자의 관심사 출력
# print(f"\nUser {members_interests[target_user_index]['member_key']}'s Interests:")
# for interest in members_interests[target_user_index]['keywords']:
#     print(f"- {interest['word']}")


def jaccard_similarity(set1, set2):
    """
    자카드 유사도 계산

    :param set1: 집합 1
    :param set2: 집합 2
    :return: 두 집합 간의 자카드 유사도
    """
    intersection = len(set1.intersection(set2))
    union = len(set1.union(set2))
    return intersection / union if union != 0 else 0.0


def get_target_member_info(member_key):
    target_member = get_member_keyword_by_member_key(member_key)

    # 이전 코드에서는 하나의 키워드만 고려했으므로, 이제 여러 키워드를 고려하기 위해 리스트를 초기화합니다.
    keywords_list = []

    # 모든 키워드에 대해 반복하여 새로운 데이터 구조에 추가합니다.
    for member in target_member:
        keyword_info = {'keyword_id': member['keyword_id'], 'word': member['word']}
        keywords_list.append(keyword_info)

    # 새로운 데이터 구조를 생성합니다.
    new_target_member = {'member_key': member_key, 'keywords': keywords_list}
    logging.debug(f"target_member = {new_target_member}")

    return new_target_member


def calculate_similarity(members_interests, target_member):
    """
    현재 회원과 전체 회원 간의 자카드 유사도 계산
    
    :param members_interests: 회원별 관심 키워드 릭스트
    :param target_member: 현재 회원
    :return: 현재 회원과 전체 회원 간의 자카드 유사도 계산 결과
    """
    target_interests = set(item['word'] for item in target_member['keywords'])

    similarities = defaultdict(float)
    for member in members_interests:
        if member['member_key'] == target_member['member_key']:
            continue
        member_interests = set(item['word'] for item in member['keywords'])
        similarity = jaccard_similarity(target_interests, member_interests)
        similarities[member['member_key']] = similarity
    return similarities


def create_recommendations(similarities, target_member):
    """
    자카드 유사도를 통한 추천 기사 리스트 생성
    
    :param similarities: 사용자별 유사도 리스트
    :param target_member: 현재 사용자
    :return:자카도 유사도를 통한 추천 기사 리스트
    """
    # 자카드 유사도가 가장 높은 상위 N명 추천
    top_n = 5
    sorted_recommendations = sorted(similarities.items(), key=lambda x: x[1], reverse=True)[:top_n]

    # 추천 결과 생성
    logging.debug(f"{target_member['member_key']}의 관심사 : {target_member['keywords']}")
    logging.debug(f"{target_member['member_key']}의 관심사와 유사한 회원 추천:")
    for member_key, similarity in sorted_recommendations:
        if similarity > 0:
            print(f"회원: {member_key}, 유사도: {similarity}")
            member_idx = get_member_id_by_member_key(member_key)[0] - 1

    read_indices = get_article_indices_by_member_key(target_member['member_key'])
    article_indices = get_article_indices_by_member_key(sorted_recommendations[0][0])

    read_indices = [id[0] for id in read_indices]
    article_indices = [id[0] for id in article_indices]

    unread_indices = [article_id for article_id in article_indices if article_id not in read_indices]

    articles = get_articles(unread_indices)
    return articles


def get_jaccard_recommendation(member_key, age):
    """
    자카드 유사도를 사용한 추천 기사 리스트 생성

    :param member_key: 회원 고유값
    :param age: 연령
    :return: 자카드 유사도를 사용한 추천 기사 리스트
    """
    members_interests = get_members_interests(age)

    start = time.time()
    # 자카드 유사도 계산 대상 회원 데이터 조회
    target_member = get_target_member_info(member_key)

    # calculate_similarity
    # 자카드 유사도 계산 및 결과 저장
    similarities = calculate_similarity(members_interests, target_member)
    end = time.time()
    logging.debug(f"jaccard running time: {end - start: .5f}")

    return create_recommendations(similarities, target_member)

# print(get_jaccard_recommendation('memberKey23', 10))
