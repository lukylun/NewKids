import mysql.connector.pooling

# 커넥션 풀 생성
connection_pool = mysql.connector.pooling.MySQLConnectionPool(
    host="127.0.0.1",
    port=3306,
    user="ssafy",
    password="ssafy",
    database="newkids",
    charset="utf8",
)


def get_member_keyword_all(age):
    """
    현재 사용자와 같은 나이의 회원들이 관심을 가진 키워드 리스트 조회
    조회 횟수가 높은 것을 관심도가 높은 것으로 가정
    
    :param age: 현재 사용자의 나이
    :return: 현재 사용자와 같은 나이의 회원들이 관심을 가진 키워드 리스트
    """

    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor(dictionary=True)

        sql = (
            "SELECT m.member_key, k.keyword_id, k.word, m.age "
            "FROM keyword_search as ks "
            "JOIN keyword as k on ks.keyword_id = k.keyword_id "
            "JOIN members as m on ks.member_key = m.member_key "
            "WHERE m.age = %s "
            "ORDER BY ks.count "
            "LIMIT 5 "
        )

        cursor.execute(sql, (age, ))
        row = cursor.fetchall()

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if cursor:
            cursor.close()
        if connection:
            connection.close()

    return row


def get_member_keyword_by_member_key(member_key):
    """
    현재 회원의 관심사 조회

    :param member_key: 현재 회원의 고유값
    :return: 현재 회원의 관심사
    """
    row = None
    connection = None

    try:
        connection = connection_pool.get_connection()
        cursor = connection.cursor(dictionary=True)

        sql = (
            "SELECT m.member_key, k.keyword_id, k.word, m.age "
            "FROM keyword_search as ks "
            "JOIN keyword as k on ks.keyword_id = k.keyword_id "
            "JOIN members as m on ks.member_key = m.member_key "
            "WHERE m.member_key = %s "
            "ORDER BY ks.count "
            "LIMIT 5 "
        )

        cursor.execute(sql, (member_key, ))
        row = (cursor.fetchall())
        cursor.close()
        print(row)

    except Exception as e:
        print(f"Exception={e}")
    finally:
        if connection:
            connection.close()

    return row
