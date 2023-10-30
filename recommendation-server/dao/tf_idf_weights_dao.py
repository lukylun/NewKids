import logging as log
import time

import mysql.connector.pooling

# 커넥션 풀 생성
connection_pool = mysql.connector.pooling.MySQLConnectionPool(
    host="127.0.0.1",
    port=3306,
    user="ssafy",
    password="ssafy",
    database="newkids",
    charset="utf8"
)


def get_article_tf_idf(articleIds: list):
    """
    기사 식별키 리스트에 해당하는 TF-IDF 가중치 벡터 행렬 조회

    :param articleIds: 기사 식별키 리스트
    :return: 기사 식별키 리스트에 해당하는 TF-IDF 가중치 벡터 행렬
    """
    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor(dictionary=True)

        sql = (
            "SELECT `article_id`, `keyword_vector`, `weight` "
            "FROM `article_tfidf` "
            "WHERE `article_id` IN ({}) "
            "ORDER BY `article_id`"
        )

        placeholders = ', '.join(['%s'] * len(articleIds))
        start = time.time()
        cursor.execute(sql.format(placeholders), tuple(articleIds))
        end = time.time()
        log.debug(f"tfidf read time: {end - start: .5f} sec")

        row = cursor.fetchall()
        cursor.close()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()
    return row


def get_article_tf_idf_by_article_id(article_id):
    """
    기사 식별키에 해당하는 TF-IDF 가중치 벡터 행렬 조회

    :param article_id: 기사 식별키
    :return: 기사 식별키에 해당하는 TF-IDF 가중치 벡터 행렬
    """
    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor(dictionary=True)

        sql = (
            "SELECT `article_id`, `keyword_vector`, `weight` "
            "FROM `article_tfidf` "
            "WHERE `article_id` = %s "
        )

        cursor.execute(sql, (article_id,))
        row = cursor.fetchall()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()
    return row
