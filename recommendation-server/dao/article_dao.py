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


def get_article_indices():
    """
    기사 식별키 리스트 조회

    :return: 최근 3000개의 기사 식별키 리스트
    """
    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor()

        sql = (
            "SELECT `article_id` "
            "FROM `article` "
            "WHERE published_date BETWEEN DATE_SUB(CURDATE(), INTERVAL 1 YEAR) "
            "AND DATE_ADD(CURDATE(), INTERVAL 1 YEAR) "
            "ORDER BY `published_date` DESC "
        )

        cursor.execute(sql)
        row = cursor.fetchall()
        cursor.close()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()
    return row


def get_articles(articleIds: list):
    """
    기사 식별키 리스트에 해당하는 기사 조회

    :param articleIds: 기사 식별키 리스트
    :return: 기사 식별키 리스트에 해당하는 기사 리스트
    """
    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor(dictionary=True)

        placeholders = ', '.join(['%s'] * len(articleIds))
        # log.debug(articleIds)

        sql = (
            "SELECT `article_id`, `title`, `thumbnail_img` "
            "FROM `article` "
            f"WHERE `article_id` IN ({placeholders}) "
        )
        # log.debug(sql)

        cursor.execute(sql, articleIds)
        row = cursor.fetchall()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()
    return row


def get_article_indices_by_member_key(member_key):
    """
    회원 고유값에 해당하는 회원의 읽은 기사 식별키 리스트 조회

    :param member_key: 회원 고유값
    :return: 해당 회원의 읽은 기사 식별키 리스트
    """

    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor()

        sql = (
            "SELECT `article_id` "
            "FROM `article_read` "
            "WHERE `member_key` = %s "
        )

        cursor.execute(sql, (member_key, ))
        row = cursor.fetchall()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()

    return row
