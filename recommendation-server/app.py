import logging as log
import time

from flask import Flask, request, jsonify
from flask_restx import Api, Resource, fields

from service.article.article import get_recommend_articles
from service.recommendation.cbf import get_recommend_ids
from service.recommendation.ubcf import get_ubcf_recommendations, get_most_similar_member, get_jaccard_recommendation

app = Flask(__name__)
api = Api(app, version="1.0", title="기사 추천 API 문서", doc="/api-docs")

Api = api.namespace("recommend", "추천 기사 목록 조회")

# init logging
log.basicConfig(level=log.DEBUG)

articles = [
    {
        "articleId": 1,
        "title": "[생활뉴스] 토끼와 함께라면 행복져요!",
        "thumbnail": "기사 썸네일"
    },
    {
        "articleId": 2,
        "title": "코골이 치료, 수면무호흡증 여부 먼저 확인하세요.",
        "thumbnail": "기사 썸네일"
    }
]

result_fields = Api.model(
    'article', {
        'article_id': fields.Integer(default=1),
        'title': fields.String(default='[생활뉴스] 토끼와 함께라면 행복져요!'),
        'thumbnail_img': fields.String(default='썸네일 URL')
    }
)

recommend_results = Api.model(
    'articleList', {
        'articles': fields.List(fields.Nested(result_fields))
    }
)


@Api.route('/content-base-filter')
@Api.doc(params={
    'articleId': {'in': 'query', 'description': '기사 식별키', 'default': '1'}
})
class CbfResult(Resource):
    @Api.response(200, "Success", recommend_results)
    def get(self):
        """
        컨텐츠 기반 필터링 추천 API

        컨텐츠 기반 필터링 추천 결과 목록
        """

        parameters = request.args.to_dict()
        if len(parameters) == 0:
            return "BAD_REQUEST"

        articleId = parameters.get("articleId")
        log.debug(f"articleId={articleId}")

        recommend_ids = get_recommend_ids(articleId)
        if len(recommend_ids) == 0:
            return jsonify({"message": "NO_CONTENT"})
        start = time.time()
        recommend_articles = get_recommend_articles(recommend_ids)
        end = time.time()
        log.debug(f"read recommend article running time: {end-start: .5f}")

        return jsonify(recommend_articles)


# @Api.route('/collaborative-filter/keywords')
# @Api.doc(params={
#     'memberKey': {'in': 'query', 'description': '회원 고유키', 'default': 'memberKey1'},
#     'age': {'in': 'query', 'description': '회원 연령', 'default': '10'}
# })
# class CfRecommendByKeywords(Resource):
#     @Api.response(200, "Success", recommend_results)
#     def get(self):
#         """
#         사용자별 관심 키워드 기반 협업 필터링 추천 API
#         현재 사용자 관심 키워드 중 다른 사용자의 관심 키워드에 속하지만
#         현재 사용자의 관심 키워드에 속하지 않는 키워드 추천
#
#         협업 필터링 추천 결과 목록
#         """
#         parameters = request.args.to_dict()
#         if len(parameters) == 0:
#             return "BAD_REQUEST"
#
#         member_key = parameters.get("memberKey")
#         log.debug(f"member_key: {member_key}")
#
#         age = parameters.get("age")
#         log.debug(f"age: {age}")
#
#         # 코사인 유사도를 구해서 추천 알고리즘 수행 -> 키워드 리스트 추출
#         start = time.time()
#         recommendations = get_ubcf_recommendations('memberKey1')
#         end = time.time()
#         print(f"running time: {end - start: .5f}")
#
#         # TODO: 2023-09-21 해당 키워드를 핵심 키워드로 갖는 기사 추천
#
#         return jsonify(recommendations)


@Api.route('/collaborative-filter/members')
@Api.doc(params={
    'memberKey': {'in': 'query', 'description': '회원 고유키', 'default': 'memberKey1'},
})
class CfRecommendByMostSimilarMember(Resource):
    @Api.response(200, "Success", recommend_results)
    def get(self):
        """
        사용자별 관심 키워드 기반 협업 필터링 추천 API
        현재 사용자와 가장 유사한 사용자가 읽었던 기사 중
        현재 사용자가 읽지 않았던 기사 추천

        협업 필터링 추천 결과 목록
        """
        parameters = request.args.to_dict()
        if len(parameters) == 0:
            return "BAD_REQUEST"

        member_key = parameters.get("memberKey")
        log.debug(f"member_key: {member_key}")

        age = parameters.get("age")
        log.debug(f"age: {age}")

        # 코사인 유사도를 구해서 추천 알고리즘 수행 -> 키워드 리스트 추출
        start = time.time()
        recommendations = get_jaccard_recommendation(member_key, age)
        end = time.time()
        print(f"running time: {end - start: .5f}")

        return jsonify(recommendations)


if __name__ == '__main__':
    app.run(debug=True, host='0.0.0.0')
