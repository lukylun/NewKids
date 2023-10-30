from dao.article_dao import get_articles


def get_recommend_articles(article_ids):
    return get_articles(article_ids)
